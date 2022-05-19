package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "op")
public class OperationType {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String type;

    public OperationType() { }

    public OperationType(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "OperationType{"
                + "name='" + name + '\''
                + ", type='" + type + '\''
                + '}';
    }
}
