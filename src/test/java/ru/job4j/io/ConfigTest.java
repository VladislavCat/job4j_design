package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        Assert.assertEquals(config.value("name"), "Petr Arsentev");
    }

    @Test
    public void whenPropertiesWithNullLines() {
        String path = "./data/properties_with_nulllines.properties";
        Config config = new Config(path);
        config.load();
        Assert.assertEquals(config.value("name1"), "Rome");
        Assert.assertEquals(config.value("name2"), "Stambul");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIllegalArgumentException() {
        String path = "./data/properties_with_error.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenSpecificPropertiesWithMultiEqualSign() {
        String path = "./data/properties_with_.multi_equal.properties";
        Config config = new Config(path);
        config.load();
        Assert.assertEquals(config.value("name2"), "useless=2");
        Assert.assertEquals(config.value("name1"), "ill");
        Assert.assertEquals(config.value("key"), "value=");
    }
}

