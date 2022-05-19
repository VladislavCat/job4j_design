package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        JSONObject jsonOP = new JSONObject("{"
                + "\"name\":\"addition\","
                + "\"type\":\"binary operation\"}");
        List<Integer> list = new ArrayList<>(List.of(12, 16));
        JSONArray jsonNumbers = new JSONArray(list);
        final Operation operationTwo = new Operation(false, 21, "*", new int[]{2, 21},
                new OperationType("multiple", "binary operation"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag", operationTwo.isFlag());
        jsonObject.put("priorityOperation", operationTwo.getPriorityOperation());
        jsonObject.put("symbolOperation", operationTwo.getSymbolOperation());
        jsonObject.put("numbers", jsonNumbers);
        jsonObject.put("op", jsonOP);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(operationTwo).put("op", jsonOP));
    }
}
