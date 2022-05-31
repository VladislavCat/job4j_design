package ru.job4j.io.exampackio;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * -path=C:\projects\job4j_design -tf="Тип файла, в которых нужно искать"
 * -sf="Словосочетание, которое требуется найти" -out=result.txt
 */

public class TestRegular {
    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        Predicate<Path> predicate = path -> path.getFileName().toString().endsWith(argsName.get("tf"));
        List<Path> resSearch = Search.search(Path.of(argsName.get("path")), predicate);
        List<String> rsl = new ArrayList<>();
        for (Path path : resSearch) {
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                if (reader.lines().anyMatch(s -> s.contains(argsName.get("sf")))) {
                    rsl.add(path.toFile().toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(argsName.get("out")))) {
            for (String s : rsl) {
                out.write(s.getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
