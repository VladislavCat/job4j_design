package ru.job4j.solid.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {
    public static final String TABULATION = "    ";
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            String tabulation = TABULATION.repeat(menuItemInfo.getNumber().split("\\.").length - 1)
                    + menuItemInfo.getNumber() + menuItemInfo.getName();
            System.out.println(tabulation);
        }
    }
}
