package ru.job4j.solid.isp.menu;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String askStr(String str) {
        System.out.println(str);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String str) {
        return Integer.parseInt(askStr(str));
    }
}
