package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMinTest {
    private Comparator<Integer> comparator;

    @Before
    public void initComparator() {
        comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
    }

    @Test
    public void whenFindMaxNum() {
        assertThat(new MaxMin().max(List.of(2, 18, 22, 109, 12, 1), comparator),
                is(109));
    }

    @Test
    public void whenFindMinNum() {
        assertThat(new MaxMin().min(List.of(2, 18, 22, 109, 12, 1), comparator),
                is(1));
    }
}
