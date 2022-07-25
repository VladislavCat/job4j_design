package ru.job4j.solid.isp.menu;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;


public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenSelectReturn() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Написать код", STUB_ACTION);
        menu.add("Написать код", "Написать классы", STUB_ACTION);
        menu.add("Написать код", "Написать тесты", STUB_ACTION);
        assertEquals(new Menu.MenuItemInfo("Написать тесты", List.of(), STUB_ACTION, "1.2."),
                menu.select("Написать тесты").get());
    }

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        ConsoleMenuPrinter consoleMenuPrinter = new ConsoleMenuPrinter();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo("Сходить в магазин",
                        List.of("Купить продукты"), STUB_ACTION, "1."
                ), menu.select("Сходить в магазин").get());
        assertEquals(new Menu.MenuItemInfo("Купить продукты",
                        List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."),
                    menu.select("Купить продукты").get());
        assertEquals(new Menu.MenuItemInfo("Покормить собаку",
                        List.of(), STUB_ACTION, "2."),
                    menu.select("Покормить собаку").get()
            );
            consoleMenuPrinter.print(menu);
        }
}
