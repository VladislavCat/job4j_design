package ru.job4j.solid.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        final Gson gson = new GsonBuilder().create();
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            text.append(gson.toJson(employee));
            text.append(System.lineSeparator());
        }
        return text.toString();
    }
}
