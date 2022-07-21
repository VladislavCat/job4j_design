package ru.job4j.solid;

import java.text.DecimalFormat;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static ru.job4j.solid.ReportEngine.DATE_FORMAT;

public class DollarSalaryReport implements Report {
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.00");
    public static final Function<Double, Double> FUNCCONVERT = i -> (double) (i / 60);
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
                    .append(DECIMAL_FORMAT.format(FUNCCONVERT.apply(employee.getSalary()))).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
