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
import java.util.concurrent.atomic.AtomicReference;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
@SuppressFBWarnings("OS_OPEN_STREAM")
final class RequestLineTest {

    @Test
    void shouldSendRequestLine() throws IOException {
        final AtomicReference<String> actual = new AtomicReference<>();
        try (FakeOutputStream output = new FakeOutputStream().with(data -> {
            actual.set(new String(data, StandardCharsets.UTF_8));
        })) {
            new RequestLine("GET", "/test").accept(output);
        }
        MatcherAssert.assertThat(
            "Should write the request line to the output stream.",
            actual.get(),
            CoreMatchers.equalTo("GET /test HTTP/1.1\r\n")
        );
    }

}
