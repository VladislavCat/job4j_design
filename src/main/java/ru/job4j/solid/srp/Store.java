package ru.job4j.solid.srp;

import ru.job4j.solid.srp.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    List<Employee> findBy(Predicate<Employee> filter);
}
