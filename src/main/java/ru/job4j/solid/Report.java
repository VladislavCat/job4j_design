package ru.job4j.solid;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}
