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
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
@SuppressFBWarnings("OS_OPEN_STREAM")
final class RequestHeadersTest {

    @Test
    void shouldSendHeaders() throws IOException {
        final StringBuilder actual = new StringBuilder();
        try (FakeOutputStream output = new FakeOutputStream().with(data -> {
            actual.append(new String(data, StandardCharsets.UTF_8));
        })) {
            final Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Content-Length", "123");
            new RequestHeaders(headers).accept(output);
        }
        MatcherAssert.assertThat(
            "Should write the headers to the output stream.",
            actual.toString(),
            CoreMatchers.anyOf(
                CoreMatchers.equalTo(
                    "Content-Type: application/json\r\n"
                        + "Content-Length: 123\r\n"
                ),
                CoreMatchers.equalTo(
                    "Content-Length: 123\r\n"
                        + "Content-Type: application/json\r\n"
                )
            )
        );
    }

}
