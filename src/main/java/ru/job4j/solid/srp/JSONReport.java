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

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        worker = new Employee("Vasya", now, now, 202);
        store.add(worker);
        worker = new Employee("Sema", now, now, 3212);
        store.add(worker);
        Report engine = new JSONReport(store);
        System.out.println(engine.generate(em -> true));
    }
}
