package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Неверно указан ключ");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            if (!s.contains("=")) {
                throw new IllegalArgumentException();
            }
            String tmpArgsSt1 = s.substring(0, s.indexOf("="));
            String tmpArgsSt2 = s.substring(s.indexOf("=") + 1);
            if (tmpArgsSt2.isEmpty() || !tmpArgsSt1.startsWith("-")
                    || (tmpArgsSt1.startsWith("-") && tmpArgsSt1.length() == 1)) {
                throw new IllegalArgumentException("Аргументы указаны неверно");
            }
            values.put(tmpArgsSt1.substring(1), tmpArgsSt2);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        if (args.length == 0) {
            throw new IllegalArgumentException();
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