/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import com.levelrin.chocomint.httpclient.response.Response;
import java.util.Map;

/**
 * It represents HTTP body.
 * It's not thread-safe and mutable.
 */
public final class Body {

    /**
     * It contains the request configuration from the previous steps.
     */
    private final Map<ConfigKeys, Object> config;

    /**
     * Constructor.
     * @param config See {@link Body#config}.
     */
    public Body(final Map<ConfigKeys, Object> config) {
        this.config = config;
    }

    /**
     * Send an HTTP request and obtain the response.
     * @return HTTP response.
     */
    public Response send() {
        return new ConnectionEntry(this.config).response();
    }

}
