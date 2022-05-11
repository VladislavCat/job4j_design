package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, String> setOriginalFile = new HashMap<>();
    List<String> duplicatesPath = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty tmpFP = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (setOriginalFile.containsKey(tmpFP)) {
            duplicatesPath.add(file.toFile().getAbsolutePath());
            duplicatesPath.add(setOriginalFile.get(tmpFP));
        }
        setOriginalFile.put(tmpFP, file.toFile().getAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void getListDuplicates() {
        duplicatesPath.forEach(System.out::println);
    }
}
