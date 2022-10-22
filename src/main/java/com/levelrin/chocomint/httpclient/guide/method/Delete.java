/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide.method;

import com.levelrin.chocomint.httpclient.guide.Body;
import com.levelrin.chocomint.httpclient.guide.ConfigKeys;
import com.levelrin.chocomint.httpclient.guide.ConnectionEntry;
import com.levelrin.chocomint.httpclient.guide.Header;
import com.levelrin.chocomint.httpclient.response.Response;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * It represents HTTP DELETE method.
 * It's not thread-safe and mutable.
 */
public final class Delete {

    /**
     * It contains the request configuration from the previous steps.
     * We will continue to store more configuration data in this.
     */
    private final Map<ConfigKeys, Object> config;

    /**
     * Constructor.
     * @param config See {@link Delete#config}.
     */
    public Delete(final Map<ConfigKeys, Object> config) {
        this.config = config;
    }

    /**
     * Add an HTTP header.
     * @param name Header name.
     * @param value Header value.
     * @return This object.
     */
    public Delete header(final String name, final String value) {
        new Header(name, value, this.config).add();
        return this;
    }

    /**
     * Configure the HTTP body.
     * We assume it's okay to use UTF-8.
     * @param value HTTP body.
     * @return Next step.
     */
    public Body body(final String value) {
        this.config.put(
            ConfigKeys.BODY,
            value.getBytes(StandardCharsets.UTF_8)
        );
        return new Body(this.config);
    }

    /**
     * Configure the HTTP body.
     * @param value HTTP body.
     * @return Next step.
     */
    public Body body(final byte[] value) {
        this.config.put(
            ConfigKeys.BODY,
            value
        );
        return new Body(this.config);
    }

    /**
     * Send an HTTP request and obtain the response.
     * @return HTTP response.
     */
    public Response send() {
        return new ConnectionEntry(this.config).response();
    }

}
