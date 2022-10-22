/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import java.util.HashMap;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class HeaderTest {

    @Test
    @SuppressWarnings("unchecked")
    void shouldAddHeader() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        config.put(ConfigKeys.HEADERS, new HashMap<>());
        new Header("one", "uno", config).add();
        MatcherAssert.assertThat(
            "Should add the header to the configuration.",
            (Map<String, String>) config.get(ConfigKeys.HEADERS),
            Matchers.hasEntry("one", "uno")
        );
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldAppendHeader() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        config.put(ConfigKeys.HEADERS, new HashMap<>());
        new Header("hana", "ichi", config).add();
        new Header("hana", "yi", config).add();
        MatcherAssert.assertThat(
            "Should append the value if the header name is the same.",
            (Map<String, String>) config.get(ConfigKeys.HEADERS),
            Matchers.hasEntry("hana", "ichi, yi")
        );
    }

    @Test
    void shouldThrowOnForbiddenHeader() {
        final String[] forbidden = {
            "Accept-Charset",
            "Accept-Encoding",
            "Access-Control-Request-Headers",
            "Access-Control-Request-Method",
            "Connection",
            "Content-Length",
            "Cookie",
            "Date",
            "DNT",
            "Expect",
            "Feature-Policy",
            "Host",
            "Keep-Alive",
            "Origin",
            "Referer",
            "TE",
            "Trailer",
            "Transfer-Encoding",
            "Upgrade",
            "Via",
            "Proxy-Any",
            "Sec-Any",
        };
        int count = 0;
        for (final String name: forbidden) {
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    final Map<ConfigKeys, Object> config = new HashMap<>();
                    config.put(ConfigKeys.HEADERS, new HashMap<>());
                    new Header(name, "any", config).add();
                }
            );
            count = count + 1;
        }
        MatcherAssert.assertThat(
            "Should throw an exception on forbidden headers.",
            count,
            CoreMatchers.equalTo(forbidden.length)
        );
    }

}
