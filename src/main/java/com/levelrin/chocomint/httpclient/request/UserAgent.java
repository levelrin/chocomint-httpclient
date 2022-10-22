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
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * It's for sending the user agent header.
 */
public final class UserAgent implements Consumer<OutputStream> {

    /**
     * The version of chocomint-httpclient.
     * It will be used when the default user agent is used.
     */
    private final String version;

    /**
     * HTTP headers.
     * We will send the default user agent value
     * if it's not specified by this map.
     */
    private final Map<String, String> headers;

    /**
     * Constructor.
     * @param version See {@link UserAgent#version}.
     * @param headers See {@link UserAgent#headers}.
     */
    public UserAgent(final String version, final Map<String, String> headers) {
        this.version = version;
        this.headers = headers;
    }

    @Override
    public void accept(final OutputStream output) {
        final Set<String> names = new HashSet<>();
        for (final String name : this.headers.keySet()) {
            names.add(name.toUpperCase(Locale.ROOT));
        }
        if (!names.contains("USER-AGENT")) {
            final String header = String.format(
                "User-Agent: chocomint-httpclient/%s\r\n",
                this.version
            );
            try {
                output.write(header.getBytes(StandardCharsets.UTF_8));
            } catch (final IOException exception) {
                throw new IllegalStateException(
                    String.format(
                        "Failed to send the user agent header header. %s",
                        header
                    ),
                    exception
                );
            }
        }
    }

}
