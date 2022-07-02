package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findElem(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findElem(value, comparator);
    }

    public <T> T findElem(List<T> value, Comparator<T> comparator) {
        value.sort(comparator);
        return value.get(0);
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));
        MaxMin mm = new MaxMin();
        System.out.println(mm.max(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        }));
        System.out.println(mm.max(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }));
    }
}