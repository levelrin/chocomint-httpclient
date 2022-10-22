/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide.method;

import com.levelrin.chocomint.httpclient.guide.ConfigKeys;
import com.levelrin.chocomint.httpclient.guide.ConnectionEntry;
import com.levelrin.chocomint.httpclient.guide.Header;
import com.levelrin.chocomint.httpclient.response.Response;
import java.util.Map;

/**
 * It represents HTTP HEAD method.
 * It's not thread-safe and mutable.
 */
public final class Head {

    /**
     * It contains the request configuration from the previous steps.
     * We will continue to store more configuration data in this.
     */
    private final Map<ConfigKeys, Object> config;

    /**
     * Constructor.
     * @param config See {@link Head#config}.
     */
    public Head(final Map<ConfigKeys, Object> config) {
        this.config = config;
    }

    /**
     * Add an HTTP header.
     * @param name Header name.
     * @param value Header value.
     * @return This object.
     */
    public Head header(final String name, final String value) {
        new Header(name, value, this.config).add();
        return this;
    }

    /**
     * Send an HTTP request and obtain the response.
     * @return HTTP response.
     */
    public Response send() {
        return new ConnectionEntry(this.config).response();
    }

}
