package ru.job4j.solid.srp;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.job4j.solid.srp.ReportEngine.DATE_FORMAT;

import org.junit.Ignore;
import org.junit.Test;

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

    @Test
    public void whenHtmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        worker = new Employee("Vasya", now, now, 202);
        store.add(worker);
        worker = new Employee("Sema", now, now, 3212);
        store.add(worker);
        Report engine = new ReportHtmlEngine(store);
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                    .append("<!DOCTYPE html>").append(ln)
                    .append("<head><meta charset=\"utf-8\">").append(ln)
                    .append("<title>Employees Report</title>").append(ln)
                    .append("</head>").append(ln).append("<body>").append(ln)
                    .append("<h1>Name;Salary;</h1>").append(ln)
                    .append("<h3>Sema;53.53$;</h3>").append(ln)
                    .append("<h3>Vasya;3.37$;</h3>").append(ln)
                    .append("<h3>Ivan;1.67$;</h3>").append(ln)
                    .append("</body>").append(ln).append("</html>");
        assertEquals(expect.toString(), engine.generate(em -> true));
    }
}