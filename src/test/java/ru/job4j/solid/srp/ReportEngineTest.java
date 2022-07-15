package ru.job4j.solid.srp;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.job4j.solid.srp.ReportEngine.DATE_FORMAT;

import org.junit.Test;

import java.time.ZonedDateTime;
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
    public void whenJSONGenerator() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JSONReport(store);
        StringBuilder expect = new StringBuilder();
        expect.append("{\"name\":\"Ivan\",\"hired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(now.get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(now.get(Calendar.MINUTE)).append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE)).append(",\"second\":")
                .append(now.get(Calendar.SECOND)).append("},\"salary\":100.0}");
        expect.append(System.lineSeparator());
        assertEquals(expect.toString(), engine.generate(em -> true));
    }

    @Test
    public void whenXMLTReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XMLReport(store);
        String ln = "\n";
        StringBuilder expect = new StringBuilder();
        String zonedDateTime = ZonedDateTime.ofInstant(now.toInstant(), now.getTimeZone()
                .toZoneId()).toString();
        zonedDateTime = zonedDateTime.substring(0, zonedDateTime.indexOf("["));
        expect.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(ln)
                .append("<Employees>").append(ln)
                .append("    <employees>").append(ln)
                .append("        <fired>").append(zonedDateTime).append("</fired>").append(ln)
                .append("        <hired>").append(zonedDateTime).append("</hired>").append(ln)
                .append("        <name>Ivan</name>").append(ln)
                .append("        <salary>100.0</salary>").append(ln)
                .append("    </employees>").append(ln)
                .append("</Employees>").append(ln);
        assertEquals(expect.toString(), engine.generate(em -> true));
    }
}