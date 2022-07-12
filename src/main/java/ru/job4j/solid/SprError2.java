package ru.job4j.solid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class SprError2 {
    private String string;
    private int i;

    public SprError2(String string, int i) {
        this.string = string;
        this.i = i;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SprError2 sprError2 = (SprError2) o;
        return i == sprError2.i && Objects.equals(string, sprError2.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string, i);
    }

    public boolean loadObjToBase(Connection connection) {
        boolean rsl = false;
        try (PreparedStatement pr = connection.prepareStatement("insert into user(name, age) values(?, ?)")) {
            pr.setString(1, this.string);
            pr.setInt(2, this.i);
            rsl = pr.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
