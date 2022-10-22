/*
 * Copyright (c) 2022 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/chocomint-httpclient/blob/main/LICENSE
 */

package com.levelrin.chocomint.httpclient.guide;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * It throws an exception when the value does not exist.
 * @param <K> Key
 * @param <V> Value
 */
public final class ThrowableMap<K, V> implements Map<K, V> {

    /**
     * We will throw an exception if this does not contain the value.
     */
    private final Map<K, V> origin;

    /**
     * Constructor.
     * @param origin See {@link ThrowableMap#origin}.
     */
    public ThrowableMap(final Map<K, V> origin) {
        this.origin = origin;
    }

    @Override
    public int size() {
        return this.origin.size();
    }

    @Override
    public boolean isEmpty() {
        return this.origin.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return this.origin.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return this.origin.containsValue(value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(final Object key) {
        if (!this.origin.containsKey((K) key)) {
            throw new IllegalStateException(
                String.format(
                    "There is a possibility that some configuration is missing.%n"
                    + "The configuration did not have the key '%s'.",
                    key
                )
            );
        }
        return this.origin.get(key);
    }

    @Nullable
    @Override
    public V put(final K key, final V value) {
        return this.origin.put(key, value);
    }

    @Override
    public V remove(final Object key) {
        return this.origin.remove(key);
    }

    @Override
    public void putAll(final @NotNull Map<? extends K, ? extends V> map) {
        this.origin.putAll(map);
    }

    @Override
    public void clear() {
        this.origin.clear();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return this.origin.keySet();
    }

    @NotNull
    @Override
    public Collection<V> values() {
        return this.origin.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.origin.entrySet();
    }

}
