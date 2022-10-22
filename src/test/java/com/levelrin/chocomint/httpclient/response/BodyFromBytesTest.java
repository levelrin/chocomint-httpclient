/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import java.util.Arrays;
import java.util.Collections;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class BodyFromBytesTest {

    @Test
    void shouldReturnByteArray() {
        MatcherAssert.assertThat(
            "Should convert the list to byte array.",
            new BodyFromBytes(Arrays.asList(1, 1, 0)).inBytes(),
            CoreMatchers.equalTo(new byte[] {1, 1, 0})
        );
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void shouldConvertToString() {
        MatcherAssert.assertThat(
            "Should convert the list to String.",
            new BodyFromBytes(Collections.singletonList(65)).toString(),
            CoreMatchers.equalTo("A")
        );
    }

}
