/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import com.levelrin.chocomint.httpclient.guide.method.Methods;

/**
 * We will store all the configurations in a map to send an HTTP request.
 * It contains available keys.
 */
public enum ConfigKeys {

    /**
     * The value should be {@link java.net.URL} object.
     */
    URL,

    /**
     * The value should be int.
     */
    PORT,

    /**
     * HTTP request path.
     * Ex: / or /some/path
     * The value should be String.
     */
    PATH,

    /**
     * HTTP method.
     * The value should be {@link Methods}.
     */
    METHOD,

    /**
     * HTTP headers.
     * The value should be {@link java.util.Map}.
     * Both key and value should be String.
     */
    HEADERS,

    /**
     * HTTP body.
     * The value should be byte[].
     * The value may not exist.
     */
    BODY

}
