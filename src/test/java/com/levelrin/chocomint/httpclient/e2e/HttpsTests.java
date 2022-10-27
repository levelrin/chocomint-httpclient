/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.e2e;

import com.levelrin.chocomint.httpclient.guide.Http;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Service;

/**
 * Tests related to HTTPS (secure connection).
 */
final class HttpsTests {

    /**
     * Web server for testing.
     */
    private static Service service;

    @BeforeAll
    @SuppressWarnings("MagicNumber")
    static void startServer() {
        service = Service.ignite();
        System.setProperty("javax.net.ssl.trustStore", "src/test/resources/test-truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");
        service.secure(
            "src/test/resources/test-keystore.jks",
            "password",
            null,
            null
        );
        service.port(4568);
        service.get("/get", (request, response) -> "GET received");
        service.awaitInitialization();
    }

    @AfterAll
    static void stopServer() {
        service.awaitStop();
    }

    @Test
    void sendHttpsGetRequest() {
        MatcherAssert.assertThat(
            "Should receive the response body from GET request.",
            new Http()
                .url("https://localhost:4568/get")
                .get()
                .send()
                .body()
                .toString(),
            CoreMatchers.equalTo("GET received")
        );
    }

}
