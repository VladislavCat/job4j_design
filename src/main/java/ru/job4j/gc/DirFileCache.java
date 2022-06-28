package ru.job4j.gc;

import java.io.*;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir + "\\";
    }

    @Override
    protected String load(String key) {
        String rsl = null;
        try (BufferedReader br = new BufferedReader(new FileReader(cachingDir + key))) {
            rsl = br.lines().reduce((s1, s2) -> s1 + System.lineSeparator() + s2).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

}
