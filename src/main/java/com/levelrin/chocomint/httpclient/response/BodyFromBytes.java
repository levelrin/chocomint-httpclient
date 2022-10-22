/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

/**
 * It's responsible for converting the HTTP body into various types.
 */
public final class BodyFromBytes extends ResponseBody {

    /**
     * Body in bytes.
     */
    private final List<Integer> bytes;

    /**
     * Constructor.
     * @param bytes See {@link BodyFromBytes#bytes}.
     */
    public BodyFromBytes(final List<Integer> bytes) {
        super();
        this.bytes = bytes;
    }

    @Override
    public byte[] inBytes() {
        return ArrayUtils.toPrimitive(
            this.bytes.stream().map(
                Integer::byteValue
            ).toArray(Byte[]::new)
        );
    }

    @Override
    public String toString() {
        return new String(this.inBytes(), StandardCharsets.UTF_8);
    }

}
