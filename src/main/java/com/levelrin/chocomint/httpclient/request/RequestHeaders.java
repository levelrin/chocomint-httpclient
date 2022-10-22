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
import java.util.Map;
import java.util.function.Consumer;

/**
 * It's for sending the HTTP headers to the server.
 */
public final class RequestHeaders implements Consumer<OutputStream> {

    /**
     * HTTP headers.
     */
    private final Map<String, String> headers;

    /**
     * Constructor.
     * @param headers See {@link RequestHeaders#headers}.
     */
    public RequestHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public void accept(final OutputStream output) {
        for (final Map.Entry<String, String> entry: this.headers.entrySet()) {
            final String header = String.format(
                "%s: %s\r\n",
                entry.getKey(),
                entry.getValue()
            );
            try {
                output.write(
                    header.getBytes(StandardCharsets.UTF_8)
                );
            } catch (final IOException exception) {
                throw new IllegalStateException(
                    String.format(
                        "Failed to send the request header: %s",
                        header
                    ),
                    exception
                );
            }
        }
    }

}
