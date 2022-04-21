package ru.job4j.collection.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNoSuchElementException() {
        Set<Integer> set = new SimpleSet<>();
        Iterator<Integer> setIt = set.iterator();
        setIt.next();
    }

    @Test
    public void whenIteratorTwoElem() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        set.add(2);
        Iterator<Integer> setIt = set.iterator();
        assertTrue(setIt.hasNext());
        assertThat(setIt.next(), is(1));
        assertTrue(setIt.hasNext());
        assertThat(setIt.next(), is(2));
    }

    @Test
    public void whenNullElementFalseHasNext() {
        Set<Integer> set = new SimpleSet<>();
        Iterator<Integer> setIt = set.iterator();
        assertFalse(setIt.hasNext());
    }
}