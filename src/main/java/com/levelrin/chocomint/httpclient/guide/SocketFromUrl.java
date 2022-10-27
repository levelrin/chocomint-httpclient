/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import javax.net.ssl.SSLSocketFactory;

/**
 * It's responsible for creating a socket object from the URL scheme.
 */
public final class SocketFromUrl {

    /**
     * The scheme determines the kind of socket.
     */
    private final URL url;

    /**
     * It's for creating a socket object.
     */
    private final int port;

    /**
     * Constructor.
     * @param url See {@link SocketFromUrl#url}.
     * @param port See {@link SocketFromUrl#port}.
     */
    public SocketFromUrl(final URL url, final int port) {
        this.url = url;
        this.port = port;
    }

    /**
     * A factory method to create a socket object.
     * @return Plain socket or SSL socket.
     * @throws IOException It's from the checked exception from the socket initialization.
     */
    public Socket create() throws IOException {
        final String scheme = this.url.getProtocol();
        final Socket result;
        if ("http".equals(scheme)) {
            result = new Socket(this.url.getHost(), this.port);
        } else if ("https".equals(scheme)) {
            result = SSLSocketFactory
                .getDefault()
                .createSocket(this.url.getHost(), this.port);
        } else {
            throw new IllegalStateException(
                String.format(
                    "The URL scheme must be HTTP or HTTPS. Your URL: %s",
                    this.url
                )
            );
        }
        return result;
    }

}
