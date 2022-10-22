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
final class HeadersFromBytesTest {

    @Test
    void shouldConvertBytesToHeaders() {
        final byte[] data = ("Content-Length: 123\r\n"
            + "Content-Type: application/json").getBytes(StandardCharsets.UTF_8);
        final List<Integer> list = new ArrayList<>();
        for (final byte octet : data) {
            list.add((int) octet);
        }
        MatcherAssert.assertThat(
            "Should convert the bytes to the map (headers).",
            new HeadersFromBytes(list).get(),
            CoreMatchers.allOf(
                Matchers.hasEntry("Content-Length", "123"),
                Matchers.hasEntry("Content-Type", "application/json")
            )
        );
    }

}
