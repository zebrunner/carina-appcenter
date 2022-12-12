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
package com.zebrunner.carina.appcenter.http.resttemplate.ssl;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mk on 6/30/15.
 */
public class DisabledSslClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        if (connection instanceof HttpsURLConnection) {
            SSLContext sslContext = null;
            TrustManager[] trustManagerArray = { new NullX509TrustManager() };
            try {
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustManagerArray, null);
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                throw new RuntimeException("Something went wrong when try to init SSLContext", e);
            }

            ((HttpsURLConnection) connection).setSSLSocketFactory(sslContext.getSocketFactory());
            ((HttpsURLConnection) connection).setHostnameVerifier(new NoopHostnameVerifier());
        }
        super.prepareConnection(connection, httpMethod);
    }

}
