package ru.job4j.solid.ocp;

public class OcpError {

    //*Поля класса должны быть абстракцией
    private String mail = "test@email.ru";
    //*Параметры и возвращаемые типы должны определяться абстракцией, а не реализацией
    //*Например, через интерфейс send
    public boolean send(String msg) {
        //*Отправка сообщения
        return true;
    }
}
