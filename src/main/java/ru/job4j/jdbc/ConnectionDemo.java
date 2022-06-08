package ru.job4j.jdbc;


import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Map<String, String> properties = new Config("app.properties").load();
        Class.forName(properties.get("driver"));
        try (Connection connection = DriverManager.getConnection(properties.get("url"),
                properties.get("login"), properties.get("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
