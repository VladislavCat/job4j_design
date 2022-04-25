package ru.job4j.collection.hashmap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int i = indexFor(hash(key.hashCode())) & (table.length - 1);
        boolean rsl = i >= 0 && i < table.length;
        if (rsl) {
            if (table[i] == null) {
                count++;
            }
            table[i] = new MapEntry<>(key, value);
            modCount++;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        V value = null;
        for (MapEntry<K, V> elem : table) {
            if (elem != null && key.equals(elem.key)) {
                value = elem.value;
                break;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && key.equals(table[i].key)) {
                table[i] = null;
                rsl = true;
                count--;
                modCount--;
                break;
            }
        }
        return rsl;
    }


    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return (table.length - 1) ^ hash;
    }

    private void expand() {
        int volume = capacity == 0 ? 16 : capacity * 2;
        MapEntry<K, V>[] tableExpand = new MapEntry[volume];
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null) {
                tableExpand[indexFor(hash(kvMapEntry.hashCode()))
                        & (volume - 1)] = kvMapEntry;
            }
        }
        table = tableExpand;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            private final int modCountIterator = modCount;

            @Override
            public boolean hasNext() {
                if (modCountIterator != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<K, V> mapEntry = (MapEntry<K, V>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
