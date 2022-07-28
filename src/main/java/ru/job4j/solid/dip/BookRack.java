package ru.job4j.solid.dip;

import java.util.List;

public class BookRack {

    public void showBook(List<Book> listBook) {
        for (Book book : listBook) {
            if (book.getText().length() > 1000) {
                System.out.println("Книга " + book.getName() + " слишком большая!");
                throw new IllegalArgumentException();
            }
        }
        for (Book book : listBook) {
            book.printBook();
        }
    }
}
