package ru.job4j.serialization;

import java.util.Arrays;

public class Operation {
    private final boolean flag;
    private final int priorityOperation;
    private final String symbolOperation;
    private final OperationType op;
    private final int[] numbers;

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
}
