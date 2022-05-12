package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, String> setOriginalFile = new HashMap<>();
    Map<String, FileProperty> duplicatesPath = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty tmpFP = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (setOriginalFile.containsKey(tmpFP)) {
            duplicatesPath.putIfAbsent(file.toFile().getAbsolutePath(), tmpFP);
            duplicatesPath.put(setOriginalFile.get(tmpFP), tmpFP);
        }
        setOriginalFile.put(tmpFP, file.toFile().getAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void getListDuplicates() {
        duplicatesPath.keySet().forEach(System.out::println);
    }
}
