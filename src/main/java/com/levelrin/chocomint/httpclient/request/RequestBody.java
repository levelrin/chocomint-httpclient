/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.request;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * It's for sending the request body to the server.
 */
public final class RequestBody implements Consumer<OutputStream> {

    /**
     * Request body in bytes.
     */
    private final byte[] body;

    /**
     * Constructor.
     * @param body See {@link RequestBody#body}.
     */
    public RequestBody(final byte[] body) {
        this.body = body.clone();
    }

    @Override
    public void accept(final OutputStream output) {
        try {
            // It assumes \r\n is already written by the end of headers.
            // Therefore, only one more \r\n is needed before the body.
            output.write("\r\n".getBytes(StandardCharsets.UTF_8));
            if (this.body.length > 0) {
                output.write(this.body);
            }
        } catch (final IOException exception) {
            throw new IllegalStateException(
                "Failed to send the request body.",
                exception
            );
        }
    }

}
