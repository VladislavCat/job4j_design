package ru.job4j.solid.isp;

public class HardCoil implements Coil {

    @Override
    public boolean ignition(int voltage, int amperage) {
        return true;
    }

    @Override
    public String getMaterialCoreVein() {
        return "NcHr80";
    }

    @Override
    public String getMaterialWinding() {
        return "NcHr80";
    }
}
