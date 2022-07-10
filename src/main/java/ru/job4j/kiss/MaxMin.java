package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return value.isEmpty() ? null : findElem(value, (o1, o2) -> comparator.compare(o1, o2) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return value.isEmpty() ? null : findElem(value, (o1, o2) -> comparator.compare(o1, o2) < 0);
    }

    public <T> T findElem(List<T> value, BiPredicate<T, T> comparator) {
        T rsl = value.get(0);
        for (T t : value) {
            if (comparator.test(t, rsl)) {
                rsl = t;
            }
        }
        return rsl;
    }
}