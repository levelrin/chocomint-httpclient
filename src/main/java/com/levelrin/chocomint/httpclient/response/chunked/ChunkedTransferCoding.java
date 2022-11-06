/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response.chunked;

import com.levelrin.chocomint.httpclient.response.ResponseBody;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * It's responsible for handling the chunked transfer coding.
 * In other words, it handles the body if the response header "Transfer-Encoding: chunked" is used.
 * As a result, it will return the response body that all chunks are combined.
 * See <a href="https://www.rfc-editor.org/rfc/rfc2616#section-3.6.1">this</a> for details.
 */
public final class ChunkedTransferCoding extends ResponseBody {

    /**
     * HTTP response header.
     */
    private final Map<String, String> headers;

    /**
     * HTTP response body before handling the chunked transfer coding.
     */
    private final ResponseBody origin;

    /**
     * Constructor.
     * @param headers See {@link ChunkedTransferCoding#headers}.
     * @param origin See {@link ChunkedTransferCoding#origin}.
     */
    public ChunkedTransferCoding(final Map<String, String> headers, final ResponseBody origin) {
        super();
        this.headers = headers;
        this.origin = origin;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public byte[] inBytes() {
        byte[] result = new byte[0];
        // fixme: HTTP header name is case-insensitive.
        // fixme: we should properly parse the header value.
        // For example, multiple values can be given.
        if (
            this.headers.containsKey("Transfer-Encoding")
            && this.headers.get("Transfer-Encoding").contains("chunked")
        ) {
            int size;
            byte[] rest = this.origin.inBytes();
            while (true) {
                final ChunkSizeLine sizeLine = new ChunkSizeLine(rest);
                size = sizeLine.size();
                rest = sizeLine.rest();
                if (size == 0) {
                    break;
                } else {
                    final ChunkData chunkData = new ChunkData(size, rest);
                    final byte[] data = chunkData.data();
                    rest = chunkData.rest();
                    final ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    try {
                        stream.write(result);
                        stream.write(data);
                        result = stream.toByteArray();
                    } catch (final IOException exception) {
                        throw new IllegalStateException("Failed to append chunk data.", exception);
                    }
                }
            }
        } else {
            result = this.origin.inBytes();
        }
        return result;
    }

    @Override
    public String toString() {
        return new String(
            this.inBytes(),
            StandardCharsets.UTF_8
        );
    }

}
