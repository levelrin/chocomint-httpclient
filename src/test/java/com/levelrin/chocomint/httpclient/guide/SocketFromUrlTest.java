/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import java.net.Socket;
import java.net.URL;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
@SuppressWarnings("MagicNumber")
final class SocketFromUrlTest {

    @Test
    void shouldShowInputUrlInTheErrorMessage() {
        final RuntimeException exception = Assertions.assertThrows(
            IllegalStateException.class,
            () -> {
                try (
                    Socket socket = new SocketFromUrl(
                        new URL("ftp://localhost"), 80
                    ).create()
                ) {
                    Assertions.fail(
                        String.format(
                            "We should not reach here because exception is expected. %s",
                            socket
                        )
                    );
                }
            },
            "Should throw an exception if the scheme is not supported."
        );
        MatcherAssert.assertThat(
            "Exception message should include the input URL.",
            exception.getMessage(),
            CoreMatchers.containsString("ftp://localhost")
        );
    }

}
