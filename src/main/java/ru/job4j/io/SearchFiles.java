package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Predicate;
import java.nio.file.Path;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<Path> predicate;
    private List<Path> pathsList;

    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            pathsList.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return pathsList;
    }
}
