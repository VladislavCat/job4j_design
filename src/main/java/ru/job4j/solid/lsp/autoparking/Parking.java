package ru.job4j.solid.lsp.autoparking;

import java.util.List;

public interface Parking {
    boolean addAutoInPark(Auto auto);
    List<Auto> getAllPassAuto();
    List<Auto> getAllTruckAuto();
}
