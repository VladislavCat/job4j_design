package ru.job4j.solid.lsp.autoparking;

public class Truck implements Auto {
    private final int size;
    public Truck() {
        size = 1;
    }

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
