package ru.job4j.tdd;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Arrays;
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

    @Test
    public void rescheduleTicket() {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        date.set(2020, 10, 11, 23, 00);
        ticket = cinema.reschedule(ticket, date);
        assertThat(ticket, is(nullValue()));
    }

    @Test
    public void deleteTicket() {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(cinema.deleteTicket(ticket), is(false));

    }

}