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
 * Test cases related to Transfer-Encoding.
 */
final class TransferEncodingTests {

    /**
     * Web server for testing.
     */
    private static Service service;

    @BeforeAll
    @SuppressWarnings("MagicNumber")
    static void startServer() {
        service = Service.ignite();
        service.port(4569);
        service.get("/get", (request, response) -> {
            response.header("Transfer-Encoding", "chunked");
            return "Apple";
        });
        service.awaitInitialization();
    }

    @AfterAll
    static void stopServer() {
        service.awaitStop();
    }

    @Test
    void chunkedEncoding() {
        MatcherAssert.assertThat(
            "\"Transfer-Encoding: chunked\" should be handled.",
            new Http()
                .url("http://localhost:4569/get")
                .get()
                .send()
                .body()
                .toString(),
            CoreMatchers.equalTo("Apple")
        );
    }

}
