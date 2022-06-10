package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"), properties.getProperty("password"));
    }

    public void createTable(String tableName) {
        String sql = "create table if not exists " + tableName
                + " (id serial primary key);";
        executeCommand(sql);
    }

    public void dropTable(String tableName) {
        String sql = "drop table " + tableName;
        executeCommand(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = "alter table " + tableName + " add column " + columnName + " " + type;
        executeCommand(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = "alter table " + tableName + " drop column " + columnName;
        executeCommand(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = "alter table " + tableName + " rename column " + columnName + " to " + newColumnName;
        executeCommand(sql);
    }

    private void executeCommand(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        try (TableEditor tr = new TableEditor(properties)) {
            tr.createTable("test_table");
            System.out.println(getTableScheme(tr.connection, "test_table"));
            tr.addColumn("test_table", "name_column", "varchar(30)");
            System.out.println(getTableScheme(tr.connection, "test_table"));
            tr.renameColumn("test_table", "name_column", "clear_name");
            System.out.println(getTableScheme(tr.connection, "test_table"));
            tr.dropColumn("test_table", "clear_name");
            System.out.println(getTableScheme(tr.connection, "test_table"));
            tr.dropTable("test_table");
        }
    }
}
