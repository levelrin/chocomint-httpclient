/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response.chunked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * It's responsible for parsing the chunk to get the chunk data.
 * After the parse, it will return the rest of chunk excluding the part
 * where it's parsed by this, so we can continue parsing the chunk in the next step.
 */
public final class ChunkData {

    /**
     * Chunk size.
     */
    private final int size;

    /**
     * We will parse this.
     * We assume it begins with chunk data.
     */
    private final byte[] chunk;

    /**
     * We will store the chunk data after the parse.
     */
    private final List<byte[]> dataCache = new ArrayList<>(1);

    /**
     * We will store the rest of the chunk after the parse.
     */
    private final List<byte[]> restCache = new ArrayList<>(1);

    /**
     * Constructor.
     * @param size See {@link ChunkData#size}.
     * @param chunk See {@link ChunkData#chunk}.
     */
    public ChunkData(final int size, final byte[] chunk) {
        this.size = size;
        this.chunk = chunk.clone();
    }

    /**
     * Parse the chunk and return the data.
     * @return Chunk data.
     */
    public byte[] data() {
        if (this.dataCache.isEmpty()) {
            this.parse();
        }
        return this.dataCache.get(0);
    }

    /**
     * Rest of the chunk bytes after the parse.
     * Note that the CRLF after the chunk data will be excluded.
     * @return It excludes the part where it's parsed by this.
     */
    public byte[] rest() {
        if (this.restCache.isEmpty()) {
            this.parse();
        }
        return this.restCache.get(0);
    }

    /**
     * Parse the chunk bytes and store the data and rest of the bytes.
     */
    @SuppressWarnings("MagicNumber")
    private void parse() {
        this.dataCache.add(
            Arrays.copyOfRange(this.chunk, 0, this.size)
        );
        this.restCache.add(
            // Exclude CRLF after the data.
            Arrays.copyOfRange(this.chunk, this.size + 2, this.chunk.length)
        );
    }

}
