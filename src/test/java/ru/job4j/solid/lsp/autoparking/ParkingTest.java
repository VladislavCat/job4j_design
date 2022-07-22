package ru.job4j.solid.lsp.autoparking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public class ParkingTest {

    @Test
    public void whenParkTwoPassCarAndOneTrack() {
        Parking parking = new SimpleParking(2, 1);
        Auto passAuto1 = new PassAuto();
        Auto passAuto2 = new PassAuto();
        Auto truck = new Truck(2);
        parking.addAutoInPark(passAuto1);
        parking.addAutoInPark(passAuto2);
        parking.addAutoInPark(truck);
        assertThat(parking.getAllPassAuto(), is(List.of(passAuto1, passAuto2)));
        assertThat(parking.getAllTruckAuto(), is(List.of(truck)));
    }

    @Test
    public void whenParkTwoTrack() {
        Parking parking = new SimpleParking(2, 1);
        Auto truck1 = new Truck(2);
        Auto truck2 = new Truck(2);
        parking.addAutoInPark(truck1);
        parking.addAutoInPark(truck2);
        assertThat(parking.getAllPassAuto(), is(List.of(truck2)));
        assertThat(parking.getAllTruckAuto(), is(List.of(truck1)));
    }

    @Test
    public void whenParkBigTrack() {
        Parking parking = new SimpleParking(2, 1);
        Auto truck1 = new Truck(2);
        Auto truck2 = new Truck(10);
        assertTrue(parking.addAutoInPark(truck1));
        assertFalse(parking.addAutoInPark(truck2));
    }

    @Test
    public void whenParkNoPlaces() {
        Parking parking = new SimpleParking(0, 0);
        Auto auto = new PassAuto();
        assertFalse(parking.addAutoInPark(auto));
    }
}
