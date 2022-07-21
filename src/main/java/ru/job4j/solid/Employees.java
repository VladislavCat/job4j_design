package ru.job4j.solid;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name = "Employees")
public class Employees {
    private List<Employee> employees = new ArrayList<>();

    public Employees() {

    }

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
