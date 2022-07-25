package ru.job4j.solid.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenuUI {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    private void init(Menu menu, MenuPrinter menuPrinter, Input input, List<Action> actions) {
        printCommand();
        boolean run = true;
        while (run) {
            printCommand();
            int i = input.askInt("Введите номер нужной команды.");
            if (i <= 0 || i > 4) {
                System.out.println("Введите число еще раз.");
            } else if (i == 4) {
                run = false;
            }
            if (i == 1) {
                actions.get(0).action();
            } else if (i == 2) {
                actions.get(1).action();
            } else if (i == 3) {
                actions.get(2).action();
            }
        }
    }

    private void printCommand() {
        System.out.println("""
                1.Добавить новую задачу\r
                2.Вывести все задачи на день.\r
                3.Найти задачу по имени.\r
                4.Закрыть приложение.""");
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new ConsoleMenuPrinter();
        ConsoleMenuUI consoleMenuUI = new ConsoleMenuUI();
        Input in = new ConsoleInput(new Scanner(System.in));
        List<Action> actions = new ArrayList<>(List.of(new AddTuskAction(in, menu), new PrintAllTuskAction(menu),
                new SelectForNameAction(in, menu)));
        consoleMenuUI.init(menu, menuPrinter, in, actions);
    }
}
