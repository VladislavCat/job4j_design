package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(Base model) {
        storage.putIfAbsent(model.getId(), (T) model);
    }

    @Override
    public boolean replace(String id, Base model) {
        storage.replace(id, (T) model);
        return findById(id) == model;
    }

    @Override
    public boolean delete(String id) {
        storage.remove(id);
        return findById(id) == null;
    }

    @Override
    public Base findById(String id) {
        return storage.keySet()
                .stream()
                .filter(s -> s.equals(id))
                .map(storage::get)
                .findFirst()
                .orElse(null);
    }
}
