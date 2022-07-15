package ru.job4j.solid.srp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

import static ru.job4j.solid.srp.ReportEngine.DATE_FORMAT;

public class HTMLReport implements Report {
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private Store store;

    public HTMLReport(Store store) {
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
                .append("<h1>Name;Hired;Fired;Salary;</h1>").append(ln);
        for (Employee employee : store.findBy(filter)) {
            text.append("<h3>").append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(decimalFormat.format(employee.getSalary()))
                    .append(";</h3>")
                    .append(System.lineSeparator());
        }
        text.append("</body>").append(ln).append("</html>");
        return text.toString();
    }
}
