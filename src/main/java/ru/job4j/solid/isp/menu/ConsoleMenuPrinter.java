package ru.job4j.solid.isp.menu;

import java.util.Arrays;
import java.util.stream.Stream;

public class ConsoleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            String tabulation = "\t".repeat(tabulationCount(menuItemInfo.getNumber()))
                    + menuItemInfo.getNumber() + menuItemInfo.getName();
            System.out.println(tabulation);
        }
    }

    private int tabulationCount(String str) {
        return ((int) str.chars().filter(c -> c == 46).count()) - 1;
    }
}
