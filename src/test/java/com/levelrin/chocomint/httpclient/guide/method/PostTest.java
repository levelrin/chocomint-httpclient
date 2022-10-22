/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide.method;

import com.levelrin.chocomint.httpclient.guide.ConfigKeys;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class PostTest {

    @Test
    @SuppressWarnings("unchecked")
    void newHeaderShouldBeAdded() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        new Post(config).header("One", "Uno");
        MatcherAssert.assertThat(
            "New header should be added.",
            (Map<String, String>) config.get(ConfigKeys.HEADERS),
            Matchers.hasEntry("One", "Uno")
        );
    }

    @Test
    @SuppressWarnings("unchecked")
    void headerShouldBeAppended() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        new Post(config)
            .header("fruits", "apple")
            .header("fruits", "banana");
        MatcherAssert.assertThat(
            "A header should be appended if the header name exists already.",
            (Map<String, String>) config.get(ConfigKeys.HEADERS),
            Matchers.hasEntry("fruits", "apple, banana")
        );
    }

    @Test
    void textBodyShouldBeConfigured() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Post(config).body("Yoi Yoi");
        MatcherAssert.assertThat(
            "Text body should be configured.",
            config.get(ConfigKeys.BODY),
            CoreMatchers.equalTo("Yoi Yoi".getBytes(StandardCharsets.UTF_8))
        );
    }

    @Test
    void binaryBodyShouldBeConfigured() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Post(config).body(new byte[] {0, 1});
        MatcherAssert.assertThat(
            "Binary body should be configured.",
            config.get(ConfigKeys.BODY),
            CoreMatchers.equalTo(new byte[] {0, 1})
        );
    }

}
