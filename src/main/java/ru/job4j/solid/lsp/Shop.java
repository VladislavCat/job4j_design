package ru.job4j.solid.lsp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private final List<Food> storeFood = new ArrayList<>();
    private final List<Food> storeDiscountFood = new ArrayList<>();

    /**
     * Метод был изменен, так-как при ресортировке скидочного товара скидка проходила два раза.
     * Добавлено вспомогательное хранилище, в котором будут храниться товары, на которые магазин уже делал скидку.
     */
    @Override
    public boolean add(Food food, LocalDateTime todayDate) {
        boolean rsl = false;
        if (checkFresh(food, todayDate)) {
            int freshPercent = (int) getPercentLifeExpired(food, todayDate);
            if (freshPercent < FreshConstants.SHOPFRESH
                    && freshPercent > FreshConstants.TRASHFRESH
                    && (!storeDiscountFood.contains(food))) {

                    food.setPrice(food.getPrice() - food.getDiscount());
                    storeDiscountFood.add(food);
            }
            storeFood.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean checkFresh(Food food, LocalDateTime todayDate) {
        int freshPercent = (int) getPercentLifeExpired(food, todayDate);
        boolean rsl = false;
        if (freshPercent < FreshConstants.FRESHWAREHOUSE
                && freshPercent > FreshConstants.TRASHFRESH) {
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
        return new ArrayList<>(storeFood);
    }

    @Override
    public void deleteAllProduct() {
        storeFood.clear();
    }
}
