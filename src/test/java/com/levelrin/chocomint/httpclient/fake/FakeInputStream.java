/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.fake;

import java.io.InputStream;

/**
 * Tests.
 */
public final class FakeInputStream extends InputStream {

    /**
     * This is the fake data for testing.
     */
    private final byte[] data;

    /**
     * Current index of the data.
     * We will increment the index when the user reads the data.
     */
    private int currentIndex;

    /**
     * Constructor.
     * @param data See {@link FakeInputStream#data}.
     */
    public FakeInputStream(final byte[] data) {
        super();
        this.data = data.clone();
    }

    @Override
    public int read() {
        final byte currentData;
        if (this.data.length > this.currentIndex) {
            currentData = this.data[this.currentIndex];
            this.currentIndex = this.currentIndex + 1;
        } else {
            currentData = -1;
        }
        return currentData;
    }

}
