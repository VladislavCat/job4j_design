package ru.job4j.solid.lsp.autoparking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    private final List<Auto> passStore;
    private final List<Auto> truckStore;
    private int placeForPassengerAuto;
    private int placeForTruck;

    public SimpleParking(int placeForPassengerAuto, int placeForTruck) {
        this.placeForPassengerAuto = placeForPassengerAuto;
        this.placeForTruck = placeForTruck;
        passStore = new ArrayList<>(placeForPassengerAuto);
        truckStore = new ArrayList<>(placeForTruck);
    }

    @Override
    public boolean addAutoInPark(Auto auto) {
        boolean rsl = false;
        int sizeAuto = auto.getSize();
        if (sizeAuto > PassAuto.SIZEPASS) {
            if (placeForTruck < PassAuto.SIZEPASS && placeForPassengerAuto >= sizeAuto) {
                    passStore.add(auto);
                    placeForPassengerAuto -= sizeAuto;
                     rsl = true;
            } else if (placeForTruck >= PassAuto.SIZEPASS) {
                    truckStore.add(auto);
                    placeForTruck--;
                    rsl = true;
            }
        } else if (placeForPassengerAuto >= PassAuto.SIZEPASS) {
            passStore.add(auto);
            placeForPassengerAuto--;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Auto> getAllPassAuto() {
        return new ArrayList<>(passStore);
    }

    @Override
    public List<Auto> getAllTruckAuto() {
        return new ArrayList<>(truckStore);
    }
}
