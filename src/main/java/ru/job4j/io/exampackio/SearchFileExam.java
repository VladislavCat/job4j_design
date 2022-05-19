package ru.job4j.io.exampackio;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchFileExam {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Аргументы указаны неверно");
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        List<Path> listPath = Search.search(Path.of(argsName.get("d")),
                createPredicateFromAllType(argsName.get("t"), argsName.get("n")));
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(argsName.get("o")))) {
            for (Path path : listPath) {
                out.write(path.toFile().getAbsolutePath().getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkArgs(ArgsName argsName) {
        File file = new File(argsName.get("d"));
        if (!file.isDirectory() || !file.exists()) {
            throw new IllegalArgumentException("Указанный путь не является директорией или не существует");
        }
        String typeFind = argsName.get("t");
        if (!"mask".equals(typeFind) && !"name".equals(typeFind) && !"regex".equals(typeFind)) {
            throw new IllegalArgumentException("Неверно указан тип поиска элемента/ов");
        }
    }
    /**
    Создание фильтра типа поиска для маски данных
     */
    private static String createExtensionForMask(String s) {
        return s.replace(".", "[.]")
                .replace("*", ".*")
                .replace("?", ".");
    }

    /**
     * Создание условия для нахождения файла в зависимости от типа поиска
     * @return Predicate<Path>
     */
    private static Predicate<Path> createPredicateFromAllType(String typeFind, String fileName) {
        Predicate<Path> rsl = null;
        if ("name".equals(typeFind)) {
            rsl = s -> s.getFileName().toString().equals(fileName);
        } else if ("mask".equals(typeFind)) {
            String key = createExtensionForMask(fileName);
            rsl = createPredicateFromAllType("regex", key);
        } else if ("regex".equals(typeFind)) {
            rsl = path -> Pattern.compile(fileName).matcher(path.getFileName().toString()).find();
        }
        return rsl;
    }
}
