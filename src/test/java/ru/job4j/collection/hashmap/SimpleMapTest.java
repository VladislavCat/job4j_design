package ru.job4j.collection.hashmap;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    @Test
    public void whenPutElement() {
        SimpleMap<String, Integer> test = new SimpleMap<>();
        test.put("Meta", 2);
        int rsl = test.get("Meta");
        Assert.assertEquals(rsl, 2);
    }

    @Test
    public void whenPutThreeElement() {
        SimpleMap<String, Integer> test = new SimpleMap<>();
        test.put("Meta", 2);
        test.put("ElementTree", 123);
        test.put("Peltor", 21);
        test.put("Meta", 11);
        int rsl = test.get("Meta");
        Assert.assertEquals(rsl, 2);
    }

    @Test
    public void whenGetElementWrongKey() {
        SimpleMap<String, String> test = new SimpleMap<>();
        test.put("Meta", "Artur");
        test.put("ElementTree", "123");
        test.put("Peltor", "21");
        Assert.assertNull(test.get("Simple"));
    }

    @Test
    public void whenRemoveElement() {
        SimpleMap<String, String> test = new SimpleMap<>();
        test.put("Meta", "Artur");
        test.put("ElementTree", "123");
        test.put("Peltor", "21");
        Assert.assertTrue(test.remove("Peltor"));
        Assert.assertNull(test.get("Peltor"));
    }

    @Test
    public void whenRemoveWrongElement() {
        SimpleMap<String, String> test = new SimpleMap<>();
        test.put("Meta", "Artur");
        test.put("ElementTree", "123");
        test.put("Peltor", "21");
        Assert.assertFalse(test.remove("Mentol"));
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<String, String> test = new SimpleMap<>();
        test.put("Meta", "Artur");
        test.put("ElementTree", "123");
        test.put("Peltor", "21");
        Iterator<String> iterator = test.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next(), "ElementTree");
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next(), "Peltor");
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next(), "Meta");
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModificationOnIterator() {
        SimpleMap<String, String> test = new SimpleMap<>();
        test.put("Meta", "Artur");
        test.put("ElementTree", "123");
        test.put("Peltor", "21");
        Iterator<String> iterator = test.iterator();
        iterator.next();
        test.put("Tol", "Error");
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        SimpleMap<String, String> test = new SimpleMap<>();
        test.put("Meta", "Artur");
        Iterator<String> iterator = test.iterator();
        iterator.next();
        iterator.next();
    }
}
