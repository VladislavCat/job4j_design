package ru.job4j.solid.srp;

import ru.job4j.solid.srp.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}
