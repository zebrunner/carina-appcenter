/*******************************************************************************
 * Copyright 2020-2022 Zebrunner Inc (https://www.zebrunner.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.zebrunner.carina.appcenter;

import com.zebrunner.carina.appcenter.client.ApiClient;
import com.zebrunner.carina.appcenter.client.api.AccountApi;
import com.zebrunner.carina.appcenter.client.api.DistributeApi;
import com.zebrunner.carina.appcenter.client.model.App;
import com.zebrunner.carina.appcenter.client.model.ReleaseDetailsResponse;
import com.zebrunner.carina.appcenter.client.model.ReleasesAvailableToTester;
import com.zebrunner.carina.appcenter.config.AppCenterConfiguration;
import com.zebrunner.carina.commons.artifact.IArtifactManager;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.utils.config.StandardConfigurationOption;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by boyle on 8/16/17.
 */
public class AppCenterManager implements IArtifactManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final ApiClient apiClient;

    private String ownerName;
    private String versionLong;
    private String versionShort;

    private static final String APP_NAME = "appName";
    private static final String PLATFORM_NAME = "platformName";
    private static final String BUILD_TYPE = "buildType";
    private static final String APP_VERSION = "version";

    // appcenter://appName/platformName/buildType/version
    private static final Pattern APP_CENTER_ENDPOINT_PATTERN = Pattern.compile(
            "appcenter:\\/\\/(?<" + APP_NAME + ">[a-zA-Z-0-9][^\\/]*)\\/"
                    + "(?<" + PLATFORM_NAME + ">[a-zA-Z-0-9][^\\/]*)\\/"
                    + "(?<" + BUILD_TYPE + ">[a-zA-Z-0-9][^\\/]*)\\/"
                    + "(?<" + APP_VERSION + ">[a-zA-Z-0-9][^\\/]*)");
    private static AppCenterManager instance = null;

    private AppCenterManager() {
        this.apiClient = new ApiClient().addDefaultHeader("x-api-token",
                Configuration.getRequired(AppCenterConfiguration.Parameter.APPCENTER_TOKEN, StandardConfigurationOption.DECRYPT));
    }

    public static synchronized AppCenterManager getInstance() {
        if (instance == null) {
            instance = new AppCenterManager();
        }
        return instance;
    }

    /**
     * @param appName      takes in the AppCenter Name to look for.
     * @param platformName takes in the platform we wish to download for.
     * @param buildType    takes in the particular build to download (i.e. Prod.AdHoc, QA.Debug, Prod-Release, QA-Internal etc...)
     * @param version      takes in either "latest" to take the first build that matches the criteria or allows to consume a version to download that
     *                     build.
     * @return download url for build artifact.
     */
   public String getDownloadUrl(String appName, String platformName, String buildType, String version) {
       return scanAppForBuild(getAppId(appName, platformName), buildType, version);
   }

    @Override
    public boolean download(String from, Path to) {
        if (!ObjectUtils.allNotNull(from, to) || from.isEmpty()) {
            throw new IllegalArgumentException("Arguments cannot be null or empty.");
        }
        boolean isSuccessful = false;
        Matcher matcher = APP_CENTER_ENDPOINT_PATTERN.matcher(from);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("AppCenter url is not correct: %s%n It should be like: %s.",
                    from, "appcenter://appName/platformName/buildType/version"));
        }

        try {
            getBuild(to.toFile().getAbsolutePath(),
                    matcher.group(APP_NAME),
                    matcher.group(PLATFORM_NAME),
                    matcher.group(BUILD_TYPE),
                    matcher.group(APP_VERSION));
            isSuccessful = true;
        } catch (Exception e) {
            LOGGER.error("Something went wrong when try to download application from AppCenter.", e);
        }
        return isSuccessful;
    }

    @Override
    public boolean put(Path from, String to) throws FileNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(String url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDirectLink(String url) {
        if (Objects.isNull(url) || url.isEmpty()) {
            throw new IllegalArgumentException("Argument cannot be null or empty.");
        }
        Matcher matcher = APP_CENTER_ENDPOINT_PATTERN.matcher(url);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("AppCenter url is not correct: %s%n It should be like: %s.",
                    url, "appcenter://appName/platformName/buildType/version"));
        }
        return getDownloadUrl(matcher.group(APP_NAME), matcher.group(PLATFORM_NAME), matcher.group(BUILD_TYPE), matcher.group(APP_VERSION));
    }

    /**
     *
     * @param folder to which upload build artifact.
     * @param appName takes in the AppCenter Name to look for.
     * @param platformName takes in the platform we wish to download for.
     * @param buildType takes in the particular build to download (i.e. Prod.AdHoc, QA.Debug, Prod-Release, QA-Internal etc...)
     * @param version takes in either "latest" to take the first build that matches the criteria or allows to consume a version to download that
     *            build.
     * @return file to the downloaded build artifact
     */
    public File getBuild(String folder, String appName, String platformName, String buildType, String version) {
        String buildToDownload = getDownloadUrl(appName, platformName, buildType, version);

        //TODO: wrap below code into the public download method
        String fileName = FilenameUtils.concat(folder, createFileName(appName, buildType, platformName));
        File fileToLocate = null;

        try {
            File file = new File(folder);
            File[] listOfFiles = file.listFiles();

            if (file.list() != null) {
                Objects.requireNonNull(listOfFiles);
                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile() && fileName.contains(listOfFile.getName())) {
                        LOGGER.info("File has been Located Locally.  File path is: {}", listOfFile.getAbsolutePath());
                        fileToLocate = listOfFile;
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error Attempting to Look for Existing File!", ex);
        }

        if (fileToLocate == null) {
            try {
                LOGGER.debug("Beginning Transfer of AppCenter Build");
                URL downloadLink = new URL(buildToDownload);
                int retryCount = 0;
                boolean retry = true;
                while (retry && retryCount <= 5) {
                    retry = downloadBuild(fileName, downloadLink);
                    retryCount = retryCount + 1;
                }
                LOGGER.debug("AppCenter Build ({}) was retrieved", fileName);
            } catch (Exception ex) {
                LOGGER.error("Error Thrown When Attempting to Transfer AppCenter Build!", ex);
            }
        } else {
            LOGGER.info("Preparing to use local version of AppCenter Build...");
        }

        return new File(fileName);
    }

    /**
     *
     * @param fileName will be the name of the downloaded file.
     * @param downloadLink will be the URL to retrieve the build from.
     * @return brings back a true/false on whether or not the build was successfully downloaded.
     * @throws IOException throws a non Interruption Exception up.
     */
    private boolean downloadBuild(String fileName, URL downloadLink) throws IOException {
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(downloadLink.openStream());
                FileOutputStream fos = new FileOutputStream(fileName)) {
            if (Thread.currentThread().isInterrupted()) {
                LOGGER.debug("Current Thread ({}) is interrupted, clearing interruption.", Thread.currentThread().getId());
                Thread.interrupted();
            }
            fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            LOGGER.info("Successfully Transferred...");
            return false;
        } catch (ClosedByInterruptException ie1) {
            LOGGER.info("Retrying....");
            LOGGER.error("Getting Error!", ie1);
            return true;
        }
    }

    /**
     *
     * @param appName takes in the AppCenter Name to look for.
     * @param platformName takes in the platform we wish to download for.
     * @return Map&lt;String, String&gt;
     */
    private Map<String, String> getAppId(String appName, String platformName) {
        Map<String, String> appMap = new HashMap<>();

        for (App node : new AccountApi(apiClient).appsList(null)) {
            if (platformName.equalsIgnoreCase(node.getOs().toString()) && node.getName().toLowerCase().contains(appName.toLowerCase())) {
                ownerName = node.getOwner().getName();
                String app = node.getName();
                LOGGER.info("Found Owner: {} App: {}", ownerName, app);
                appMap.put(app, getLatestBuildDate(app, node.getUpdatedAt()));
            }
        }

        if (!appMap.isEmpty()) {
            return appMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        }
        throw new NotFoundException(String.format("Application Not Found in AppCenter for Organization (%s) Name (%s), Platform (%s)", ownerName, appName, platformName));
    }

    /**
     *
     * @param apps takes in the application Ids
     * @param buildType takes in the particular build to download (i.e. Prod.AdHoc, QA.Debug, Prod-Release, QA-Internal etc...)
     * @param version takes in either "latest" to take the first build that matches the criteria or allows to consume a version to download that
     *            build.
     * @return String
     */
    private String scanAppForBuild(Map<String, String> apps, String buildType, String version) {
        for (String currentApp : apps.keySet()) {
            LOGGER.info("Scanning App {}", currentApp);
            List<ReleasesAvailableToTester> retrieveList = new DistributeApi(apiClient).releasesList(ownerName, currentApp, true,
                    "tester", null,
                    null);

            LOGGER.debug("Available Builds JSON: {}", retrieveList);

            if (!retrieveList.isEmpty()) {
                int buildLimiter = 0;
                for (ReleasesAvailableToTester build : retrieveList) {

                    buildLimiter += 1;
                    if (buildLimiter >= 50) {
                        break;
                    }

                    Integer latestBuildNumber = build.getId();
                    versionShort = build.getShortVersion();
                    versionLong = build.getVersion();

                    ReleaseDetailsResponse appBuild = new DistributeApi(apiClient).releasesGetLatestByUser(String.valueOf(latestBuildNumber),
                            ownerName, currentApp, null, null);

                    if (checkBuild(version, appBuild) && (checkTitleForCorrectPattern(buildType.toLowerCase(), appBuild) || checkNotesForCorrectBuild(
                            buildType.toLowerCase(), appBuild))) {
                        LOGGER.debug("Print Build Info: {}", appBuild);
                        LOGGER.info("Fetching Build ID ({}) Version: {} ({})", latestBuildNumber, versionShort, versionLong);
                        String buildUrl = appBuild.getDownloadUrl();
                        LOGGER.info("Download URL For Build: {}", buildUrl);
                        return buildUrl;
                    }
                }
            }
        }

        throw new NotFoundException(String.format("Unable to find build to download, version provided (%s)", version));
    }

    /**
     * The updated_at field returned by AppCenter doesn't contain the "latest time" a build was updated, so we grab the first build to do our sort.
     *
     * @param app name of the app to check.
     * @param appUpdatedAt passing in of a backup date value if the app we look at doesn't have a build associated to it.
     * @return the date value to be used in sorting.
     */
    private String getLatestBuildDate(String app, String appUpdatedAt) {
        List<ReleasesAvailableToTester> retrieveList = new DistributeApi(apiClient).releasesList(ownerName, app, true, "tester", null,
                null);
        if (!retrieveList.isEmpty()) {
            return retrieveList.get(0).getUploadedAt();
        }
        return appUpdatedAt;
    }

    private boolean checkBuild(String version, ReleaseDetailsResponse node) {

        if ("latest".equalsIgnoreCase(version)) {
            return true;
        }

        return version.equalsIgnoreCase(
                node.getShortVersion() + "." + node.getVersion())
                || version.equalsIgnoreCase(node.getShortVersion());
    }

    private String createFileName(String appName, String buildType, String platformName) {

        String fileName = String.format("%s.%s.%s.%s", appName, buildType, versionShort, versionLong)
                .replace(" ", "");

        if (platformName.toLowerCase().contains("ios")) {
            return fileName + ".ipa";
        }
        return fileName + ".apk";
    }

    private boolean checkNotesForCorrectBuild(String pattern, ReleaseDetailsResponse node) {
        LOGGER.debug("\nPattern to be checked: {}", pattern);

        String nodeField = node.getReleaseNotes().toLowerCase();
        String[] splitPattern = pattern.split("\\.");
        LinkedList<Boolean> segmentsFound = new LinkedList<>();
        for (String segment : splitPattern) {
            segmentsFound.add(nodeField.contains(segment));
        }

        if (!segmentsFound.isEmpty() && !segmentsFound.contains(false)) {
            LOGGER.debug("\nPattern match found!! This is the buildType to be used: {}", nodeField);
            return true;
        }
        String patternToReplace = ".*[ ->\\S]%s[ -<\\S].*";
        return !pattern.isEmpty() && scanningAllNotes(String.format(patternToReplace, pattern), nodeField);
    }

    private boolean checkTitleForCorrectPattern(String pattern, ReleaseDetailsResponse node) {
        LOGGER.debug("\nPattern to be checked: {}", pattern);

        String nodeField = node.getAppName().toLowerCase();
        String[] splitPattern = pattern.split("\\.");
        LinkedList<Boolean> segmentsFound = new LinkedList<>();
        for (String segment : splitPattern) {
            segmentsFound.add(nodeField.contains(segment));
        }

        if (!segmentsFound.isEmpty() && !segmentsFound.contains(false)) {
            LOGGER.debug("\nPattern match found!! This is the buildType to be used: {}", nodeField);
            return true;
        }
        String patternToReplace = ".*[ ->\\S]%s[ -<\\S].*";
        return !pattern.isEmpty() && scanningAllNotes(String.format(patternToReplace, pattern), nodeField);

    }

    private boolean searchFieldsForString(String pattern, String stringToSearch) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(stringToSearch);

        return m.find();
    }

    private boolean scanningAllNotes(String pattern, String noteField) {
        boolean foundMessages = false;

        foundMessages = searchFieldsForString(pattern, noteField);

        return foundMessages;
    }
}
