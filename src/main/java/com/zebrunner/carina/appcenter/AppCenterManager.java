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
import com.zebrunner.carina.commons.artifact.IAppInfo;
import com.zebrunner.carina.commons.artifact.IArtifactManager;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.utils.config.StandardConfigurationOption;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by boyle on 8/16/17.
 */
public class AppCenterManager implements IArtifactManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Map<String, LazyInitializer<AppCenterApp>> APP_INFO_MAP = new ConcurrentHashMap<>();
    private static final ThreadLocal<ApiClient> API_CLIENT = ThreadLocal.withInitial(() -> new ApiClient().addDefaultHeader("x-api-token",
            Configuration.getRequired(AppCenterConfiguration.Parameter.APPCENTER_TOKEN,
                    StandardConfigurationOption.DECRYPT)));
    private static final String INVALID_LINK_MESSAGE = "AppCenter url is not correct: %s%n It should be like: %s.";
    private static final String VALID_LINK = "appcenter://appName/platformName/buildType/version";

    private static final String APP_NAME = "appName";
    private static final String PLATFORM_NAME = "platformName";
    private static final String BUILD_TYPE = "buildType";
    private static final String APP_VERSION = "version";

    private AppCenterManager() {
        // hide
    }

    /**
     * Pattern for link like {@code appcenter://appName/platformName/buildType/version}
     */
    private static final Pattern APP_CENTER_ENDPOINT_PATTERN = Pattern.compile(
            "appcenter:\\/\\/(?<" + APP_NAME + ">[a-zA-Z-0-9][^\\/]*)\\/"
                    + "(?<" + PLATFORM_NAME + ">[a-zA-Z-0-9][^\\/]*)\\/"
                    + "(?<" + BUILD_TYPE + ">[a-zA-Z-0-9][^\\/]*)\\/"
                    + "(?<" + APP_VERSION + ">[a-zA-Z-0-9][^\\/]*)");

    /**
     * Get new instance of {@link AppCenterManager}
     *
     * @return {@link AppCenterManager}
     */
    public static synchronized AppCenterManager getInstance() {
        return new AppCenterManager();
    }

    /**
     * Get direct (pre-signed) link to the application
     *
     * @param appName      takes in the AppCenter Name to look for.
     * @param platformName takes in the platform we wish to download for.
     * @param buildType    takes in the particular build to download (i.e. Prod.AdHoc, QA.Debug, Prod-Release, QA-Internal etc...)
     * @param version      takes in either "latest" to take the first build that matches the criteria or allows to consume a version to download that
     *                     build.
     * @return download url for build artifact.
     * @deprecated use {@link #getAppInfo(String, String, String, String)} instead
     */
    @Deprecated(forRemoval = true, since = "1.2.6")
    public String getDownloadUrl(String appName, String platformName, String buildType, String version) {
        if (StringUtils.isBlank(appName) ||
                StringUtils.isBlank(platformName) ||
                StringUtils.isBlank(buildType) ||
                StringUtils.isBlank(version)) {
            throw new IllegalArgumentException("Parameters could not be null or empty or blank.");
        }
        return getAppInfo(appName, platformName, buildType, version)
                .getDirectLink();
    }

    /**
     * Get {@link AppCenterApp} with the information about application (direct link, version and so on)
     *
     * @param appName      takes in the AppCenter Name to look for.
     * @param platformName takes in the platform we wish to download for.
     * @param buildType    takes in the particular build to download (i.e. Prod.AdHoc, QA.Debug, Prod-Release, QA-Internal etc...)
     * @param version      takes in either "latest" to take the first build that matches the criteria or allows to consume a version to download that
     *                     build.
     * @return {@link AppCenterApp}
     */
    public AppCenterApp getAppInfo(String appName, String platformName, String buildType, String version) {
        return getAppInfo(String.format("appcenter://%s/%s/%s/%s", appName, platformName, buildType, version));
    }

    /**
     * Get {@link AppCenterApp} with the information about application (direct link, version and so on)
     *
     * @param originalLink {@code appcenter://appName/platformName/buildType/version}
     * @return {@link AppCenterApp}
     */
    public AppCenterApp getAppInfo(String originalLink) {
        try {
            return APP_INFO_MAP.computeIfAbsent(originalLink,
                            link -> new LazyInitializer<>() {
                                @Override
                                protected AppCenterApp initialize() throws ConcurrentException {
                                    Matcher matcher = APP_CENTER_ENDPOINT_PATTERN.matcher(Objects.requireNonNull(link));
                                    if (!matcher.find()) {
                                        throw new IllegalArgumentException(String.format(INVALID_LINK_MESSAGE, link, VALID_LINK));
                                    }
                                    String appName = matcher.group(APP_NAME);
                                    String platformName = matcher.group(PLATFORM_NAME);
                                    String buildType = matcher.group(BUILD_TYPE);
                                    String version = matcher.group(APP_VERSION);

                                    Map<String, App> appMap = new AccountApi(API_CLIENT.get())
                                            .appsList(null)
                                            .stream()
                                            .filter(a -> StringUtils.equalsIgnoreCase(platformName, a.getOs().toString()) &&
                                                    StringUtils.containsIgnoreCase(a.getName(), appName))
                                            .map(a -> {
                                                String app = a.getName();
                                                return new ImmutablePair<>(app, a);
                                            })
                                            .sorted((a, b) -> Comparator.<String>reverseOrder()
                                                    .compare(getLatestBuildDate(a.getLeft(), a.getRight().getUpdatedAt(), a.getRight().getOwner().getName()),
                                                            getLatestBuildDate(b.getLeft(), b.getRight().getUpdatedAt(), a.getRight().getOwner().getName())))
                                            .collect(Collectors.toMap(ImmutablePair::getLeft, ImmutablePair::getRight, (e1, e2) -> e1, LinkedHashMap::new));

                                    if (appMap.isEmpty()) {
                                        throw new NotFoundException(
                                                String.format("Application '%s' Not Found in AppCenter, Platform (%s)",
                                                        appName,
                                                        platformName));
                                    }

                                    for (Map.Entry<String, App> entry : appMap.entrySet()) {
                                        String name = entry.getKey();
                                        App app = entry.getValue();

                                        List<ReleasesAvailableToTester> retrieveList = new DistributeApi(API_CLIENT.get())
                                                .releasesList(app.getOwner().getName(), name, true,
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
                                                AppCenterApp appCenterApp = new AppCenterApp();
                                                appCenterApp.setVersion(build.getShortVersion());
                                                appCenterApp.setBuild(build.getVersion());

                                                ReleaseDetailsResponse appBuild = new DistributeApi(API_CLIENT.get()).releasesGetLatestByUser(
                                                        String.valueOf(latestBuildNumber),
                                                        app.getOwner().getName(), name, null, null);

                                                if (checkBuild(version, appBuild) && (checkTitleForCorrectPattern(buildType.toLowerCase(), appBuild)
                                                        || checkNotesForCorrectBuild(
                                                        buildType.toLowerCase(), appBuild))) {
                                                    LOGGER.debug("Print Build Info: {}", appBuild);
                                                    LOGGER.info("Fetching Build ID ({}) Version: {} ({})", latestBuildNumber, appCenterApp.getVersion(),
                                                            appCenterApp.getBuild());
                                                    String buildUrl = appBuild.getDownloadUrl();
                                                    LOGGER.info("Download URL For Build: {}", buildUrl);
                                                    appCenterApp.setDirectLink(buildUrl);
                                                    return appCenterApp;
                                                }
                                            }
                                        }
                                    }
                                    throw new NotFoundException(String.format("Unable to find build to download, version provided (%s)", version));
                                }
                            })
                    .get();
        } catch (ConcurrentException e) {
            return ExceptionUtils.rethrow(e);
        }
    }

    @Override
    public boolean download(String from, Path to) {
        boolean isSuccessful = false;
        Matcher matcher = APP_CENTER_ENDPOINT_PATTERN.matcher(ObjectUtils.requireNonEmpty(from));
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format(INVALID_LINK_MESSAGE, from, VALID_LINK));
        }

        try {
            getBuild(Objects.requireNonNull(to).toFile().getAbsolutePath(),
                    matcher.group(APP_NAME),
                    matcher.group(PLATFORM_NAME),
                    matcher.group(BUILD_TYPE),
                    matcher.group(APP_VERSION));
            isSuccessful = true;
        } catch (Exception e) {
            LOGGER.error("Cannot download application from AppCenter. Message: " + e.getMessage(), e);
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
        return getAppInfo(url).getDirectLink();
    }

    /**
     * @param folder       to which upload build artifact.
     * @param appName      takes in the AppCenter Name to look for.
     * @param platformName takes in the platform we wish to download for.
     * @param buildType    takes in the particular build to download (i.e. Prod.AdHoc, QA.Debug, Prod-Release, QA-Internal etc...)
     * @param version      takes in either "latest" to take the first build that matches the criteria or allows to consume a version to download that
     *                     build.
     * @return file to the downloaded build artifact
     */
    public File getBuild(String folder, String appName, String platformName, String buildType, String version) {
        AppCenterApp appCenterApp = getAppInfo(appName, platformName, buildType, version);
        //TODO: wrap below code into the public download method
        String fileName = FilenameUtils.concat(folder, createFileName(appName, buildType, platformName, appCenterApp));
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
                URL downloadLink = new URL(appCenterApp.getDirectLink());
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
     * @param fileName     will be the name of the downloaded file.
     * @param downloadLink will be the URL to retrieve the build from.
     * @return brings back a true/false on whether or not the build was successfully downloaded.
     * @throws IOException throws a non Interruption Exception up.
     */
    private static boolean downloadBuild(String fileName, URL downloadLink) throws IOException {
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
     * The updated_at field returned by AppCenter doesn't contain the "latest time" a build was updated, so we grab the first build to do our sort.
     *
     * @param app          name of the app to check.
     * @param appUpdatedAt passing in of a backup date value if the app we look at doesn't have a build associated to it.
     * @return the date value to be used in sorting.
     */
    private static String getLatestBuildDate(String app, String appUpdatedAt, String ownerName) {
        List<ReleasesAvailableToTester> retrieveList = new DistributeApi(API_CLIENT.get()).releasesList(ownerName, app,
                true, "tester", null, null);
        if (!retrieveList.isEmpty()) {
            return retrieveList.get(0).getUploadedAt();
        }
        return appUpdatedAt;
    }

    private static boolean checkBuild(String version, ReleaseDetailsResponse node) {
        if ("latest".equalsIgnoreCase(version)) {
            return true;
        }
        return version.equalsIgnoreCase(
                node.getShortVersion() + "." + node.getVersion())
                || version.equalsIgnoreCase(node.getShortVersion());
    }

    private static String createFileName(String appName, String buildType, String platformName, AppCenterApp appCenterApp) {
        String fileName = String.format("%s.%s.%s.%s", appName, buildType, appCenterApp.getVersion(), appCenterApp.getBuild())
                .replace(" ", "");
        if (platformName.toLowerCase().contains("ios")) {
            return fileName + ".ipa";
        }
        return fileName + ".apk";
    }

    private static boolean checkNotesForCorrectBuild(String pattern, ReleaseDetailsResponse node) {
        LOGGER.debug("\nPattern to be checked: {}", pattern);
        String nodeField = StringUtils.lowerCase(node.getReleaseNotes());
        String[] splitPattern = pattern.split("\\.");
        LinkedList<Boolean> segmentsFound = new LinkedList<>();
        for (String segment : splitPattern) {
            segmentsFound.add(StringUtils.contains(nodeField, segment));
        }
        if (!segmentsFound.isEmpty() && !segmentsFound.contains(false)) {
            LOGGER.debug("\nPattern match found!! This is the buildType to be used: {}", nodeField);
            return true;
        }
        String patternToReplace = ".*[ ->\\S]%s[ -<\\S].*";
        return !pattern.isEmpty() && scanningAllNotes(String.format(patternToReplace, pattern), nodeField);
    }

    private static boolean checkTitleForCorrectPattern(String pattern, ReleaseDetailsResponse node) {
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
        return !pattern.isEmpty() &&
                scanningAllNotes(String.format(patternToReplace, pattern), nodeField);
    }

    private static boolean scanningAllNotes(String pattern, String noteField) {
        return Pattern.compile(pattern)
                .matcher(noteField)
                .find();
    }
}
