package ru.job4j.solid.lsp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> storeFood = new ArrayList<>();


    @Override
    public Food add(Food food, LocalDateTime todayDate) {
        Food rsl = null;
        if (checkFresh(food, todayDate)) {
            storeFood.add(food);
            rsl = get(food.getName());
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
}
