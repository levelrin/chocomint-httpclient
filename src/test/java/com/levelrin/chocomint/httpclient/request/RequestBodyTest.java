/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.request;

import com.levelrin.chocomint.httpclient.fake.FakeOutputStream;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
@SuppressFBWarnings("OS_OPEN_STREAM")
final class RequestBodyTest {

    @Test
    void shouldWriteBodyWithLineBreakAtFront() throws IOException {
        final StringBuilder actual = new StringBuilder();
        try (FakeOutputStream output = new FakeOutputStream().with(data -> {
            actual.append(new String(data, StandardCharsets.UTF_8));
        })) {
            new RequestBody("a".getBytes(StandardCharsets.UTF_8)).accept(output);
        }
        MatcherAssert.assertThat(
            "Should write body to the output stream with a line break at front.",
            actual.toString(),
            CoreMatchers.equalTo("\r\na")
        );
    }

}
