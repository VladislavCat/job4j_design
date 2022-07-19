package ru.job4j.solid.lsp;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.time.LocalDateTime;
import java.util.List;

public class ControlQualityTest {

    @Test
    public void whenAddInWareHouseTwoFood() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        Food food1 = new Food("Meat", LocalDateTime.of(2022, 5, 11, 12, 0),
                LocalDateTime.of(2022, 5, 22, 12, 0), 250, 70);
        Food food2 = new Food("Rice", LocalDateTime.of(2022, 5, 5, 12, 0),
                LocalDateTime.of(2023, 5, 22, 12, 0), 250, 70);
        LocalDateTime todayDate = LocalDateTime.of(2022, 5, 12, 12, 0);
        cq.addManyFood(List.of(food1, food2), todayDate);
        assertThat(List.of(food1, food2), is(warehouse.getAll()));
    }

    @Test
    public void whenAddInShopTwoFood() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        Food food1 = new Food("Meat", LocalDateTime.of(2022, 5, 1, 12, 0),
                LocalDateTime.of(2022, 5, 22, 12, 0), 250, 70);
        Food food2 = new Food("Bread", LocalDateTime.of(2022, 5, 7, 12, 0),
                LocalDateTime.of(2022, 5, 19, 12, 0), 250, 70);
        LocalDateTime todayDate = LocalDateTime.of(2022, 5, 12, 12, 0);
        cq.addManyFood(List.of(food1, food2), todayDate);
        assertThat(List.of(food1, food2), is(shop.getAll()));
    }

    @Test
    public void whenAddInShopTwoTrash() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        Food food1 = new Food("Meat", LocalDateTime.of(2022, 5, 1, 12, 0),
                LocalDateTime.of(2022, 5, 22, 12, 0), 250, 70);
        Food food2 = new Food("Bread", LocalDateTime.of(2022, 5, 7, 12, 0),
                LocalDateTime.of(2022, 5, 19, 12, 0), 250, 70);
        LocalDateTime todayDate = LocalDateTime.of(2022, 8, 12, 12, 0);
        cq.addManyFood(List.of(food1, food2), todayDate);
        assertThat(List.of(food1, food2), is(trash.getAll()));
    }

    @Test
    public void whenAddFoodInShopWithDiscount() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        LocalDateTime todayDate = LocalDateTime.of(2022, 5, 12, 12, 0);
        Food food7 = new Food("Kefir", LocalDateTime.of(2022, 5, 5, 12, 0),
                LocalDateTime.of(2022, 5, 13, 12, 0), 117, 70);
        Food food8 = new Food("Cake", LocalDateTime.of(2022, 5, 7, 12, 0),
                LocalDateTime.of(2022, 5, 13, 12, 0), 214, 70);
        cq.addManyFood(List.of(food7, food8), todayDate);
        food7.setPrice(food7.getPrice() - food7.getDiscount());
        food8.setPrice(food8.getPrice() - food8.getDiscount());
        assertThat(List.of(food7, food8), is(shop.getAll()));
    }

    @Test
    public void whenEightFoodInAllStore() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        LocalDateTime todayDate = LocalDateTime.of(2022, 5, 12, 12, 0);
        Food food1 = new Food("FreezeChicken", LocalDateTime.of(2022, 5, 1, 12, 0),
                LocalDateTime.of(2022, 6, 30, 12, 0), 250, 70);
        Food food2 = new Food("Pelmen", LocalDateTime.of(2022, 5, 11, 12, 0),
                LocalDateTime.of(2022, 6, 12, 12, 0), 250, 70);

        Food food3 = new Food("Yogurt", LocalDateTime.of(2022, 5, 7, 12, 0),
                LocalDateTime.of(2022, 5, 15, 12, 0), 250, 70);
        Food food4 = new Food("Bread", LocalDateTime.of(2022, 5, 8, 12, 0),
                LocalDateTime.of(2022, 5, 16, 12, 0), 250, 70);

        Food food5 = new Food("Meat", LocalDateTime.of(2022, 5, 1, 12, 0),
                LocalDateTime.of(2022, 5, 12, 12, 0), 250, 70);
        Food food6 = new Food("Bread", LocalDateTime.of(2022, 5, 7, 12, 0),
                LocalDateTime.of(2022, 5, 12, 12, 0), 250, 70);

        Food food7 = new Food("Kefir", LocalDateTime.of(2022, 5, 5, 12, 0),
                LocalDateTime.of(2022, 5, 13, 12, 0), 117, 70);
        Food food8 = new Food("Cake", LocalDateTime.of(2022, 5, 7, 12, 0),
                LocalDateTime.of(2022, 5, 13, 12, 0), 214, 70);
        cq.addManyFood(List.of(food1, food2, food3, food4, food5, food6, food7, food8), todayDate);
        food7.setPrice(food7.getPrice() - food7.getDiscount());
        food8.setPrice(food8.getPrice() - food8.getDiscount());
        assertThat(List.of(food1, food2), is(warehouse.getAll()));
        assertThat(List.of(food3, food4, food7, food8), is(shop.getAll()));
        assertThat(List.of(food5, food6), is(trash.getAll()));
    }
}
