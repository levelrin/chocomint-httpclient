/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.response;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * It's for getting all the bytes from the server.
 */
public final class BytesFromServer implements Supplier<List<Integer>> {

    /**
     * For getting the response from the server.
     */
    private final InputStream input;

    /**
     * Constructor.
     * @param input See {@link BytesFromServer#input}.
     */
    public BytesFromServer(final InputStream input) {
        this.input = input;
    }

    @Override
    @SuppressWarnings("PMD.AssignmentInOperand")
    public List<Integer> get() {
        try {
            final List<Integer> bytes = new ArrayList<>();
            int next;
            while ((next = this.input.read()) != -1) {
                bytes.add(next);
            }
            return bytes;
        } catch (final IOException ex) {
            throw new IllegalStateException("Failed to read bytes from the socket.", ex);
        }
    }

}
