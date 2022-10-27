/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import com.levelrin.chocomint.httpclient.guide.method.Methods;
import com.levelrin.chocomint.httpclient.response.BytesFromServer;
import com.levelrin.chocomint.httpclient.response.Response;
import com.levelrin.chocomint.httpclient.request.ConnectionHeader;
import com.levelrin.chocomint.httpclient.request.ContentLength;
import com.levelrin.chocomint.httpclient.request.DateHeader;
import com.levelrin.chocomint.httpclient.request.HostHeader;
import com.levelrin.chocomint.httpclient.request.RequestBody;
import com.levelrin.chocomint.httpclient.request.RequestHeaders;
import com.levelrin.chocomint.httpclient.request.RequestLine;
import com.levelrin.chocomint.httpclient.request.UserAgent;
import com.levelrin.chocomint.httpclient.response.ResponseFromBytes;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

/**
 * It's the entrypoint of making an HTTP connection.
 */
@SuppressWarnings("ClassDataAbstractionCoupling")
public final class ConnectionEntry {

    /**
     * It contains the request configuration.
     */
    private final Map<ConfigKeys, Object> config;

    /**
     * Constructor.
     * @param config See {@link ConnectionEntry#config}.
     */
    public ConnectionEntry(final Map<ConfigKeys, Object> config) {
        this.config = config;
    }

    /**
     * Send an HTTP request and return the response.
     * @return HTTP response.
     */
    @SuppressWarnings("unchecked")
    public Response response() {
        try {
            final Map<ConfigKeys, Object> checkedConfig = new ThrowableMap<>(this.config);
            final String version = "0.1.0";
            final URL url = (URL) checkedConfig.get(ConfigKeys.URL);
            final int port = (int) checkedConfig.get(ConfigKeys.PORT);
            final String path = (String) checkedConfig.get(ConfigKeys.PATH);
            final String method = ((Methods) checkedConfig.get(ConfigKeys.METHOD)).name();
            final Map<String, String> headers = (Map<String, String>) checkedConfig.get(
                ConfigKeys.HEADERS
            );
            final byte[] body = Optional.ofNullable(
                (byte[]) this.config.get(ConfigKeys.BODY)
            ).orElse(new byte[0]);
            try (
                Socket socket = new SocketFromUrl(url, port).create();
                BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
                BufferedInputStream input = new BufferedInputStream(socket.getInputStream())
            ) {
                new RequestLine(
                    method,
                    path
                ).andThen(
                    new RequestHeaders(headers)
                ).andThen(
                    new HostHeader(url)
                ).andThen(
                    new ContentLength(body)
                ).andThen(
                    new DateHeader()
                ).andThen(
                    new UserAgent(version, headers)
                ).andThen(
                    new ConnectionHeader()
                ).andThen(
                    new RequestBody(body)
                ).accept(output);
                output.flush();
                return new ResponseFromBytes(new BytesFromServer(input).get());
            }
        } catch (final IOException exception) {
            throw new IllegalStateException("Connection failure.", exception);
        }
    }

}
