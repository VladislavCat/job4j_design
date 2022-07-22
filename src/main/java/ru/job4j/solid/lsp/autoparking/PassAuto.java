package ru.job4j.solid.lsp.autoparking;

public class PassAuto implements Auto {
    private final int size = 1;
    @Override
    public int getSize() {
        return size;
    }
}
