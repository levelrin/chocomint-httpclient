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
final class ChunkDataTest {

    @Test
    @SuppressWarnings("MagicNumber")
    void shouldReturnData() {
        MatcherAssert.assertThat(
            "Should read the data by the given size.",
            new ChunkData(
                4,
                this.chunkBytes()
            ).data(),
            CoreMatchers.equalTo(
                "Wiki".getBytes(StandardCharsets.UTF_8)
            )
        );
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void shouldReturnBytesAfterData() {
        MatcherAssert.assertThat(
            "Should return the rest of data after reading the chunk data.",
            new ChunkData(
                4,
                this.chunkBytes()
            ).rest(),
            CoreMatchers.equalTo(
                (
                    "0\r\n"
                        + "trailer\r\n"
                        + "\r\n"
                ).getBytes(StandardCharsets.UTF_8)
            )
        );
    }

    /**
     * A data for testing.
     * @return Bytes where "Transfer-Encoding: chunked" is used.
     */
    private byte[] chunkBytes() {
        return ("Wiki\r\n"
            + "0\r\n"
            + "trailer\r\n"
            + "\r\n").getBytes(StandardCharsets.UTF_8);
    }

}
