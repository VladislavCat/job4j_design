package ru.job4j.solid.dip;

/**
 * Одним из примеров нарушения DIP было бы использование в проекте Tracker реализации ArrayList прямо в StartUI.
 * Если честно, на тему DIP очень тяжело придумать примеры, потому что все упирается в то, что нужно использовать
 * и зависеть от абстракций, а не от реализаций
 */
public class Book {
        private final String text;
        private final PrinterConsole printerConsole;

    /**
     *Модуль Book зависит от низкоуровневой реализации, что является нарушением принципа инверсий зависимостей
     */
        public Book(String text, PrinterConsole printerConsole) {
            this.text = text;
            this.printerConsole = printerConsole;
        }

        public void printBook() {
            printerConsole.print(text);
        }
}
