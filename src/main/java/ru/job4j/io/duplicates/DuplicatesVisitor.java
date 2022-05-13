package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> setOriginalFile = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty tmpFP = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!setOriginalFile.containsKey(tmpFP)) {
            setOriginalFile.put(tmpFP, new ArrayList<>());
        }
        setOriginalFile.get(tmpFP).add(file);
        return super.visitFile(file, attrs);
    }

    public void getListDuplicates() {
        setOriginalFile.values().stream().filter(s -> s.size() > 1).forEach(s -> s.forEach(System.out::println));
    }
}
