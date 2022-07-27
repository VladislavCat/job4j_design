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
        LocalDateTime todayDate = LocalDateTime.now();
        Food food1 = new Food("Meat", todayDate.minusDays(2),
                todayDate.plusDays(15), 250, 70);
        Food food2 = new Food("Rice", todayDate.minusDays(5),
                todayDate.plusDays(70), 250, 70);
        cq.addManyFood(List.of(food1, food2), todayDate);
        assertThat(List.of(food1, food2), is(warehouse.getAll()));
    }

    @Test
    public void whenAddInShopTwoFood() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        LocalDateTime todayDate = LocalDateTime.now();
        Food food1 = new Food("Meat", todayDate.minusDays(15),
                todayDate.plusDays(14), 250, 70);
        Food food2 = new Food("Bread", todayDate.minusDays(7),
                todayDate.plusDays(18), 250, 70);
        cq.addManyFood(List.of(food1, food2), todayDate);
        assertThat(List.of(food1, food2), is(shop.getAll()));
    }

    @Test
    public void whenAddInShopTwoTrash() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        LocalDateTime todayDate = LocalDateTime.now();
        Food food1 = new Food("Meat", todayDate.minusDays(11),
                todayDate.minusDays(1), 250, 70);
        Food food2 = new Food("Bread", todayDate.minusDays(31),
                todayDate.minusDays(11), 250, 70);
        cq.addManyFood(List.of(food1, food2), todayDate);
        assertThat(List.of(food1, food2), is(trash.getAll()));
    }

    @Test
    public void whenAddFoodInShopWithDiscount() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        LocalDateTime todayDate = LocalDateTime.now();
        Food food7 = new Food("Kefir", todayDate.minusDays(11),
                todayDate.plusDays(2), 117, 70);
        Food food8 = new Food("Cake", todayDate.minusDays(15),
                todayDate.plusDays(2), 214, 70);
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
        LocalDateTime todayDate = LocalDateTime.now();
        Food food1 = new Food("Meat", todayDate.minusDays(2),
                todayDate.plusDays(15), 250, 70);
        Food food2 = new Food("Rice", todayDate.minusDays(5),
                todayDate.plusDays(70), 250, 70);

        Food food3 = new Food("Meat", todayDate.minusDays(15),
                todayDate.plusDays(14), 250, 70);
        Food food4 = new Food("Bread", todayDate.minusDays(7),
                todayDate.plusDays(18), 250, 70);

        Food food5 = new Food("Kefir", todayDate.minusDays(11),
                todayDate.plusDays(2), 117, 70);
        Food food6 = new Food("Cake", todayDate.minusDays(15),
                todayDate.plusDays(2), 214, 70);

        Food food7 = new Food("Meat", todayDate.minusDays(11),
                todayDate.minusDays(1), 250, 70);
        Food food8 = new Food("Bread", todayDate.minusDays(31),
                todayDate.minusDays(11), 250, 70);
        cq.addManyFood(List.of(food1, food2, food3, food4, food5, food6, food7, food8), todayDate);
        food7.setPrice(food5.getPrice() - food5.getDiscount());
        food8.setPrice(food6.getPrice() - food6.getDiscount());
        assertThat(List.of(food1, food2), is(warehouse.getAll()));
        assertThat(List.of(food3, food4, food5, food6), is(shop.getAll()));
        assertThat(List.of(food7, food8), is(trash.getAll()));
    }

    @Test
    public void whenResort() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        LocalDateTime todayDate = LocalDateTime.now();
        Food food1 = new Food("Meat", todayDate.minusDays(2),
                todayDate.plusDays(15), 250, 70);
        Food food2 = new Food("Rice", todayDate.minusDays(5),
                todayDate.plusDays(70), 250, 70);
        Food food3 = new Food("Meat", todayDate.minusDays(15),
                todayDate.plusDays(14), 250, 70);
        Food food4 = new Food("Bread", todayDate.minusDays(7),
                todayDate.plusDays(18), 250, 70);
        Food food5 = new Food("Kefir", todayDate.minusDays(11),
                todayDate.plusDays(2), 117, 70);
        Food food6 = new Food("Cake", todayDate.minusDays(15),
                todayDate.plusDays(2), 214, 70);
        Food food7 = new Food("Meat", todayDate.minusDays(11),
                todayDate.minusDays(1), 250, 70);
        Food food8 = new Food("Bread", todayDate.minusDays(31),
                todayDate.minusDays(11), 250, 70);
        cq.addManyFood(List.of(food1, food2, food3, food4, food5, food6, food7, food8), todayDate);
        cq.resortFood(todayDate);
        assertThat(List.of(food1, food2), is(warehouse.getAll()));
        assertThat(List.of(food3, food4, food5, food6), is(shop.getAll()));
        assertThat(List.of(food7, food8), is(trash.getAll()));
        System.out.println(food5);
        }
}

