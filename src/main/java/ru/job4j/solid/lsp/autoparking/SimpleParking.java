package ru.job4j.solid.lsp.autoparking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    List<Auto> passStore = new ArrayList<>();
    List<Auto> truckStore = new ArrayList<>();
    int placeForPassengerAuto;
    int placeForTruck;

    public SimpleParking(int placeForPassengerAuto, int placeForTruck) {
        this.placeForPassengerAuto = placeForPassengerAuto;
        this.placeForTruck = placeForTruck;
    }

    @Override
    public boolean addAutoInPark(Auto auto) {
        boolean rsl = false;
        if (auto instanceof Truck) {
            rsl = addTruckInParking(auto);
        } else {
            rsl = addPassengerAutoInParking(auto);
        }
        return rsl;
    }

    private boolean addTruckInParking(Auto auto) {
        if (placeForTruck == 0 && placeForPassengerAuto <= auto.getSize()) {
            throw new IllegalArgumentException("Нет места для грузовиков на парковке!");
        } else if (placeForTruck == 0 && placeForPassengerAuto >= auto.getSize()) {
            passStore.add(auto);
            placeForPassengerAuto -= auto.getSize();
        } else {
            truckStore.add(auto);
            placeForTruck--;
        }
        return true;
    }

    private boolean addPassengerAutoInParking(Auto auto) {
        if (placeForPassengerAuto == 0 && placeForTruck == 0) {
            throw new IllegalArgumentException("На парковке нет мест!");
        } else if (placeForPassengerAuto == 0 && placeForTruck >= 1) {
            truckStore.add(auto);
            placeForTruck--;
        } else {
            passStore.add(auto);
            placeForPassengerAuto--;
        }
        return true;
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
