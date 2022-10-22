/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.fake;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

/**
 * It's for testing.
 * It's not thread-safe.
 */
public final class FakeOutputStream extends OutputStream {

    /**
     * It will execute the consumers when {@link FakeOutputStream#write(byte[])} is called.
     */
    private final List<Consumer<byte[]>> writeEvents = new ArrayList<>();

    @Override
    public void write(final int data) {
        throw new UnsupportedOperationException("Unexpected method call.");
    }

    @Override
    public void write(final byte @NotNull [] data) {
        for (final Consumer<byte[]> event: this.writeEvents) {
            event.accept(data);
        }
    }

    /**
     * Register a consumer that will be executed on {@link FakeOutputStream#write(byte[])}.
     * @param writeEvent It will be executed on write.
     * @return The current object.
     */
    public FakeOutputStream with(final Consumer<byte[]> writeEvent) {
        this.writeEvents.add(writeEvent);
        return this;
    }

}
