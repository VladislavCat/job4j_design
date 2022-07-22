package ru.job4j.solid.lsp.autoparking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.List;

public class ParkingTest {

    @Test
    public void whenParkTwoPassCarAndOneTrack() {
        Parking parking = new SimpleParking(2, 1);
        Auto passAuto1 = new PassAuto();
        Auto passAuto2 = new PassAuto();
        Auto truck = new Truck();
        parking.addAutoInPark(passAuto1);
        parking.addAutoInPark(passAuto2);
        parking.addAutoInPark(truck);
        assertThat(parking.getAllPassAuto(), is(List.of(passAuto1, passAuto2)));
        assertThat(parking.getAllTruckAuto(), is(List.of(truck)));
    }

    @Test
    public void whenParkTwoTrack() {
        Parking parking = new SimpleParking(2, 1);
        Auto truck1 = new Truck();
        Auto truck2 = new Truck();
        parking.addAutoInPark(truck1);
        parking.addAutoInPark(truck2);
        assertThat(parking.getAllPassAuto(), is(List.of(truck2)));
        assertThat(parking.getAllTruckAuto(), is(List.of(truck1)));
    }

    @Test
    public void whenParkTwoPassCarInTrackParking() {
        Parking parking = new SimpleParking(0, 2);
        Auto passAuto1 = new PassAuto();
        Auto passAuto2 = new PassAuto();
        parking.addAutoInPark(passAuto1);
        parking.addAutoInPark(passAuto2);
        assertThat(parking.getAllTruckAuto(), is(List.of(passAuto1, passAuto2)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenParkBigTrack() {
        Parking parking = new SimpleParking(2, 1);
        Auto truck1 = new Truck();
        Auto truck2 = new Truck(10);
        parking.addAutoInPark(truck1);
        parking.addAutoInPark(truck2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenParkNoPlaces() {
        Parking parking = new SimpleParking(0, 0);
        Auto auto = new PassAuto();
        parking.addAutoInPark(auto);
    }
}
