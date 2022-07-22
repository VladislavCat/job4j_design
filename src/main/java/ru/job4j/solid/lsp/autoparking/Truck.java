package ru.job4j.solid.lsp.autoparking;

public class Truck implements Auto {
    private final int size;

    public Truck(int size) {
        if (size <= PassAuto.SIZEPASS) {
            throw new IllegalArgumentException("Размер грузовика указан неверно");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
