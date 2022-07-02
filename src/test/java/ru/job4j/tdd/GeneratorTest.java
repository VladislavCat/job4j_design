package ru.job4j.tdd;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class GeneratorTest {

    @Test
    public void whenGenerate() {
        Generator generator = new GeneratorNameSubject();
        Assert.assertEquals(generator.produce("I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr", "subject", "You")),
                "I am a Petr, Who are You? ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsMoreThanTwo() {
        Generator generator = new GeneratorNameSubject();
        generator.produce("I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr", "subject", "You", "Alter", "Winter"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsNotName() {
        Generator generator = new GeneratorNameSubject();
        generator.produce("I am a ${name}, Who are ${subject}? ",
                Map.of("date", "2002-12-22", "subject", "You"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsNotSubject() {
        Generator generator = new GeneratorNameSubject();
        generator.produce("I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr", "phone", "921293490"));
    }
}
