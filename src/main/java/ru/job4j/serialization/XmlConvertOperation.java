package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlConvertOperation {
    public static void main(String[] args) throws Exception {
        Operation div = new Operation(false,
                30, "/", new int[]{1, 2},
                new OperationType("division", "binary operation"));
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Operation.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(div, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Operation result = (Operation) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

