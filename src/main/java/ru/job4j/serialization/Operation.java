package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;
@XmlRootElement(name = "operation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Operation {
    @XmlAttribute
    private boolean flag;
    @XmlAttribute
    private int priorityOperation;
    @XmlAttribute
    private String symbolOperation;
    private OperationType op;
    @XmlElementWrapper(name = "numbers")
    @XmlElement(name = "number")
    private int[] numbers;

    public Operation() {

    }

    public Operation(boolean flag, int priorityOperation,
                     String symbolOperation, int[] numbers, OperationType op) {
        this.flag = flag;
        this.priorityOperation = priorityOperation;
        this.symbolOperation = symbolOperation;
        this.numbers = numbers;
        this.op = op;
    }

    public boolean isFlag() {
        return flag;
    }

    public int getPriorityOperation() {
        return priorityOperation;
    }

    public String getSymbolOperation() {
        return symbolOperation;
    }

    public OperationType getOp() {
        return op;
    }

    public int[] getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "Operation{"
                + "flag=" + flag
                + ", priorityOperation=" + priorityOperation
                + ", symbolOperation='" + symbolOperation + '\''
                + ", op=" + op
                + ", numbers=" + Arrays.toString(numbers)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Operation div = new Operation(false,
                30, "/", new int[]{1, 2},
                new OperationType("division", "binary operation"));
        JAXBContext context = JAXBContext.newInstance(Operation.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(div, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
}
