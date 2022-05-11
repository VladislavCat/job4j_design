package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Set<FileProperty> setOriginalFile = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty tmpFP = new FileProperty(file.toFile().length(), file.getFileName().toString());
        checkDuplicates(tmpFP, file);
        return super.visitFile(file, attrs);
    }

    public void checkDuplicates(FileProperty fileProperty, Path file) {
        if (!setOriginalFile.add(fileProperty)) {
            System.out.println(file.toFile().getAbsolutePath());
        }
    }
}
