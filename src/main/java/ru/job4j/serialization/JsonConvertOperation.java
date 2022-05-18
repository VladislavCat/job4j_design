package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConvertOperation {
    public static void main(String[] args) {
        final Operation operation = new Operation(true, 1, "+", new int[]{12, 11},
                new OperationType("addition", "binary operation"));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(operation));
        final String operationJson =
                "{"
                        + "\"flag\":true,"
                        + "\"priorityOperation\":\"1\","
                        + "\"symbolOperation\":\"+\","
                        + "\"numbers\":"
                        + "[12, 11],"
                        + "\"op\":"
                        + "{"
                        + "\"name\":\"addition\","
                        + "\"type\":\"binary operation\""
                        + "}"
                        + "}";
        final Operation operationMod = gson.fromJson(operationJson, Operation.class);
        System.out.println(operationMod);
    }

}
