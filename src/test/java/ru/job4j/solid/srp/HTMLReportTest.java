package ru.job4j.solid.srp;

import org.junit.Test;
import ru.job4j.solid.Employee;
import ru.job4j.solid.HTMLReport;
import ru.job4j.solid.MemStore;
import ru.job4j.solid.Report;

import java.util.Calendar;
import static ru.job4j.solid.ReportEngine.DATE_FORMAT;

import static org.junit.Assert.assertEquals;

public class HTMLReportTest {
    @Test
    public void whenHtmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Sema", now, now, 3212);
        store.add(worker);
        Report engine = new HTMLReport(store);
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>").append(ln)
                .append("<head><meta charset=\"utf-8\">").append(ln)
                .append("<title>Employees Report</title>").append(ln)
                .append("</head>").append(ln).append("<body>").append(ln)
                .append("<h1>Name;Hired;Fired;Salary;</h1>").append(ln)
                .append("<h3>Sema;").append((DATE_FORMAT.format(now.getTime()))).append(";")
                .append((DATE_FORMAT.format(now.getTime()))).append(";")
                .append(worker.getSalary())
                .append(";</h3>").append(ln)
                .append("</body>").append(ln).append("</html>");
        assertEquals(expect.toString(), engine.generate(em -> true));
    }
}
