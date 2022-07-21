package ru.job4j.gc;

import java.io.*;
import java.lang.instrument.Instrumentation;
import java.nio.charset.StandardCharsets;

public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();
    private static int counter = 0;

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / KB);
        System.out.printf("Total: %d%n", totalMemory / KB);
        System.out.printf("Max: %d%n", maxMemory / KB);
    }

    public static void main(String[] args) throws InterruptedException {
        info();
        for (int i = 0; i < 10000; i++) {
            new Person();
            counter++;
            if (i % 1000 == 0) {
                info();
            }
            Thread.sleep(10);
        }
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        GCDemo.counter = counter;
    }
}
