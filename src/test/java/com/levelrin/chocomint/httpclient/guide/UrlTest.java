/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import com.levelrin.chocomint.httpclient.guide.method.Methods;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Unit tests.
 */
final class UrlTest {

    @Test
    void shouldConfigureGetMethod() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).get();
        MatcherAssert.assertThat(
            "HTTP GET should be configured.",
            config.get(ConfigKeys.METHOD),
            CoreMatchers.equalTo(Methods.GET)
        );
    }

    @Test
    void shouldConfigureHeadMethod() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).head();
        MatcherAssert.assertThat(
            "HTTP HEAD should be configured.",
            config.get(ConfigKeys.METHOD),
            CoreMatchers.equalTo(Methods.HEAD)
        );
    }

    @Test
    void shouldConfigurePostMethod() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).post();
        MatcherAssert.assertThat(
            "HTTP POST should be configured.",
            config.get(ConfigKeys.METHOD),
            CoreMatchers.equalTo(Methods.POST)
        );
    }

    @Test
    void shouldConfigurePutMethod() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).put();
        MatcherAssert.assertThat(
            "HTTP PUT should be configured.",
            config.get(ConfigKeys.METHOD),
            CoreMatchers.equalTo(Methods.PUT)
        );
    }

    @Test
    void shouldConfigureDeleteMethod() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).delete();
        MatcherAssert.assertThat(
            "HTTP DELETE should be configured.",
            config.get(ConfigKeys.METHOD),
            CoreMatchers.equalTo(Methods.DELETE)
        );
    }

    @Test
    void shouldConfigureConnectMethod() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).connect();
        MatcherAssert.assertThat(
            "HTTP CONNECT should be configured.",
            config.get(ConfigKeys.METHOD),
            CoreMatchers.equalTo(Methods.CONNECT)
        );
    }

    @Test
    void shouldConfigureOptionsMethod() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).options();
        MatcherAssert.assertThat(
            "HTTP OPTIONS should be configured.",
            config.get(ConfigKeys.METHOD),
            CoreMatchers.equalTo(Methods.OPTIONS)
        );
    }

    @Test
    void shouldConfigureTraceMethod() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).trace();
        MatcherAssert.assertThat(
            "HTTP TRACE should be configured.",
            config.get(ConfigKeys.METHOD),
            CoreMatchers.equalTo(Methods.TRACE)
        );
    }

    @Test
    void shouldConfigurePatchMethod() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).patch();
        MatcherAssert.assertThat(
            "HTTP PATCH should be configured.",
            config.get(ConfigKeys.METHOD),
            CoreMatchers.equalTo(Methods.PATCH)
        );
    }

    @Test
    void shouldPutEmptyMapForHeadersOnGet() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).get();
        MatcherAssert.assertThat(
            "A map for the headers should be included on GET.",
            config,
            Matchers.hasKey(ConfigKeys.HEADERS)
        );
    }

    @Test
    void shouldPutEmptyMapForHeadersOnHead() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).head();
        MatcherAssert.assertThat(
            "A map for the headers should be included on HEAD.",
            config,
            Matchers.hasKey(ConfigKeys.HEADERS)
        );
    }

    @Test
    void shouldPutEmptyMapForHeadersOnPost() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).post();
        MatcherAssert.assertThat(
            "A map for the headers should be included on POST.",
            config,
            Matchers.hasKey(ConfigKeys.HEADERS)
        );
    }

    @Test
    void shouldPutEmptyMapForHeadersOnPut() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).put();
        MatcherAssert.assertThat(
            "A map for the headers should be included on PUT.",
            config,
            Matchers.hasKey(ConfigKeys.HEADERS)
        );
    }

    @Test
    void shouldPutEmptyMapForHeadersOnDelete() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).delete();
        MatcherAssert.assertThat(
            "A map for the headers should be included on DELETE.",
            config,
            Matchers.hasKey(ConfigKeys.HEADERS)
        );
    }

    @Test
    void shouldPutEmptyMapForHeadersOnConnect() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).connect();
        MatcherAssert.assertThat(
            "A map for the headers should be included on CONNECT.",
            config,
            Matchers.hasKey(ConfigKeys.HEADERS)
        );
    }

    @Test
    void shouldPutEmptyMapForHeadersOnOptions() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).options();
        MatcherAssert.assertThat(
            "A map for the headers should be included on OPTIONS.",
            config,
            Matchers.hasKey(ConfigKeys.HEADERS)
        );
    }

    @Test
    void shouldPutEmptyMapForHeadersOnTrace() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).trace();
        MatcherAssert.assertThat(
            "A map for the headers should be included on TRACE.",
            config,
            Matchers.hasKey(ConfigKeys.HEADERS)
        );
    }

    @Test
    void shouldPutEmptyMapForHeadersOnPatch() {
        final Map<ConfigKeys, Object> config = new HashMap<>();
        new Url(config).patch();
        MatcherAssert.assertThat(
            "A map for the headers should be included on PATCH.",
            config,
            Matchers.hasKey(ConfigKeys.HEADERS)
        );
    }

}
