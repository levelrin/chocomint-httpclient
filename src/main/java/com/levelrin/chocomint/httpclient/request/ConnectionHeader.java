/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.request;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * It's for sending the connection header to the server.
 */
public final class ConnectionHeader implements Consumer<OutputStream> {

    @Override
    public void accept(final OutputStream output) {
        try {
            output.write(
                "Connection: close\r\n".getBytes(StandardCharsets.UTF_8)
            );
        } catch (final IOException exception) {
            throw new IllegalStateException(
                "Failed to send the connection header. Connection: close",
                exception
            );
        }
    }

}
