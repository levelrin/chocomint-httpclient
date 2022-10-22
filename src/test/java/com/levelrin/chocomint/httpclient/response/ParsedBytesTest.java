/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class ParsedBytesTest {

    @Test
    void shouldParseStatusLine() {
        MatcherAssert.assertThat(
            "Should return the status line in bytes.",
            new ParsedBytes(this.responseWithBody()).statusLine(),
            CoreMatchers.equalTo(
                this.toList(
                    "HTTP/1.1 200 OK".getBytes(StandardCharsets.UTF_8)
                )
            )
        );
    }

    @Test
    void shouldParseHeaders() {
        MatcherAssert.assertThat(
            "Should return the headers in bytes.",
            new ParsedBytes(this.responseWithBody()).headers(),
            CoreMatchers.equalTo(
                this.toList(
                    ("Content-Length: 123\r\n"
                        + "Content-Type: application/json"
                    ).getBytes(StandardCharsets.UTF_8)
                )
            )
        );
    }

    @Test
    void shouldParseBody() {
        MatcherAssert.assertThat(
            "Should return the body in bytes.",
            new ParsedBytes(this.responseWithBody()).body(),
            CoreMatchers.equalTo(
                this.toList(
                    "{}".getBytes(StandardCharsets.UTF_8)
                )
            )
        );
    }

    @Test
    void shouldReturnEmptyListIfBodyDoesNotExist() {
        MatcherAssert.assertThat(
            "Should return an empty list if the body does not exist.",
            new ParsedBytes(this.responseWithoutBody()).body(),
            Matchers.empty()
        );
    }

    /**
     * Fake HTTP response.
     * @return Fake response with body.
     */
    private List<Integer> responseWithBody() {
        return this.toList(
            ("HTTP/1.1 200 OK\r\n"
                + "Content-Length: 123\r\n"
                + "Content-Type: application/json\r\n\r\n"
                + "{}"
            ).getBytes(StandardCharsets.UTF_8)
        );
    }

    /**
     * Fake HTTP response.
     * @return Fake response without body.
     */
    private List<Integer> responseWithoutBody() {
        return this.toList(
            ("HTTP/1.1 204 Created\r\n"
                + "Connection: closed\r\n\r\n"
            ).getBytes(StandardCharsets.UTF_8)
        );
    }

    /**
     * Convert the byte array to list of integers.
     * @param bytes We will convert this.
     * @return List of integers.
     */
    private List<Integer> toList(final byte[] bytes) {
        final List<Integer> result = new ArrayList<>();
        for (final byte octet : bytes) {
            result.add((int) octet);
        }
        return result;
    }

}
