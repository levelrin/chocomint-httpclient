/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

/**
 * It represents HTTP response body.
 */
public abstract class ResponseBody {

    /**
     * Return the HTTP body in byte array.
     * @return Raw HTTP body.
     */
    public abstract byte[] inBytes();

    /**
     * Return the HTTP body in String.
     * @return HTTP body in String.
     */
    @Override
    public abstract String toString();

}
