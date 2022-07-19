package ru.job4j.solid.lsp;

import java.time.LocalDateTime;

public class CheckExpiryDate {

    public static int checkDate(LocalDateTime createDate, LocalDateTime expiryDate, LocalDateTime thisDate) {
        if (createDate.isAfter(expiryDate)) {
            throw new IllegalArgumentException("Неверно указаны даты, создание товара не может быть после выхода срока годности.");
        }
        double hundredPercent = dateToDays(expiryDate, createDate);
        int rsl = (int) (dateToDays(expiryDate, thisDate) / (hundredPercent / 100));
        return Math.max(rsl, 0);
    }

    private static int dateToDays(LocalDateTime dateOne, LocalDateTime dateTwo) {
        int mount = dateOne.getDayOfYear() - dateTwo.getDayOfYear();
        int year = dateOne.getYear() - dateTwo.getYear();
        return mount + (year * 365);
    }
}
