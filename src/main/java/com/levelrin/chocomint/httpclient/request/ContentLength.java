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
 * It's for sending the Content-Length header if the request body exists.
 */
public final class ContentLength implements Consumer<OutputStream> {

    /**
     * HTTP request body.
     */
    private final byte[] body;

    /**
     * Constructor.
     * @param body See {@link ContentLength#body}.
     */
    public ContentLength(final byte[] body) {
        this.body = body.clone();
    }

    @Override
    public void accept(final OutputStream output) {
        try {
            if (this.body.length > 0) {
                output.write(
                    String.format(
                        "Content-Length: %d\r\n",
                        this.body.length
                    ).getBytes(StandardCharsets.UTF_8)
                );
            }
        } catch (final IOException exception) {
            throw new IllegalStateException(
                String.format(
                    "Failed to send the Content-Length header. Content-Length: %s",
                    this.body.length
                ),
                exception
            );
        }
    }

}
