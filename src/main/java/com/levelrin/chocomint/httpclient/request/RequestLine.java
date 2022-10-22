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
 * It's for sending the HTTP request line.
 */
public final class RequestLine implements Consumer<OutputStream> {

    /**
     * HTTP method.
     */
    private final String method;

    /**
     * HTTP request path.
     */
    private final String path;

    /**
     * Constructor.
     * @param method See {@link RequestLine#method}.
     * @param path See {@link RequestLine#path}.
     */
    public RequestLine(final String method, final String path) {
        this.method = method;
        this.path = path;
    }

    @Override
    public void accept(final OutputStream output) {
        final String line = String.format(
            "%s %s HTTP/1.1\r\n",
            this.method,
            this.path
        );
        try {
            output.write(line.getBytes(StandardCharsets.UTF_8));
        } catch (final IOException exception) {
            throw new IllegalStateException(
                String.format("Failed to send the request line: %s", line),
                exception
            );
        }
    }

}
