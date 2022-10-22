/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.request;

import com.levelrin.chocomint.httpclient.fake.FakeOutputStream;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
@SuppressFBWarnings("OS_OPEN_STREAM")
final class DateHeaderTest {

    @Test
    void dateShouldBeFormattedCorrectly() {
        final AtomicReference<String> actual = new AtomicReference<>();
        try (FakeOutputStream output = new FakeOutputStream().with(data -> {
            actual.set(new String(data, StandardCharsets.UTF_8));
        })) {
            final GregorianCalendar calendar = new GregorianCalendar(
                2022,
                Calendar.AUGUST,
                15,
                4,
                33,
                33
            );
            calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
            new DateHeader(calendar.getTime()).accept(output);
        } catch (final IOException ex) {
            throw new IllegalStateException(
                "Maybe the unit test is trying to access the real IO.",
                ex
            );
        }
        MatcherAssert.assertThat(
            "Should add the Date header in the correct format.",
            actual.get(),
            CoreMatchers.equalTo("Date: Mon, 15 Aug 2022 04:33:33 GMT\r\n")
        );
    }

}
