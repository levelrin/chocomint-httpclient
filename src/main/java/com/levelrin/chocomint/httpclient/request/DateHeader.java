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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.function.Consumer;

/**
 * It's for sending the date header.
 */
public final class DateHeader implements Consumer<OutputStream> {

    /**
     * It will be the value of the header.
     */
    private final Date date;

    /**
     * Secondary constructor.
     * It will use the current date.
     */
    public DateHeader() {
        this(new Date());
    }

    /**
     * Primary constructor.
     * @param date See {@link DateHeader#date}.
     */
    public DateHeader(final Date date) {
        this.date = date;
    }

    @Override
    public void accept(final OutputStream output) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss zzz",
            Locale.ENGLISH
        );
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        final String header = String.format(
            "Date: %s\r\n",
            dateFormat.format(this.date)
        );
        try {
            output.write(header.getBytes(StandardCharsets.UTF_8));
        } catch (final IOException exception) {
            throw new IllegalStateException(
                String.format(
                    "Failed to send the date header. Date: %s",
                    header
                ),
                exception
            );
        }
    }

}
