package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;


public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.addAfter(input, 8, 5);
    }

    @Test
    public void whenRemoveIfNotEvenNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Predicate<Integer> evenPredict = s -> s % 2 != 0;
        ListUtils.removeIf(input, evenPredict);
        assertThat(input, is(Arrays.asList(2, 4, 6)));
    }

    @Test
    public void whenReplaceIfEvenNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Predicate<Integer> evenPredict = s -> s % 2 == 0;
        ListUtils.replaceIf(input, evenPredict, 1);
        assertThat(input, is(Arrays.asList(1, 1, 3, 1, 5, 1)));
    }

    @Test
    public void whenDeleteIfContainsInListElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.removeAll(input, Arrays.asList(6, 3, 9));
        assertThat(input, is(Arrays.asList(1, 2, 4, 5)));
    }

    @Test
    public void whenDeleteWhenElementsEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.removeAll(input, Arrays.asList());
        assertThat(input, is(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }



}
