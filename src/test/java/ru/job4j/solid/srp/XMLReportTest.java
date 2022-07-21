package ru.job4j.solid.srp;

import org.junit.Test;
import ru.job4j.solid.Employee;
import ru.job4j.solid.MemStore;
import ru.job4j.solid.Report;
import ru.job4j.solid.XMLReport;

import java.time.ZonedDateTime;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class XMLReportTest {
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
