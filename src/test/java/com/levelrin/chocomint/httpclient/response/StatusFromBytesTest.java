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
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class StatusFromBytesTest {

    @Test
    @SuppressWarnings("MagicNumber")
    void shouldReturnCode() {
        MatcherAssert.assertThat(
            "Should return the code.",
            new StatusFromBytes(this.statusLine()).code(),
            CoreMatchers.equalTo(204)
        );
    }

    @Test
    void shouldReturnReason() {
        MatcherAssert.assertThat(
            "Should return the reason.",
            new StatusFromBytes(this.statusLine()).reason(),
            CoreMatchers.equalTo("No Content")
        );
    }

    @Test
    void shouldReturnStatusLineInString() {
        MatcherAssert.assertThat(
            "Should return the entire status line in String.",
            new StatusFromBytes(this.statusLine()).toString(),
            CoreMatchers.equalTo("HTTP/1.1 204 No Content")
        );
    }

    /**
     * Fake status line.
     * @return Bytes in list of integers.
     */
    private List<Integer> statusLine() {
        return this.toList(
            "HTTP/1.1 204 No Content".getBytes(StandardCharsets.UTF_8)
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
