/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response.chunked;

import java.nio.charset.StandardCharsets;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class ChunkSizeLineTest {

    @Test
    @SuppressWarnings("MagicNumber")
    void shouldReturnChunkSize() {
        MatcherAssert.assertThat(
            "Should return the chunk size.",
            new ChunkSizeLine(
                this.chunkBytes()
            ).size(),
            CoreMatchers.equalTo(4)
        );
    }

    @Test
    void shouldReturnBytesAfterFirstLine() {
        MatcherAssert.assertThat(
            "Should return the rest of the bytes after parsing the first line.",
            new ChunkSizeLine(
                this.chunkBytes()
            ).rest(),
            CoreMatchers.equalTo(
                (
                    "Wiki\r\n"
                    + "0\r\n"
                    + "trailer\r\n"
                    + "\r\n"
                ).getBytes(StandardCharsets.UTF_8)
            )
        );
    }

    @Test
    void shouldReturnEmptyBytesForRestIfSizeIsZero() {
        MatcherAssert.assertThat(
            "The rest of bytes should be empty if the size is zero.",
            new ChunkSizeLine(
                (
                    "0\r\n"
                    + "trailer\r\n"
                    + "\r\n"
                ).getBytes(StandardCharsets.UTF_8)
            ).rest().length,
            CoreMatchers.equalTo(0)
        );
    }

    /**
     * A data for testing.
     * @return Bytes where "Transfer-Encoding: chunked" is used.
     */
    private byte[] chunkBytes() {
        return ("4;chunk-ext-name1;chunk-ext-name2=chunk-ext-val\r\n"
            + "Wiki\r\n"
            + "0\r\n"
            + "trailer\r\n"
            + "\r\n").getBytes(StandardCharsets.UTF_8);
    }

}
