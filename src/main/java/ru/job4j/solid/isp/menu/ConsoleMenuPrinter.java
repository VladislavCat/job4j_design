package ru.job4j.solid.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            String tabulation = "    ".repeat(menuItemInfo.getNumber().split("\\.").length - 1)
                    + menuItemInfo.getNumber() + menuItemInfo.getName();
            System.out.println(tabulation);
        }
    }
}
