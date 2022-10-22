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
 * Unit tests.
 */
final class HttpTest {

    @Test
    void shouldThrowExceptionIfSchemeDoesNotExist() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new Http().url("www.levelrin.com"),
            "Exception should be thrown if the URL doesn't have the scheme."
        );
    }

    @Test
    void originalConfigShouldNotBeAffected() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Http(config).url("https://www.levelrin.com");
        MatcherAssert.assertThat(
            "The original configuration map should not be affected.",
            config,
            CoreMatchers.not(
                Matchers.hasKey(ConfigKeys.URL)
            )
        );
    }

}
