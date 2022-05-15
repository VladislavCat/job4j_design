package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Неверно указаны аргументы");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        List<String> argsValues = new ArrayList<>(argsName.getAllValues().values());
        zip.checkArgs(argsValues);
        zip.packFiles(zip.findAllFile(argsValues), new File(argsValues.get(2)));
        zip.packSingleFile(new File("pom.xml"), new File(".pom.zip"));
    }

    private void checkArgs(List<String> args) {
        if (!new File(args.get(0)).isDirectory()) {
            throw new IllegalArgumentException("Неверно указаны аргументы");
        }
        if (!args.get(1).startsWith(".")) {
            throw new IllegalArgumentException("Неверно указаны аргументы");
        }
    }

    private List<File> findAllFile(List<String> args) throws IOException {
        return Search.search(Path.of(args.get(0)),
                        s -> !(s.getFileName().toString().endsWith(args.get(1))))
                .stream()
                .map(Path::toFile)
                .toList();
    }
}