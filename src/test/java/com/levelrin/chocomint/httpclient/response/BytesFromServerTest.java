/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import com.levelrin.chocomint.httpclient.fake.FakeInputStream;
import java.util.Arrays;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class BytesFromServerTest {

    @Test
    void shouldReadAllBytes() {
        MatcherAssert.assertThat(
            "Should read all bytes from the input stream.",
            new BytesFromServer(new FakeInputStream(new byte[] {1, 1, 0})).get(),
            CoreMatchers.equalTo(
                Arrays.asList(1, 1, 0)
            )
        );
    }

}
