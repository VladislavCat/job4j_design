package ru.job4j.tdd;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(nullValue()));
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new SessionCinema());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(nullValue()));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.DECEMBER, 13, 19, 30);
        cinema.buy(account, -1, -1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.DECEMBER, 45, 25, 30);
        cinema.buy(account, 1, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenOccupiedPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.DECEMBER, 13, 19, 30);
        cinema.buy(account, 1, 1, date);
        cinema.buy(account, 1, 1, date);
    }
}