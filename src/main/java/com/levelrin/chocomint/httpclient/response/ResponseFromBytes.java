/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import com.levelrin.chocomint.httpclient.response.chunked.ChunkedTransferCoding;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * It's responsible for parsing the bytes to provide HTTP response.
 */
public final class ResponseFromBytes implements Response {

    /**
     * It's for parsing the HTTP response in bytes
     * into three parts: status line, headers, and body.
     * The return type of each part is still in bytes.
     * That means more parsing is needed for end users.
     */
    private final ParsedBytes bytes;

    /**
     * Secondary constructor.
     * @param bytes HTTP response in bytes.
     */
    public ResponseFromBytes(final List<Integer> bytes) {
        this(new ParsedBytes(bytes));
    }

    /**
     * Primary constructor.
     * @param bytes See {@link ResponseFromBytes#bytes}.
     */
    public ResponseFromBytes(final ParsedBytes bytes) {
        this.bytes = bytes;
    }

    @Override
    public StatusLine status() {
        return new StatusFromBytes(
            this.bytes.statusLine()
        );
    }

    @Override
    public Map<String, String> headers() {
        // fixme: it's not efficient if both headers() and body() are used
        // because body() also calls headers() internally.
        return new HeadersFromBytes(
            this.bytes.headers()
        ).get();
    }

    @Override
    public ResponseBody body() {
        return new ChunkedTransferCoding(
            this.headers(),
            new BodyFromBytes(
                this.bytes.body()
            )
        );
    }

    /**
     * Convert bytes into String.
     * @return HTTP response.
     */
    @Override
    public String toString() {
        return new String(
            this.bytes.inBytes(),
            StandardCharsets.UTF_8
        );
    }

}
