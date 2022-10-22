/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.request;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * It's for sending the host header to the server.
 */
public final class HostHeader implements Consumer<OutputStream> {

    /**
     * It's for getting the hostname.
     */
    private final URL url;

    /**
     * Constructor.
     * @param url See {@link HostHeader#url}.
     */
    public HostHeader(final URL url) {
        this.url = url;
    }

    @Override
    public void accept(final OutputStream output) {
        try {
            output.write(
                String.format(
                    "Host: %s\r\n",
                    this.url.getHost()
                ).getBytes(StandardCharsets.UTF_8)
            );
        } catch (final IOException exception) {
            throw new IllegalStateException(
                String.format(
                    "Failed to send the host header. Host: %s",
                    this.url.getHost()
                ),
                exception
            );
        }
    }

}
