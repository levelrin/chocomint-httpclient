/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response.chunked;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * It's responsible for parsing the chunk to get the chunk size.
 * The chunk extensions will be ignored because it's not used that much.
 * Let us know if you need extensions.
 * After the parse, it will return the rest of chunk excluding the part
 * where it's parsed by this, so we can continue parsing the chunk in the next step.
 * Note that the rest of bytes can be empty if the chunk size is 0.
 * Currently, we do not support the trailer.
 * Let us know if you need the trailer.
 */
public final class ChunkSizeLine {

    /**
     * We will parse this.
     * We assume it begins with the chunk size.
     */
    private final byte[] chunk;

    /**
     * We will store the chunk size after the parse.
     */
    private final List<Integer> sizeCache = new ArrayList<>(1);

    /**
     * We will store the rest of the chunk after the parse.
     */
    private final List<byte[]> restCache = new ArrayList<>(1);

    /**
     * Constructor.
     * @param chunk See {@link ChunkSizeLine#chunk}.
     */
    public ChunkSizeLine(final byte[] chunk) {
        this.chunk = chunk.clone();
    }

    /**
     * Parse the chunk bytes to get the chunk size.
     * @return Chunk size.
     */
    public int size() {
        if (this.sizeCache.isEmpty()) {
            this.parse();
        }
        return this.sizeCache.get(0);
    }

    /**
     * Rest of the chunk bytes after the parse.
     * Note that the empty array can be returned if the size is 0.
     * @return It excludes the part where it's parsed by this.
     */
    public byte[] rest() {
        if (this.restCache.isEmpty()) {
            this.parse();
        }
        return this.restCache.get(0);
    }

    /**
     * Parse the chunk bytes and store the size and rest of the bytes.
     */
    @SuppressWarnings("MagicNumber")
    private void parse() {
        // We will reassign this value to hold the chunk bytes
        // from the beginning to first CRLF (exclusive), which will have the chunk size
        // and possibly chunk extensions.
        byte[] firstLine = new byte[0];
        // Find the first line and store the rest of bytes.
        for (int index = 2; index < this.chunk.length; index = index + 1) {
            // [13, 10] means \r\n
            if (this.chunk[index - 1] == 13 && this.chunk[index] == 10) {
                firstLine = Arrays.copyOfRange(this.chunk, 0, index - 1);
                this.restCache.add(
                    Arrays.copyOfRange(
                        this.chunk,
                        index + 1,
                        this.chunk.length
                    )
                );
                break;
            }
        }
        // We may reassign this value if there are extensions.
        byte[] sizeBytes = firstLine;
        // Check if there are extensions.
        for (int index = 0; index < firstLine.length; index = index + 1) {
            // 59 means ';' which tells us that the rest will be extensions.
            if (firstLine[index] == 59) {
                sizeBytes = Arrays.copyOfRange(firstLine, 0, index);
                break;
            }
        }
        final int chunkSize = Integer.parseInt(
            new String(sizeBytes, StandardCharsets.UTF_8),
            16
        );
        this.sizeCache.add(chunkSize);
        // We will store the empty array as the rest of bytes
        // to indicate that it's time to end parsing chunks.
        // The trailer will be ignored if exists.
        if (chunkSize == 0) {
            this.restCache.clear();
            this.restCache.add(new byte[0]);
        }
    }

}
