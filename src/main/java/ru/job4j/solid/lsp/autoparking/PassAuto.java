package ru.job4j.solid.lsp.autoparking;

public class PassAuto implements Auto {
    public static final int SIZEPASS = 1;
    @Override
    public int getSize() {
        return SIZEPASS;
    }
}
