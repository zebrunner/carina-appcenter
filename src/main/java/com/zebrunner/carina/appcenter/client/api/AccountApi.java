package com.zebrunner.carina.appcenter.client.api;

import com.google.gson.reflect.TypeToken;
import com.zebrunner.carina.appcenter.client.ApiClient;
import com.zebrunner.carina.appcenter.client.ApiException;
import com.zebrunner.carina.appcenter.client.ApiResponse;
import com.zebrunner.carina.appcenter.client.Pair;
import com.zebrunner.carina.appcenter.client.UncheckedApiException;
import com.zebrunner.carina.appcenter.client.model.App;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountApi {
    private final ApiClient apiClient;

    public AccountApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get a list of apps
     *
     * @param orderBy the name of the attribute by which to order the response by.
     *                By default, apps are in order of creation. All results are ordered in ascending order. (optional)
     * @return {@link List} of {@link App}
     */
    public List<App> appsList(String orderBy) {
        try {
            List<Pair> localVarQueryParams = new ArrayList<>();
            List<Pair> localVarCollectionQueryParams = new ArrayList<>();
            Map<String, String> localVarHeaderParams = new HashMap<>();
            Map<String, String> localVarCookieParams = new HashMap<>();
            Map<String, Object> localVarFormParams = new HashMap<>();

            if (orderBy != null) {
                localVarQueryParams.addAll(apiClient.parameterToPair("orderBy", orderBy));
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

            okhttp3.Call localVarCall = apiClient.buildCall("/v0.1/apps", "GET",
                    localVarQueryParams, localVarCollectionQueryParams,
                    null,
                    localVarHeaderParams, localVarCookieParams, localVarFormParams, new String[] { "APIToken" });

            Type localVarReturnType = new TypeToken<List<App>>() {
            }.getType();
            ApiResponse<List<App>> localVarResp = apiClient.execute(localVarCall, localVarReturnType);
            return localVarResp.getData();
        } catch (ApiException e) {
            throw new UncheckedApiException("Cannot get apps list. Message:" + e.getMessage(), e);
        }
    }

}
