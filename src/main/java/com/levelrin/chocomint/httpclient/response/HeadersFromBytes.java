/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.lang3.ArrayUtils;

/**
 * It's responsible for parsing the bytes to provide HTTP headers.
 */
public final class HeadersFromBytes implements Supplier<Map<String, String>> {

    /**
     * HTTP headers in bytes.
     */
    private final List<Integer> bytes;

    /**
     * Constructor.
     * @param bytes See {@link HeadersFromBytes#bytes}.
     */
    public HeadersFromBytes(final List<Integer> bytes) {
        this.bytes = bytes;
    }

    /**
     * Key - HTTP header name.
     * Value - HTTP header value.
     * @return HTTP headers.
     */
    @Override
    public Map<String, String> get() {
        // Convert bytes into String.
        final String text = new String(
            ArrayUtils.toPrimitive(
                this.bytes.stream().map(
                    Integer::byteValue
                ).toArray(Byte[]::new)
            ),
            StandardCharsets.UTF_8
        );
        final Map<String, String> headers = new HashMap<>();
        final String[] lines = text.split("\r\n");
        for (final String line: lines) {
            final String[] split = line.split(":");
            headers.put(split[0].trim(), split[1].trim());
        }
        return headers;
    }

}
