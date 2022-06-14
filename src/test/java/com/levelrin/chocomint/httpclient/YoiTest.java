/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class YoiTest {

    @Test
    void test() {
        MatcherAssert.assertThat(
            "Should return Hello, World!",
            new Yoi().hello(),
            CoreMatchers.equalTo("Hello, World!")
        );
    }

}
