package ru.job4j.solid.lsp;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class CheckExpireDateTest {

    @Test
    public void whenOneHundredPercentFresh() {
        Assert.assertEquals(
                CheckExpiryDate.checkDate(LocalDateTime.of(2022, 12, 22, 12, 0),
                LocalDateTime.of(2022, 12, 28, 12, 0),
                LocalDateTime.of(2022, 12, 22, 12, 0)), 100);
    }

    @Test
    public void whenSeventyPercentFresh() {
        Assert.assertEquals(
                CheckExpiryDate.checkDate(LocalDateTime.of(2022, 12, 20, 12, 0),
                        LocalDateTime.of(2022, 12, 30, 12, 0),
                        LocalDateTime.of(2022, 12, 23, 12, 0)), 70);
    }


    @Test
    public void whenNullPercentFresh() {
        Assert.assertEquals(
                CheckExpiryDate.checkDate(LocalDateTime.of(2022, 12, 22, 12, 0),
                        LocalDateTime.of(2022, 12, 28, 12, 0),
                        LocalDateTime.of(2022, 12, 28, 12, 0)), 0);
    }

    @Test
    public void whenNegativePercentFresh() {
        Assert.assertEquals(
                CheckExpiryDate.checkDate(LocalDateTime.of(2022, 12, 22, 12, 0),
                        LocalDateTime.of(2022, 12, 28, 12, 0),
                        LocalDateTime.of(2023, 12, 28, 12, 0)), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenErrorFresh() {
                CheckExpiryDate.checkDate(LocalDateTime.of(2022, 12, 28, 12, 0),
                        LocalDateTime.of(2022, 12, 22, 12, 0),
                        LocalDateTime.of(2023, 12, 28, 12, 0));
    }
}
