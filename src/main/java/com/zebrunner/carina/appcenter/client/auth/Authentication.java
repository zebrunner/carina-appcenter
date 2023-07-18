package com.zebrunner.carina.appcenter.client.auth;

import com.zebrunner.carina.appcenter.client.ApiException;
import com.zebrunner.carina.appcenter.client.Pair;

import java.net.URI;
import java.util.List;
import java.util.Map;

public interface Authentication {

    void applyToParams(List<Pair> queryParams, Map<String, String> headerParams, Map<String, String> cookieParams, String payload, String method,
            URI uri) throws
            ApiException;
}
