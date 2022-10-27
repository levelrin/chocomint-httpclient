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
 * It has basic use cases.
 */
final class BasicTests {

    /**
     * Web server for testing.
     */
    private static Service service;

    @BeforeAll
    static void startServer() {
        service = Service.ignite();
        service.get("/get", (request, response) -> "GET received");
        service.post("/post", (request, response) -> {
            final String body = request.body();
            final String result;
            if ("body from client".equals(body)) {
                result = "expected POST body received";
            } else {
                result = String.format("unexpected POST body received: %s", body);
            }
            return result;
        });
        service.delete("/delete", (request, response) -> {
            final int noContent = 204;
            response.status(noContent);
            return "";
        });
        service.awaitInitialization();
    }

    @AfterAll
    static void stopServer() {
        service.awaitStop();
    }

    @Test
    void sendGetRequest() {
        MatcherAssert.assertThat(
            "Should receive the response body from GET request.",
            new Http()
                .url("http://localhost:4567/get")
                .get()
                .send()
                .body()
                .toString(),
            CoreMatchers.equalTo("GET received")
        );
    }

    @Test
    void sendPostRequest() {
        MatcherAssert.assertThat(
            "Should receive the response body from POST request.",
            new Http()
                .url("http://localhost:4567/post")
                .post()
                .body("body from client")
                .send()
                .body()
                .toString(),
            CoreMatchers.equalTo("expected POST body received")
        );
    }

    @Test
    void sendDeleteRequest() {
        final int expected = 204;
        MatcherAssert.assertThat(
            "Should receive the response code from DELETE request.",
            new Http()
                .url("http://localhost:4567/delete")
                .delete()
                .send()
                .status()
                .code(),
            CoreMatchers.equalTo(expected)
        );
    }

}
