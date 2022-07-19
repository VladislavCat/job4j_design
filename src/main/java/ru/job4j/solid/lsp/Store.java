package ru.job4j.solid.lsp;

import java.time.LocalDateTime;
import java.util.List;

public interface Store {
    Food add(Food food);
    boolean checkFresh(Food food, LocalDateTime todayDate);
    Food get(String nameFood);
    List<Food> getAll();
    boolean delete(String nameFood);
}
