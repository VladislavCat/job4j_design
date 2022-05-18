package ru.job4j.serialization;

public class OperationType {
    private final String name;
    private final String type;

    public OperationType(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "OperationType{"
                + "name='" + name + '\''
                + ", type='" + type + '\''
                + '}';
    }
}
