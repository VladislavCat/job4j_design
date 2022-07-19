package ru.job4j.solid.lsp;

public class Message {
    private String str;

    public Message(String str) {
        this.str = str;
    }

    public void printText() {
        if (this.str.length() == 0) {
            throw new IllegalArgumentException("В сообщение ничего нет");
        }
        System.out.println(this.str);
    }

    public String sendMessage() {
        if (str.length() > 5000) {
            throw new IllegalArgumentException("Сообщение слишком большое");
        }
        return str;
    }

}
