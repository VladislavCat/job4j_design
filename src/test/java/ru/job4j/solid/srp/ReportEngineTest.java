package ru.job4j.solid.srp;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.job4j.solid.ReportEngine.DATE_FORMAT;

import org.junit.Test;
import ru.job4j.solid.Employee;
import ru.job4j.solid.MemStore;
import ru.job4j.solid.Report;
import ru.job4j.solid.ReportEngine;

import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}