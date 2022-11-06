/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

/**
 * It's responsible for parsing the bytes from the server.
 * It can provide status line, headers, and body in bytes.
 */
public final class ParsedBytes {

    /**
     * All bytes from the server.
     */
    private final List<Integer> bytes;

    /**
     * We will store the status line after the parse.
     */
    private final List<Integer> statusCache = new ArrayList<>(1);

    /**
     * We will store the headers after the parse.
     */
    private final List<Integer> headersCache = new ArrayList<>(1);

    /**
     * We will store the body after the parse.
     */
    private final List<Integer> bodyCache = new ArrayList<>(1);

    /**
     * Constructor.
     * @param bytes See {@link ParsedBytes#bytes}.
     */
    public ParsedBytes(final List<Integer> bytes) {
        this.bytes = bytes;
    }

    /**
     * Parse the bytes to return the status line.
     * @return Status line in bytes.
     */
    public List<Integer> statusLine() {
        if (this.statusCache.isEmpty()) {
            this.parse();
        }
        return new ArrayList<>(this.statusCache);
    }

    /**
     * Parse the bytes to return the headers.
     * @return Headers in bytes.
     */
    public List<Integer> headers() {
        if (this.headersCache.isEmpty()) {
            this.parse();
        }
        return new ArrayList<>(this.headersCache);
    }

    /**
     * Parse the bytes to return body.
     * @return Body in bytes.
     */
    public List<Integer> body() {
        if (this.bodyCache.isEmpty()) {
            this.parse();
        }
        return new ArrayList<>(this.bodyCache);
    }

    /**
     * Convert the bytes from list of integers to array of bytes.
     * @return HTTP response in primitive form.
     */
    public byte[] inBytes() {
        return ArrayUtils.toPrimitive(
            this.bytes.stream().map(
                Integer::byteValue
            ).toArray(Byte[]::new)
        );
    }

    /**
     * Parse the bytes and store the components.
     */
    @SuppressWarnings("MagicNumber")
    private void parse() {
        // [13, 10] means \r\n
        // which means we should interpret bytes after [13, 10, 13, 10] as response body.
        // Split the bytes by the first occurrence of [13, 10, 13, 10]
        int splitIndex = -1;
        for (int index = 3; index < this.bytes.size(); index = index + 1) {
            if (
                this.bytes.get(index - 3) == 13
                    && this.bytes.get(index - 2) == 10
                    && this.bytes.get(index - 1) == 13
                    && this.bytes.get(index) == 10
            ) {
                splitIndex = index;
                break;
            }
        }
        // Bytes before response body
        final List<Integer> beforeBody = new ArrayList<>(this.bytes.subList(0, splitIndex - 3));
        // Store the response body.
        this.bodyCache.addAll(this.bytes.subList(splitIndex + 1, this.bytes.size()));
        // Bytes before the first [13, 10] would be the status line.
        // The rest would be the headers.
        splitIndex = -1;
        for (int index = 1; index < beforeBody.size(); index = index + 1) {
            if (beforeBody.get(index - 1) == 13 && beforeBody.get(index) == 10) {
                splitIndex = index;
                break;
            }
        }
        // Store the status line.
        this.statusCache.addAll(beforeBody.subList(0, splitIndex - 1));
        // Store the response headers.
        this.headersCache.addAll(beforeBody.subList(splitIndex + 1, beforeBody.size()));
    }

}
