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
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class ResponseFromBytesTest {

    @Test
    void shouldReturnResponseInString() {
        MatcherAssert.assertThat(
            "Should return the entire response in String.",
            new ResponseFromBytes(this.responseWithBody()).toString(),
            CoreMatchers.equalTo(
                "HTTP/1.1 200 OK\r\n"
                    + "Content-Length: 123\r\n"
                    + "Content-Type: application/json\r\n\r\n"
                    + "{}"
            )
        );
    }

    @Test
    void shouldReturnStatusLineObject() {
        MatcherAssert.assertThat(
            "Should create a StatusLine object.",
            new ResponseFromBytes(this.responseWithBody()).status(),
            CoreMatchers.instanceOf(StatusLine.class)
        );
    }

    @Test
    void shouldReturnMapOfHeaders() {
        MatcherAssert.assertThat(
            "Should create a Map (headers) object.",
            new ResponseFromBytes(this.responseWithBody()).headers(),
            CoreMatchers.instanceOf(Map.class)
        );
    }

    @Test
    void shouldReturnResponseBodyObject() {
        MatcherAssert.assertThat(
            "Should create a ResponseBody object.",
            new ResponseFromBytes(this.responseWithBody()).body(),
            CoreMatchers.instanceOf(ResponseBody.class)
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
