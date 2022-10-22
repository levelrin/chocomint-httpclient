/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * It represents an HTTP client.
 * It's thread-safe by the immutable structure.
 */
public final class Http {

    /**
     * It contains the request configuration.
     * We will pass the copy of this to the next step.
     */
    private final Map<ConfigKeys, Object> config;

    /**
     * Secondary constructor.
     * It will use an empty {@link HashMap}.
     */
    public Http() {
        this(new HashMap<>());
    }

    /**
     * Primary constructor.
     * @param config See {@link Http#config}.
     */
    public Http(final Map<ConfigKeys, Object> config) {
        this.config = config;
    }

    /**
     * Configure the request URL.
     * @param input Request URL.
     * @return Next configuration.
     */
    public Url url(final String input) {
        try {
            final Map<ConfigKeys, Object> configCopy = new HashMap<>(this.config);
            final URL urlObj = new URL(input);
            configCopy.put(ConfigKeys.URL, urlObj);
            configCopy.put(ConfigKeys.PORT, this.port(urlObj));
            configCopy.put(ConfigKeys.PATH, this.path(urlObj));
            return new Url(configCopy);
        } catch (final MalformedURLException exception) {
            throw new IllegalArgumentException(
                String.format(
                    "The URL is incorrect.%n"
                    + "Please make sure it includes the scheme.%n"
                    + "Your input: %s%n",
                    input
                ),
                exception
            );
        }
    }

    /**
     * Read the URL and get the port.
     * @param url We will get the port number from this.
     * @return Port number.
     */
    private int port(final URL url) {
        int result = url.getPort();
        if (result == -1) {
            result = url.getDefaultPort();
        }
        return result;
    }

    /**
     * Read the URL and get the path.
     * @param url We will get the path from this.
     * @return Path.
     */
    private String path(final URL url) {
        final String result;
        final String rawPath = url.getPath();
        if (rawPath.isEmpty()) {
            result = "/";
        } else {
            result = rawPath;
        }
        return result;
    }

}
