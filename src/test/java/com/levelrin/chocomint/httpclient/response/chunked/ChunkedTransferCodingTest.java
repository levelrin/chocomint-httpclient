/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response.chunked;

import com.levelrin.chocomint.httpclient.response.BodyFromBytes;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class ChunkedTransferCodingTest {

    @Test
    void shouldGetDataFromChunks() {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Transfer-Encoding", "chunked");
        MatcherAssert.assertThat(
            "Should handle the Transfer-Encoding: chunked",
            new ChunkedTransferCoding(
                headers,
                new BodyFromBytes(this.chunks())
            ).toString(),
            CoreMatchers.equalTo(
                "Wikipedia in \r\n"
                + "\r\nchunks."
            )
        );
    }

    @Test
    void shouldGetDataFromOrigin() {
        final List<Integer> body = new ArrayList<>();
        for (final byte octet : "Apple".getBytes(StandardCharsets.UTF_8)) {
            body.add((int) octet);
        }
        MatcherAssert.assertThat(
            "Should get the body using origin.",
            new ChunkedTransferCoding(
                new HashMap<>(),
                new BodyFromBytes(body)
            ).toString(),
            CoreMatchers.equalTo("Apple")
        );
    }

    /**
     * It creates the data for testing.
     * The data would look like this:
     * 4\r\n        (bytes to send)
     * Wiki\r\n     (data)
     * 6\r\n        (bytes to send)
     * pedia \r\n   (data)
     * E\r\n        (bytes to send)
     * in \r\n
     * \r\n
     * chunks.\r\n  (data)
     * 0\r\n        (final byte - 0)
     * \r\n         (end message)
     * @return Data in chunks.
     */
    private List<Integer> chunks() {
        final List<Integer> result = new ArrayList<>();
        final byte[] raw = (
            "4\r\n"
            + "Wiki\r\n"
            + "6\r\n"
            + "pedia \r\n"
            + "E\r\n"
            + "in \r\n"
            + "\r\n"
            + "chunks.\r\n"
            + "0\r\n"
            + "\r\n"
        ).getBytes(StandardCharsets.UTF_8);
        for (final byte octet : raw) {
            result.add((int) octet);
        }
        return result;
    }

}
