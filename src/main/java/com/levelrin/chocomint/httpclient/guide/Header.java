/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import java.util.Locale;
import java.util.Map;

/**
 * It's responsible for adding an HTTP header to the configuration.
 * It's not thread-safe.
 */
public final class Header {

    /**
     * HTTP header name.
     */
    private final String name;

    /**
     * HTTP header value.
     */
    private final String value;

    /**
     * We will add the header into this.
     */
    private final Map<ConfigKeys, Object> config;

    /**
     * Constructor.
     * @param name See {@link Header#name}.
     * @param value See {@link Header#value}.
     * @param config See {@link Header#config}.
     */
    public Header(final String name, final String value, final Map<ConfigKeys, Object> config) {
        this.name = name;
        this.value = value;
        this.config = config;
    }

    /**
     * Add the HTTP header into the configuration.
     */
    @SuppressWarnings("unchecked")
    public void add() {
        this.validate();
        final Map<String, String> headers = (Map<String, String>) this.config.get(
            ConfigKeys.HEADERS
        );
        if (headers.containsKey(this.name)) {
            headers.put(
                this.name,
                String.format(
                    "%s, %s",
                    headers.get(this.name),
                    this.value
                )
            );
        } else {
            headers.put(this.name, this.value);
        }
    }

    /**
     * Check if the header is forbidden.
     */
    @SuppressWarnings({"CyclomaticComplexity", "PMD.CyclomaticComplexity"})
    private void validate() {
        boolean issue = false;
        final String header = this.name.toUpperCase(Locale.ROOT);
        switch (header) {
            case "ACCEPT-CHARSET":
            case "ACCEPT-ENCODING":
            case "ACCESS-CONTROL-REQUEST-HEADERS":
            case "ACCESS-CONTROL-REQUEST-METHOD":
            case "CONNECTION":
            case "CONTENT-LENGTH":
            case "COOKIE":
            case "DATE":
            case "DNT":
            case "EXPECT":
            case "FEATURE-POLICY":
            case "HOST":
            case "KEEP-ALIVE":
            case "ORIGIN":
            case "REFERER":
            case "TE":
            case "TRAILER":
            case "TRANSFER-ENCODING":
            case "UPGRADE":
            case "VIA":
                issue = true;
                break;
            default:
                // No issue found.
        }
        if (header.startsWith("PROXY-") || header.startsWith("SEC-")) {
            issue = true;
        }
        if (issue) {
            throw new IllegalArgumentException(
                String.format(
                    "Forbidden header name is used: %s%n"
                    + "Please read %s",
                    this.name,
                    "https://developer.mozilla.org/en-US/docs/Glossary/Forbidden_header_name"
                )
            );
        }
    }

}
