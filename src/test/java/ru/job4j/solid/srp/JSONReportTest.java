package ru.job4j.solid.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class JSONReportTest {

    @Test
    public void whenJSONGenerator() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JSONReport(store);
        StringBuilder expect = new StringBuilder();
        expect.append("[{\"name\":\"Ivan\",\"hired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(now.get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(now.get(Calendar.MINUTE)).append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE)).append(",\"second\":")
                .append(now.get(Calendar.SECOND)).append("},\"salary\":100.0}]");
        assertEquals(expect.toString(), engine.generate(em -> true));
    }

}
