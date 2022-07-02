package ru.job4j.tdd;

import java.util.Map;

public class GeneratorNameSubject implements Generator {
    @Override
    public String produce(String template, Map<String, String> args) {
        if (args.size() != 2 || args.get("name") == null || args.get("subject") == null) {
            throw new IllegalArgumentException();
        }
        return template.replace("${name}", args.get("name")).replace("${subject}", args.get("subject"));
    }
}
