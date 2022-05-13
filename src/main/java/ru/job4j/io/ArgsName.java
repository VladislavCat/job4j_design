package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Неверно указан ключ");
        }
        return values.get(key);
    }

    public Map<String, String> getAllValues() {
        return values;
    }

    private void checkString(String str, Predicate<String> predicate) {
        if (predicate.test(str)) {
            throw new IllegalArgumentException("Неверно указаны аргументы");
        }
    }

    private void parse(String[] args) {
        for (String s : args) {
            checkString(s, s1 -> !s1.contains("=")
                    || !s1.startsWith("-") || s1.startsWith("-="));
            String tmpArgsSt1 = s.substring(0, s.indexOf("="));
            String tmpArgsSt2 = s.substring(s.indexOf("=") + 1);
            checkString(tmpArgsSt2, String::isEmpty);
            values.put(tmpArgsSt1.substring(1), tmpArgsSt2);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        if (args.length == 0) {
            throw new IllegalArgumentException("Аргументы указаны неверно");
        }
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}