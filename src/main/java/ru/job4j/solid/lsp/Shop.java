package ru.job4j.solid.lsp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private final List<Food> storeFood = new ArrayList<>();


    @Override
    public Food add(Food food) {
        storeFood.add(food);
        return get(food.getName());
    }

    @Override
    public boolean checkFresh(Food food, LocalDateTime todayDate) {
        int freshPercent = CheckExpiryDate.checkDate(food.getCreateDate(),
                food.getExpiryDate(), todayDate);
        boolean rsl = false;
        if (freshPercent < 75 && freshPercent > 25) {
            add(food);
            rsl = true;
        } else if (freshPercent < 25 && freshPercent > 0) {
            food.setPrice(food.getPrice() - food.getDiscount());
            add(food);
            rsl = true;
        }
        return rsl;
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
        return storeFood;
    }

    @Override
    public boolean delete(String nameFood) {
        return storeFood.remove(get(nameFood));
    }
}
