package ru.job4j.solid.isp;

import java.awt.*;

/**
 * Данный интерфейс тоже является перегруженным. Часть телефонов может не иметь сенсорного экрана, камеры или Wifi модуля,
 * однако, им придется их реализовывать. Главная суть подхода ISP это не создавать перегруженные интерфейсы.
 */
public interface Phone {
    int touchReaction();
    Image photoResult();
    int wiFiModule();
    int call();
}
