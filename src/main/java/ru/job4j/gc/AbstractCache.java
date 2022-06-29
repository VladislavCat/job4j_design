package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.putIfAbsent(key, new SoftReference<>(value));
    }

    public V get(K key) {
        var value = cache.getOrDefault(key, new SoftReference<>(null));
        if (value.get() == null) {
            put(key, load(key));
        }
        var valueRsl = cache.get(key).get();
        return valueRsl;
    }

    protected abstract V load(K key);
}
