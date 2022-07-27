package ru.job4j.solid.lsp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> storeFood = new ArrayList<>();


    @Override
    public boolean add(Food food, LocalDateTime todayDate) {
        boolean rsl = false;
        if (checkFresh(food, todayDate)) {
            storeFood.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean checkFresh(Food food, LocalDateTime todayDate) {
        int freshPercent = (int) getPercentLifeExpired(food, todayDate);
        return freshPercent > FreshConstants.FRESHWAREHOUSE;
    }

    @Override
    public Food get(String nameFood) {
        return storeFood.stream()
                .filter(food -> food.getName().equals(nameFood))
                .findFirst()
                .get();
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(storeFood);
    }

    @Override
    public void deleteAllProduct() {
        storeFood.clear();
    }
}
