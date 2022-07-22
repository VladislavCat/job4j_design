package ru.job4j.solid.lsp.autoparking;

import java.util.List;

public class SimpleParking implements Parking {


    public SimpleParking(int i, int n) {
    }

    @Override
    public boolean addAutoInPark(Auto auto) {
        return false;
    }

    @Override
    public List<Auto> getAllPassAuto() {
        return null;
    }

    @Override
    public List<Auto> getAllTruckAuto() {
        return null;
    }
}
