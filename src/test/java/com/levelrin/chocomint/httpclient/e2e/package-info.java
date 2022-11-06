/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

/**
 * This package contains test files for end-to-end testing.
 * In other words, it's about testing from the user's perspective.
 *
 * We use Spark framework to run the web server for testing.
 *
 * Since the Gradle 'test' task runs test files in parallel, we need to make sure
 * that each web server uses unique port.
 * We start using the port 4567 (Spark's default port) and increment the port number
 * as the number of test files grow.
 * Please use this file to keep track of the port numbers.
 *
 * So far, we've used the port 4567-4569.
 * Next available port: 4570.
 */
package com.levelrin.chocomint.httpclient.e2e;
