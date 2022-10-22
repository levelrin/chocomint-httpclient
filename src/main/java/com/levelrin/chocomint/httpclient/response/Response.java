/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import java.util.Map;

/**
 * It represents an HTTP response.
 */
public interface Response {

    /**
     * It contains status code and reason.
     * @return Response status.
     */
    StatusLine status();

    /**
     * Key is the header name.
     * Value is the header value.
     * @return Response headers.
     */
    Map<String, String> headers();

    /**
     * Response body.
     * @return HTTP response body.
     */
    ResponseBody body();

}
