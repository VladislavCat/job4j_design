package ru.job4j.solid.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenuUI {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final int WRONGINDEX = 0;
    public static final int ADDINDEX = 1;
    public static final int SNOWALL = 2;
    public static final int FINDFORNAME = 3;
    public static final int CLOSEINDEX = 4;

    private void init(Menu menu, MenuPrinter menuPrinter, Input input, List<Action> actions) {
        boolean run = true;
        while (run) {
            printCommand();
            int i = input.askInt("Введите номер нужной команды.");
            if (i <= WRONGINDEX || i > CLOSEINDEX) {
                System.out.println("Введите число еще раз.");
            } else if (i == CLOSEINDEX) {
                run = false;
            }
            if (i == ADDINDEX) {
                actions.get(0).action();
            } else if (i == SNOWALL) {
                actions.get(1).action();
            } else if (i == FINDFORNAME) {
                actions.get(2).action();
            }
        }
    }

    private void printCommand() {
        System.out.println("""
                1.Добавить новую задачу
                2.Вывести все задачи на день.
                3.Найти задачу по имени.
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
