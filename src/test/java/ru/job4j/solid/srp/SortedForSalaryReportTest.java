package ru.job4j.solid.srp;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.Employee;
import ru.job4j.solid.MemStore;
import ru.job4j.solid.Report;
import ru.job4j.solid.SortedForSalaryReport;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SortedForSalaryReportTest {

    @Test
    public void whenDollarGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        Employee worker2 = new Employee("Grisha", now, now, 500);
        store.add(worker2);
        Employee worker3 = new Employee("Ilya", now, now, 250);
        store.add(worker3);
        Report engine = new SortedForSalaryReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name;Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        Assert.assertEquals(engine.generate(em -> true), expect.toString());
    }
}
