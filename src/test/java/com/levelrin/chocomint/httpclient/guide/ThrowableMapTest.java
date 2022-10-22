/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Tests.
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
@SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
final class ThrowableMapTest {

    @Test
    void shouldThrowExceptionIfKeyDoesNotExist() {
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new ThrowableMap<>(
                new HashMap<>()
            ).get(""),
            "It should throw an exception when the map does not contain the key."
        );
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallSizeMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        MatcherAssert.assertThat(
            "Should call the method from origin.",
            new ThrowableMap<>(origin).size(),
            CoreMatchers.equalTo(0)
        );
        Mockito.verify(origin).size();
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallIsEmptyMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        MatcherAssert.assertThat(
            "Should call the method from origin.",
            new ThrowableMap<>(origin).isEmpty(),
            // Mocked object always return false.
            CoreMatchers.equalTo(false)
        );
        Mockito.verify(origin).isEmpty();
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallContainsKeyMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        MatcherAssert.assertThat(
            "Should call the method from origin.",
            new ThrowableMap<>(origin).containsKey("key"),
            CoreMatchers.equalTo(false)
        );
        Mockito.verify(origin).containsKey(Mockito.anyString());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallContainsValueMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        MatcherAssert.assertThat(
            "Should call the method from origin.",
            new ThrowableMap<>(origin).containsValue("value"),
            CoreMatchers.equalTo(false)
        );
        Mockito.verify(origin).containsValue(Mockito.anyString());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallPutMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        MatcherAssert.assertThat(
            "Should call the method from origin.",
            new ThrowableMap<>(origin).put("uno", "one"),
            CoreMatchers.nullValue()
        );
        Mockito.verify(origin).put(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallRemoveMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        MatcherAssert.assertThat(
            "Should call the method from origin.",
            new ThrowableMap<>(origin).remove("apple"),
            CoreMatchers.nullValue()
        );
        Mockito.verify(origin).remove(Mockito.anyString());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallPutAllMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        final Map<String, String> checkedMap = new ThrowableMap<>(origin);
        checkedMap.putAll(new HashMap<>());
        Mockito.verify(origin).putAll(Mockito.anyMap());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallClearMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        final Map<String, String> checkedMap = new ThrowableMap<>(origin);
        checkedMap.clear();
        Mockito.verify(origin).clear();
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallKeySetMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        MatcherAssert.assertThat(
            "Should call the method from origin.",
            new ThrowableMap<>(origin).keySet().size(),
            CoreMatchers.equalTo(0)
        );
        Mockito.verify(origin).keySet();
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallValuesMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        MatcherAssert.assertThat(
            "Should call the method from origin.",
            new ThrowableMap<>(origin).values().size(),
            CoreMatchers.equalTo(0)
        );
        Mockito.verify(origin).values();
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCallEntrySetMethodFromOrigin() {
        final Map<String, String> origin = Mockito.mock(Map.class);
        MatcherAssert.assertThat(
            "Should call the method from origin.",
            new ThrowableMap<>(origin).entrySet().size(),
            CoreMatchers.equalTo(0)
        );
        Mockito.verify(origin).entrySet();
    }

}
