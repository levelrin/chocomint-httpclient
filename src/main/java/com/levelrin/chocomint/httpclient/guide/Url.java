/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import com.levelrin.chocomint.httpclient.guide.method.Connect;
import com.levelrin.chocomint.httpclient.guide.method.Delete;
import com.levelrin.chocomint.httpclient.guide.method.Get;
import com.levelrin.chocomint.httpclient.guide.method.Head;
import com.levelrin.chocomint.httpclient.guide.method.Methods;
import com.levelrin.chocomint.httpclient.guide.method.Options;
import com.levelrin.chocomint.httpclient.guide.method.Patch;
import com.levelrin.chocomint.httpclient.guide.method.Post;
import com.levelrin.chocomint.httpclient.guide.method.Put;
import com.levelrin.chocomint.httpclient.guide.method.Trace;
import java.util.HashMap;
import java.util.Map;

/**
 * It represents the HTTP request URL.
 * It's not thread-safe and mutable.
 */
@SuppressWarnings("ClassDataAbstractionCoupling")
public final class Url {

    /**
     * It contains the request configuration from the previous steps.
     * We will continue to store more configuration data in this.
     */
    private final Map<ConfigKeys, Object> config;

    /**
     * Constructor.
     * @param config See {@link Url#config}.
     */
    public Url(final Map<ConfigKeys, Object> config) {
        this.config = config;
    }

    /**
     * Configure the HTTP method to GET.
     * @return Next configuration.
     */
    public Get get() {
        this.config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        this.config.put(ConfigKeys.METHOD, Methods.GET);
        return new Get(this.config);
    }

    /**
     * Configure the HTTP method to HEAD.
     * @return Next configuration.
     */
    public Head head() {
        this.config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        this.config.put(ConfigKeys.METHOD, Methods.HEAD);
        return new Head(this.config);
    }

    /**
     * Configure the HTTP method to POST.
     * @return Next configuration.
     */
    public Post post() {
        this.config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        this.config.put(ConfigKeys.METHOD, Methods.POST);
        return new Post(this.config);
    }

    /**
     * Configure the HTTP method to PUT.
     * @return Next configuration.
     */
    public Put put() {
        this.config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        this.config.put(ConfigKeys.METHOD, Methods.PUT);
        return new Put(this.config);
    }

    /**
     * Configure the HTTP method to DELETE.
     * @return Next configuration.
     */
    public Delete delete() {
        this.config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        this.config.put(ConfigKeys.METHOD, Methods.DELETE);
        return new Delete(this.config);
    }

    /**
     * Configure the HTTP method to CONNECT.
     * @return Next configuration.
     */
    public Connect connect() {
        this.config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        this.config.put(ConfigKeys.METHOD, Methods.CONNECT);
        return new Connect(this.config);
    }

    /**
     * Configure the HTTP method to OPTIONS.
     * @return Next configuration.
     */
    public Options options() {
        this.config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        this.config.put(ConfigKeys.METHOD, Methods.OPTIONS);
        return new Options(this.config);
    }

    /**
     * Configure the HTTP method to TRACE.
     * @return Next configuration.
     */
    public Trace trace() {
        this.config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        this.config.put(ConfigKeys.METHOD, Methods.TRACE);
        return new Trace(this.config);
    }

    /**
     * Configure the HTTP method to PATCH.
     * @return Next configuration.
     */
    public Patch patch() {
        this.config.put(ConfigKeys.HEADERS, new HashMap<String, String>());
        this.config.put(ConfigKeys.METHOD, Methods.PATCH);
        return new Patch(this.config);
    }

}
