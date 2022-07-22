package ru.job4j.solid.isp;

public class MicroCoil implements Coil {
    @Override
    public boolean ignition(int voltage, int amperage) {
        return true;
    }

    @Override
    public String getMaterialCoreVein() {
        return "NiCr80";
    }

    /**
     * Данный метод придется заглушить так как в случае простых спиралей, у них нет обмотки;
     * Правильным решение было бы выделить два интерфейса
     * @return
     */
    @Override
    public String getMaterialWinding() {
        return null;
    }
}
