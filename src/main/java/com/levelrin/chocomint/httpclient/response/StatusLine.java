/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

/**
 * It represents the status line.
 */
public interface StatusLine {

    /**
     * Status code.
     * @return Status code.
     */
    int code();

    /**
     * Reason for the code.
     * @return Status reason.
     */
    String reason();

}
