package ru.job4j.solid.lsp;

import java.time.LocalDateTime;
import java.util.List;

public class ControlQuality {
    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void addFood(Food food, LocalDateTime todayDate) {
        for (Store store : storeList) {
            if (store.checkFresh(food, todayDate)) {
                if (store.add(food, todayDate)) {
                    break;
                }
            }
        }
    }

    public void addManyFood(List<Food> foodList, LocalDateTime todayDate) {
        for (Food food : foodList) {
            addFood(food, todayDate);
        }
    }
}
