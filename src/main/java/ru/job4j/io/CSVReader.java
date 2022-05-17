package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        Scanner scanner = new Scanner(new File(argsName.get("path")));
        String line = scanner.nextLine();
        List<Integer> integers = checkColumnForDelete(line, argsName.get("filter"), delimiter);
        String rsl = null;
        boolean firstLine = true;
        while (scanner.hasNextLine()) {
            if (firstLine) {
                firstLine = false;
                rsl = generatingString(line, integers, delimiter)
                        + System.lineSeparator();
                continue;
            }
            rsl = rsl.concat(generatingString(scanner.nextLine(), integers, delimiter)
                    + System.lineSeparator());
        }
        printResult(rsl, argsName);
    }
    private static String generatingString(String line, List<Integer> cellForDelete, String delimiter) {
        List<String> tmp = List.of(line.split(delimiter));
        return tmp.stream()
                .filter(s -> cellForDelete.contains(tmp.indexOf(s)))
                .reduce((s1, s2) -> s1 + delimiter + s2).get();
    }

    private static List<Integer> checkColumnForDelete(String firstLine, String columnForDelete, String delimiter) {
        List<String> arr = List.of(firstLine.split(delimiter));
        return Stream.of(columnForDelete.split(","))
                .filter(arr::contains)
                .map(arr::indexOf)
                .toList();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        handle(argsName);
    }

    private static void checkArgs(ArgsName argsName) {
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Указан не csv-файл");
        }
        if ("\";\"".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Указан разделитель не для CSV-файла");
        }
    }
    private static void printResult(String rsl, ArgsName argsName) {
        if ("stdout".equals(argsName.get("out"))) {
            System.out.println(rsl);
        } else {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(argsName.get("out")))) {
                out.write(rsl.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
