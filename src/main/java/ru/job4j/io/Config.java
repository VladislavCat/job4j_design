package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public Map<String, String> load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
             read.lines()
                    .filter(s -> !(s.isEmpty()))
                    .filter(s -> !(s.startsWith("#")))
                    .filter(this::checkException)
                    .forEach(s -> values.put(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=") + 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

    private boolean checkException(String s) {
        if ((s.substring(0, s.indexOf("=")).isEmpty() || (s.substring(s.indexOf("=") + 1)).isEmpty())) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}