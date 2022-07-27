package ru.job4j.solid.lsp;

import java.time.LocalDateTime;
import java.util.List;

public interface Store {
    boolean add(Food food, LocalDateTime todayDate);
    boolean checkFresh(Food food, LocalDateTime todayDate);
    Food get(String nameFood);
    List<Food> getAll();
    void deleteAllProduct();
    default double getPercentLifeExpired(Food food, LocalDateTime todayDate) {
        double oneHundredPercent  = dateToDays(food.getExpiryDate(), food.getCreateDate());
        int rsl = (int) (dateToDays(food.getExpiryDate(), todayDate) / (oneHundredPercent / 100));
        return Math.max(rsl, 0);
    }
    default double dateToDays(LocalDateTime dateOne, LocalDateTime dateTwo) {
        int mount = dateOne.getDayOfYear() - dateTwo.getDayOfYear();
        int year = dateOne.getYear() - dateTwo.getYear();
        return mount + (year * 365);
    }
}
