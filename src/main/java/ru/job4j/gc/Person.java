package ru.job4j.gc;

public class Person {

    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {

    };

    @Override
    protected void finalize() throws Throwable {
        System.out.println(GCDemo.getCounter());
        GCDemo.setCounter(GCDemo.getCounter() - 1);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}