package ru.job4j.io;


import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MulTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("res.txt")) {
            for (int i = 2; i < 10; i++) {
                for (int j = 1; j < 11; j++) {
                    out.write(String.valueOf(i * j).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
