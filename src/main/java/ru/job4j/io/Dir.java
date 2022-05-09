package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            long sizeSubFile = volumeDirectory(subfile);
            System.out.println(String.valueOf(subfile.getAbsoluteFile()) + " " + sizeSubFile);
        }
    }

    private static long volumeDirectory(File file) {
        long rsl = 0;
        if (file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                rsl += volumeDirectory(file1);
            }
        } else {
            rsl += file.length();
        }
        return rsl;
    }
}