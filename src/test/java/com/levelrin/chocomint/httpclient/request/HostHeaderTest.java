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
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
@SuppressFBWarnings("OS_OPEN_STREAM")
final class HostHeaderTest {

    @Test
    void shouldAddHostHeader() throws IOException {
        final AtomicReference<String> actual = new AtomicReference<>();
        try (FakeOutputStream output = new FakeOutputStream().with(data -> {
            actual.set(new String(data, StandardCharsets.UTF_8));
        })) {
            new HostHeader(new URL("https://localhost")).accept(output);
        }
        MatcherAssert.assertThat(
            "Should add the Host header.",
            actual.get(),
            CoreMatchers.equalTo("Host: localhost\r\n")
        );
    }

}
