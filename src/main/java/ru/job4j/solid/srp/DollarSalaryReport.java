package ru.job4j.solid.srp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static ru.job4j.solid.srp.ReportEngine.DATE_FORMAT;

public class DollarSalaryReport implements Report {
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private final Function<Double, Double> funcConvert = i -> (double) (i / 60);
    private Store store;

    public DollarSalaryReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees.sort(((em1, em2) -> Double.compare(em2.getSalary(), em1.getSalary())));
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(decimalFormat.format(funcConvert.apply(employee.getSalary()))).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
