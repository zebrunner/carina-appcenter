package com.zebrunner.carina.appcenter.client.api;

import com.google.gson.reflect.TypeToken;
import com.zebrunner.carina.appcenter.client.ApiClient;
import com.zebrunner.carina.appcenter.client.ApiException;
import com.zebrunner.carina.appcenter.client.ApiResponse;
import com.zebrunner.carina.appcenter.client.Pair;
import com.zebrunner.carina.appcenter.client.UncheckedApiException;
import com.zebrunner.carina.appcenter.client.model.ReleaseDetailsResponse;
import com.zebrunner.carina.appcenter.client.model.ReleasesAvailableToTester;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistributeApi {
    private final ApiClient apiClient;

    public DistributeApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get a release with id &#x60;release_id&#x60;. If &#x60;release_id&#x60; is &#x60;latest&#x60;, return the latest release
     * that was distributed to the current user (from all the distribution groups).
     *
     * @param releaseId     the ID of the release, or &#x60;latest&#x60; to get the latest release from all the distribution groups
     *                      assigned to the current user. (required)
     * @param ownerName     the name of the owner (required)
     * @param appName       the name of the application (required)
     * @param udid          when supplied, this call will also check if the given UDID is provisioned. Will be ignored for non-iOS platforms.
     *                      The value will be returned in the property is_udid_provisioned. (optional)
     * @param isInstallPage the check if the request is from Install page (optional)
     * @return {@link ReleaseDetailsResponse}
     */
    public ReleaseDetailsResponse releasesGetLatestByUser(String releaseId, String ownerName, String appName, String udid, Boolean isInstallPage) {
        try {
            // verify the required parameter 'releaseId' is set
            if (releaseId == null) {
                throw new ApiException("Missing the required parameter 'releaseId' when calling releasesGetLatestByUser(Async)");
            }

            // verify the required parameter 'ownerName' is set
            if (ownerName == null) {
                throw new ApiException("Missing the required parameter 'ownerName' when calling releasesGetLatestByUser(Async)");
            }

            // verify the required parameter 'appName' is set
            if (appName == null) {
                throw new ApiException("Missing the required parameter 'appName' when calling releasesGetLatestByUser(Async)");
            }

            // create path and map variables
            String localVarPath = "/v0.1/apps/{owner_name}/{app_name}/releases/{release_id}"
                    .replace("{" + "release_id" + "}", apiClient.escapeString(releaseId))
                    .replace("{" + "owner_name" + "}", apiClient.escapeString(ownerName))
                    .replace("{" + "app_name" + "}", apiClient.escapeString(appName));

            List<Pair> localVarQueryParams = new ArrayList<>();
            List<Pair> localVarCollectionQueryParams = new ArrayList<>();
            Map<String, String> localVarHeaderParams = new HashMap<>();
            Map<String, String> localVarCookieParams = new HashMap<>();
            Map<String, Object> localVarFormParams = new HashMap<>();

            if (udid != null) {
                localVarQueryParams.addAll(apiClient.parameterToPair("udid", udid));
            }

            if (isInstallPage != null) {
                localVarQueryParams.addAll(apiClient.parameterToPair("is_install_page", isInstallPage));
            }

            final String[] localVarAccepts = {
                    "application/json"
            };
            final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
            if (localVarAccept != null) {
                localVarHeaderParams.put("Accept", localVarAccept);
            }

            final String[] localVarContentTypes = {
            };
            final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
            if (localVarContentType != null) {
                localVarHeaderParams.put("Content-Type", localVarContentType);
            }

            String[] localVarAuthNames = new String[] { "APIToken" };
            okhttp3.Call localVarCall = apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams,
                    null,
                    localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames);

            Type localVarReturnType = new TypeToken<ReleaseDetailsResponse>() {
            }.getType();
            ApiResponse<ReleaseDetailsResponse> localVarResp = apiClient.execute(localVarCall, localVarReturnType);

            return localVarResp.getData();
        } catch (ApiException e) {
            throw new UncheckedApiException("Cannot get latest releases. Message: " + e.getMessage(), e);
        }
    }

    /**
     * Get basic information about releases.
     *
     * @param ownerName     the name of the owner (required)
     * @param appName       the name of the application (required)
     * @param publishedOnly when *true*, filters out releases that were uploaded but were never distributed. Releases that
     *                      under deleted distribution groups will not be filtered out. (optional)
     * @param scope         when the scope is &#39;tester&#39;, only includes releases that have been distributed to groups
     *                      that the user belongs to. (optional)
     * @param top           the number of releases to return (optional)
     * @param releaseId     the id of a release (optional)
     * @return {@link List} of {@link ReleasesAvailableToTester}
     */
    public List<ReleasesAvailableToTester> releasesList(String ownerName, String appName, Boolean publishedOnly, String scope,
            BigDecimal top, BigDecimal releaseId) {
        try {
            // verify the required parameter 'ownerName' is set
            if (ownerName == null) {
                throw new ApiException("Missing the required parameter 'ownerName' when calling releasesList(Async)");
            }

            // verify the required parameter 'appName' is set
            if (appName == null) {
                throw new ApiException("Missing the required parameter 'appName' when calling releasesList(Async)");
            }

            // create path and map variables
            String localVarPath = "/v0.1/apps/{owner_name}/{app_name}/releases"
                    .replace("{" + "owner_name" + "}", apiClient.escapeString(ownerName))
                    .replace("{" + "app_name" + "}", apiClient.escapeString(appName));

            List<Pair> localVarQueryParams = new ArrayList<>();
            List<Pair> localVarCollectionQueryParams = new ArrayList<>();
            Map<String, String> localVarHeaderParams = new HashMap<>();
            Map<String, String> localVarCookieParams = new HashMap<>();
            Map<String, Object> localVarFormParams = new HashMap<>();

            if (publishedOnly != null) {
                localVarQueryParams.addAll(apiClient.parameterToPair("published_only", publishedOnly));
            }

            if (scope != null) {
                localVarQueryParams.addAll(apiClient.parameterToPair("scope", scope));
            }

            if (top != null) {
                localVarQueryParams.addAll(apiClient.parameterToPair("top", top));
            }

            if (releaseId != null) {
                localVarQueryParams.addAll(apiClient.parameterToPair("releaseId", releaseId));
            }

            final String[] localVarAccepts = {
                    "application/json"
            };
            final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
            if (localVarAccept != null) {
                localVarHeaderParams.put("Accept", localVarAccept);
            }

            final String[] localVarContentTypes = {
            };
            final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
            if (localVarContentType != null) {
                localVarHeaderParams.put("Content-Type", localVarContentType);
            }

            String[] localVarAuthNames = new String[] { "APIToken" };
            okhttp3.Call call = apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams,
                    null,
                    localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames);

            ApiResponse<List<ReleasesAvailableToTester>> localVarResp = apiClient.execute(call,
                    new TypeToken<List<ReleasesAvailableToTester>>() {
                    }.getType());
            return localVarResp.getData();
        } catch (ApiException e) {
            throw new UncheckedApiException("Cannot get releases list. Message: " + e.getMessage(), e);
        }
    }

}
