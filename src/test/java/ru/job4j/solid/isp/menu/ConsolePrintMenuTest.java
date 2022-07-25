package ru.job4j.solid.isp.menu;

import org.junit.Test;

import java.io.*;
import static ru.job4j.solid.isp.menu.SimpleMenuTest.STUB_ACTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsolePrintMenuTest {

    @Test
    public void whenPrint() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        ConsoleMenuPrinter consoleMenuPrinter = new ConsoleMenuPrinter();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Написать код", STUB_ACTION);
        menu.add("Написать код", "Написать классы", STUB_ACTION);
        menu.add("Написать код", "Написать тесты", STUB_ACTION);
        consoleMenuPrinter.print(menu);
        String actual = outputStream.toString();
        assertEquals("""
                1.Написать код\r
                \t1.1.Написать классы\r
                \t1.2.Написать тесты\r
                """, actual);
    }
}
