package ru.job4j.solid.isp;

public interface Coil {
    boolean ignition(int voltage, int amperage);
    String getMaterialCoreVein();
    String getMaterialWinding();
}
