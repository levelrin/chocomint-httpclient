/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

/**
 * It's responsible for parsing the bytes to provide status line.
 */
public final class StatusFromBytes implements StatusLine {

    /**
     * Status line in bytes.
     */
    private final List<Integer> bytes;

    /**
     * We will store the status code after the parse.
     */
    private final List<Integer> codeCache = new ArrayList<>(1);

    /**
     * We will store the status reason after the parse.
     */
    private final List<String> reasonCache = new ArrayList<>(1);

    /**
     * We will convert the bytes into String and store.
     */
    private final List<String> textCache = new ArrayList<>(1);

    /**
     * Constructor.
     * @param bytes See {@link StatusFromBytes#bytes}.
     */
    public StatusFromBytes(final List<Integer> bytes) {
        this.bytes = bytes;
    }

    @Override
    public int code() {
        if (this.codeCache.isEmpty()) {
            this.parse();
        }
        return this.codeCache.get(0);
    }

    @Override
    public String reason() {
        if (this.reasonCache.isEmpty()) {
            this.parse();
        }
        return this.reasonCache.get(0);
    }

    /**
     * Convert status line into String.
     * @return Status line.
     */
    @Override
    public String toString() {
        if (this.textCache.isEmpty()) {
            this.parse();
        }
        return this.textCache.get(0);
    }

    /**
     * Parse the bytes and store the information.
     */
    @SuppressFBWarnings("MagicNumber")
    private void parse() {
        // Convert bytes into String.
        final String line = new String(
            ArrayUtils.toPrimitive(
                this.bytes.stream().map(
                    Integer::byteValue
                ).toArray(Byte[]::new)
            ),
            StandardCharsets.UTF_8
        );
        this.textCache.add(line);
        // Status line looks like this: HTTP/1.1 200 OK
        // We will split the text by space for parsing
        final String[] split = line.split(" ", 3);
        this.codeCache.add(Integer.parseInt(split[1]));
        this.reasonCache.add(split[2]);
    }

}
