/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;

/**
 * It's responsible for parsing the bytes to provide HTTP response.
 */
public final class ResponseFromBytes implements Response {

    /**
     * HTTP response in bytes.
     */
    private final List<Integer> bytes;

    /**
     * Constructor.
     * @param bytes See {@link ResponseFromBytes#bytes}.
     */
    public ResponseFromBytes(final List<Integer> bytes) {
        this.bytes = bytes;
    }

    @Override
    public StatusLine status() {
        return new StatusFromBytes(
            new ParsedBytes(this.bytes).statusLine()
        );
    }

    @Override
    public Map<String, String> headers() {
        return new HeadersFromBytes(
            new ParsedBytes(this.bytes).headers()
        ).get();
    }

    @Override
    public ResponseBody body() {
        return new BodyFromBytes(
            new ParsedBytes(this.bytes).body()
        );
    }

    /**
     * Convert bytes into String.
     * @return HTTP response.
     */
    @Override
    public String toString() {
        return new String(
            ArrayUtils.toPrimitive(
                this.bytes.stream().map(
                    Integer::byteValue
                ).toArray(Byte[]::new)
            ),
            StandardCharsets.UTF_8
        );
    }

}
