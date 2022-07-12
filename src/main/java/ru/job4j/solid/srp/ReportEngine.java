package ru.job4j.solid.srp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        String ln = System.lineSeparator();
        text.append("<!DOCTYPE html>").append(ln).append("<head>")
                .append("<meta charset=\"utf-8\">").append(ln)
                .append("<title>Employees Report</title>").append(ln)
                .append("</head>").append(ln).append("<body>").append(ln)
                .append("<h1>Name;Salary;</h1>").append(ln);
        List<Employee> employees = store.findBy(filter);
        employees.sort(((em1, em2) -> Double.compare(em2.getSalary(), em1.getSalary())));
        for (Employee employee : employees) {
            text.append("<h3>").append(employee.getName()).append(";")
                    .append(new DecimalFormat("#0.00").format(employee.getSalary() / 60).concat("$;</h3>"))
                    .append(System.lineSeparator());
        }
        text.append("</body>").append(ln).append("</html>");
        return text.toString();
    }
}