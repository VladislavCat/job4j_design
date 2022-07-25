package ru.job4j.solid.isp.menu;

public class PrintAllTuskAction implements Action {
    private final Menu menu;

    public PrintAllTuskAction(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void action() {
        MenuPrinter menuPrinter = new ConsoleMenuPrinter();
        menuPrinter.print(menu);
    }
}
