/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide.method;

import com.levelrin.chocomint.httpclient.guide.ConfigKeys;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class TraceTest {

    @Test
    @SuppressWarnings("unchecked")
    void newHeaderShouldBeAdded() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        new Trace(config).header("One", "Uno");
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
        new Trace(config)
            .header("fruits", "apple")
            .header("fruits", "banana");
        MatcherAssert.assertThat(
            "A header should be appended if the header name exists already.",
            (Map<String, String>) config.get(ConfigKeys.HEADERS),
            Matchers.hasEntry("fruits", "apple, banana")
        );
    }

}
