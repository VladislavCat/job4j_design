package ru.job4j.gc;

import java.util.Scanner;

public class Emulator {
    private static Scanner scanner;
    private static DirFileCache dfc;

    public static void showMenu() {
        System.out.println("""
                1. Записать директорию кеширования
                2. Добавить файл в кеш
                3. Получить содержимое файла из кеша
                4. Закрыть приложение""");
    }

    public static void main(String[] args) {
        boolean run = true;
        showMenu();
        scanner = new Scanner(System.in);
        Emulator emulator = new Emulator();
        while (run) {
            int i = emulator.askInt("Введите номер нужного запроса: ");
            if (i == 1) {
                emulator.createDFC();
            } else if (i == 2) {
                if (dfc == null) {
                    emulator.createDFC();
                }
                String a = emulator.askStr("Введите имя файла: ");
                dfc.put(a, dfc.load(a));
            } else if (i == 3) {
                if (dfc == null) {
                    emulator.createDFC();
                }
                System.out.println(dfc.get(emulator.askStr("Введите имя файла: ")));
            } else if (i == 4) {
                run = false;
            }
        }
    }

    private void createDFC() {
        dfc = new DirFileCache(this.askStr("Выберите директорию: "));
    }

    private int askInt(String ask) {
        int i = 0;
        try {
            i = Integer.parseInt(askStr(ask));
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели не число");
        }
        return i;
    }

    private String askStr(String ask) {
        System.out.println(ask);
        return scanner.nextLine();
    }
}
