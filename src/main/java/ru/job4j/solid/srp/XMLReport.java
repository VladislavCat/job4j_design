package ru.job4j.solid.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XMLReport implements Report {
    private Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder rsl = new StringBuilder();
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            rsl.append(writer.getBuffer().toString());
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return rsl.toString();
    }
}
