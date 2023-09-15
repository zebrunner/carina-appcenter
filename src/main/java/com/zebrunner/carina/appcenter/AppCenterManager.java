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

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableBiMap;
import com.zebrunner.carina.appcenter.http.resttemplate.RestTemplateBuilder;
import com.zebrunner.carina.commons.artifact.IArtifactManager;
import com.zebrunner.carina.utils.Configuration;
import com.zebrunner.carina.utils.Configuration.Parameter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
    private static final Map<String, LazyInitializer<AppInfo>> CACHE_APP_INFO = new ConcurrentHashMap<>();

    private static final ThreadLocal<RestTemplate> restTemplate = ThreadLocal.withInitial(() -> {
        return RestTemplateBuilder.newInstance()
                .withDisabledSslChecking()
                .withSpecificJsonMessageConverter().build();
    });

    private static final String HOST_URL = "api.appcenter.ms";
    private static final String API_APPS = "/v0.1/apps";

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
    }

    public static synchronized AppCenterManager getInstance() {
        return new AppCenterManager();
    }

    /**
     * @param appName      takes in the AppCenter Name to look for.
     * @param platformName takes in the platform we wish to download for.
     * @param buildType    takes in the particular build to download (i.e. Prod.AdHoc, QA.Debug, Prod-Release, QA-Internal etc...)
     * @param version      takes in either "latest" to take the first build that matches the criteria or allows to consume a version to download that
     *                     build.
     * @return download url for build artifact.
     */
    @Deprecated(forRemoval = true)
    public static String getDownloadUrl(String appName, String platformName, String buildType, String version) {
        return scanAppForBuild(getAppId(appName, platformName), buildType, version, new AppInfo());
    }

    @Deprecated(forRemoval = true)
    public static String getDownloadUrl(String appName, String platformName, String buildType, String version, AppInfo appInfo) {
        return scanAppForBuild(getAppId(appName, platformName), buildType, version, appInfo);
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
        return getAppInfo(url).getDirectLink();
    }

    public AppInfo getAppInfo(String originalLink) {
        Matcher matcher = APP_CENTER_ENDPOINT_PATTERN.matcher(Objects.requireNonNull(originalLink));
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("AppCenter url is not correct: %s%n It should be like: %s.",
                    originalLink, "appcenter://appName/platformName/buildType/version"));
        }

        try {
            return CACHE_APP_INFO.computeIfAbsent(originalLink,
                            link -> new LazyInitializer<>() {
                                @Override
                                protected AppInfo initialize() throws ConcurrentException {
                                    AppInfo info = new AppInfo();

                                    info.setDirectLink(getDownloadUrl(matcher.group(APP_NAME), matcher.group(PLATFORM_NAME), matcher.group(BUILD_TYPE),
                                            matcher.group(APP_VERSION), info));
                                    return info;
                                }
                            })
                    .get();
        } catch (ConcurrentException e) {
            return ExceptionUtils.rethrow(e);
        }
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
    public  static File getBuild(String folder, String appName, String platformName, String buildType, String version) {
        AppInfo appInfo = getInstance().getAppInfo(String.format("appcenter://%s/%s/%s/%s", appName, platformName, buildType, version));

        //TODO: wrap below code into the public download method
        String fileName = FilenameUtils.concat(folder, createFileName(appName, buildType, platformName, appInfo));
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
                URL downloadLink = new URL(appInfo.getDirectLink());
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
     * @param appName      takes in the AppCenter Name to look for.
     * @param platformName takes in the platform we wish to download for.
     * @return Map&lt;String, String&gt;
     */
    private static Map<String, ImmutablePair<String, String>> getAppId(String appName, String platformName) {
        Map<String, ImmutablePair<String, String>> appMap = new HashMap<>();

        RequestEntity<String> retrieveApps = buildRequestEntity(
                HOST_URL,
                API_APPS,
                HttpMethod.GET);
        JsonNode appResults = restTemplate.get().exchange(retrieveApps, JsonNode.class).getBody();
        LOGGER.info("AppCenter Searching For App: {}", appName);
        LOGGER.debug("AppCenter JSON Response: {}", appResults);
        Objects.requireNonNull(appResults);

        for (JsonNode node : appResults) {
            if (platformName.equalsIgnoreCase(node.get("os").asText()) && node.get("name").asText().toLowerCase().contains(appName.toLowerCase())) {
                String ownerName = node.get("owner").get("name").asText();
                String app = node.get("name").asText();
                LOGGER.info("Found Owner: {} App: {}", ownerName, app);
                appMap.put(app, getLatestBuildDate(app, node.get("updated_at").asText(), ownerName));
            }
        }

        if (!appMap.isEmpty()) {
            return appMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        }

        throw new NotFoundException(
                String.format("Application Not Found in AppCenter for Name (%s), Platform (%s)", appName, platformName));
    }

    /**
     * @param apps      takes in the application Ids
     * @param buildType takes in the particular build to download (i.e. Prod.AdHoc, QA.Debug, Prod-Release, QA-Internal etc...)
     * @param version   takes in either "latest" to take the first build that matches the criteria or allows to consume a version to download that
     *                  build.
     * @return String
     */
    private static String scanAppForBuild(Map<String, ImmutablePair<String, String>> apps, String buildType, String version, AppInfo appInfo) {
        for (String currentApp : apps.keySet()) {
            LOGGER.info("Scanning App {}", currentApp);
            MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
            queryParams.add("published_only", "true");
            queryParams.add("scope", "tester");

            RequestEntity<String> retrieveList = buildRequestEntity(
                    HOST_URL,
                    String.format("%s/%s/%s/releases", API_APPS, apps.get(currentApp).getRight(), currentApp),
                    queryParams,
                    HttpMethod.GET);
            JsonNode buildList = restTemplate.get().exchange(retrieveList, JsonNode.class).getBody();
            LOGGER.debug("Available Builds JSON: {}", buildList);

            Objects.requireNonNull(buildList);

            if (buildList.size() > 0) {
                int buildLimiter = 0;
                for (JsonNode build : buildList) {

                    buildLimiter += 1;
                    if (buildLimiter >= 50) {
                        break;
                    }

                    String latestBuildNumber = build.get("id").asText();
                    String versionShort = build.get("short_version").asText();
                    String versionLong = build.get("version").asText();

                    RequestEntity<String> retrieveBuildUrl = buildRequestEntity(
                            HOST_URL,
                            String.format("%s/%s/%s/releases/%s", API_APPS, apps.get(currentApp).getRight(), currentApp, latestBuildNumber),
                            HttpMethod.GET);
                    JsonNode appBuild = restTemplate.get().exchange(retrieveBuildUrl, JsonNode.class).getBody();

                    Objects.requireNonNull(appBuild);

                    if (checkBuild(version, appBuild) && (checkTitleForCorrectPattern(buildType.toLowerCase(), appBuild) || checkNotesForCorrectBuild(
                            buildType.toLowerCase(), appBuild))) {
                        LOGGER.debug("Print Build Info: {}", appBuild);
                        appInfo.setVersion(versionShort);
                        appInfo.setBuild(versionLong);
                        LOGGER.info("Fetching Build ID ({}) Version: {} ({})", latestBuildNumber, versionShort, versionLong);
                        String buildUrl = appBuild.get("download_url").asText();
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
     * @param app          name of the app to check.
     * @param appUpdatedAt passing in of a backup date value if the app we look at doesn't have a build associated to it.
     * @return the date value to be used in sorting.
     */
    private static ImmutablePair<String, String> getLatestBuildDate(String app, String appUpdatedAt, String ownerName) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("published_only", "true");
        queryParams.add("scope", "tester");

        RequestEntity<String> retrieveList = buildRequestEntity(
                HOST_URL,
                String.format("%s/%s/%s/releases", API_APPS, ownerName, app),
                queryParams,
                HttpMethod.GET);
        JsonNode buildList = restTemplate.get().exchange(retrieveList, JsonNode.class).getBody();
        Objects.requireNonNull(buildList);

        if (buildList.size() > 0) {
            return new ImmutablePair<>(buildList.get(0).get("uploaded_at").asText(), ownerName);
        }
        return new ImmutablePair<>(appUpdatedAt, ownerName);
    }

    private static boolean checkBuild(String version, JsonNode node) {

        if ("latest".equalsIgnoreCase(version)) {
            return true;
        }

        return version.equalsIgnoreCase(
                node.get("short_version").asText() + "." + node.get("version").asText())
                || version.equalsIgnoreCase(node.get("short_version").asText());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static RequestEntity<String> buildRequestEntity(String hostUrl, String path, HttpMethod httpMethod) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(hostUrl)
                .path(path)
                .build();

        return new RequestEntity(setHeaders(), httpMethod, uriComponents.toUri());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static RequestEntity<String> buildRequestEntity(String hostUrl, String path,
            MultiValueMap<String, String> listQueryParams, HttpMethod httpMethod) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(hostUrl)
                .path(path)
                .queryParams(listQueryParams)
                .build();

        return new RequestEntity(setHeaders(), httpMethod, uriComponents.toUri());
    }

    private static HttpHeaders setHeaders() {

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("Content-Type", "application/json; charset=utf-8");
        httpHeader.add("x-api-token", Configuration.get(Parameter.APPCENTER_TOKEN));

        return httpHeader;
    }

    private static String createFileName(String appName, String buildType, String platformName, AppInfo appInfo) {

        String fileName = String.format("%s.%s.%s.%s", appName, buildType, appInfo.getVersion(), appInfo.getBuild())
                .replace(" ", "");

        if (platformName.toLowerCase().contains("ios")) {
            return fileName + ".ipa";
        }
        return fileName + ".apk";
    }

    private static boolean checkNotesForCorrectBuild(String pattern, JsonNode node) {

        return checkForPattern("release_notes", pattern, node);
    }

    private static boolean checkTitleForCorrectPattern(String pattern, JsonNode node) {
        return checkForPattern("app_name", pattern, node);
    }

    private static boolean checkForPattern(String nodeName, String pattern, JsonNode node) {
        LOGGER.debug("\nPattern to be checked: {}", pattern);
        if (node.findPath("release_notes").isMissingNode()) {
            return false;
        }

        String nodeField = node.get(nodeName).asText().toLowerCase();
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

    private static boolean searchFieldsForString(String pattern, String stringToSearch) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(stringToSearch);

        return m.find();
    }

    private static boolean scanningAllNotes(String pattern, String noteField) {
        boolean foundMessages = false;

        foundMessages = searchFieldsForString(pattern, noteField);

        return foundMessages;
    }
}
