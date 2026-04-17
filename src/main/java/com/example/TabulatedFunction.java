package com.example;

import java.util.Map;
import java.util.TreeMap;

public class TabulatedFunction implements Function {

    private final TreeMap<Double, Double> table = new TreeMap<>();
    private final String name;

    public TabulatedFunction(String name) {
        this.name = name;
    }

    public void addPoint(double x, double fx) {
        table.put(x, fx);
    }

    @Override
    public double evaluate(double x) {
        if (table.isEmpty()) {
            throw new IllegalStateException("Таблиця порожня");
        }

        Map.Entry<Double, Double> lower = table.floorEntry(x);
        Map.Entry<Double, Double> upper = table.ceilingEntry(x);

        if (lower == null) {
            return upper.getValue();
        }
        if (upper == null) {
            return lower.getValue();
        }
        if (lower.getKey().equals(upper.getKey())) {
            return lower.getValue();
        }

        double x1 = lower.getKey();
        double y1 = lower.getValue();
        double x2 = upper.getKey();
        double y2 = upper.getValue();

        double t = (x - x1) / (x2 - x1);
        return y1 + t * (y2 - y1);
    }

    @Override
    public String getName() {
        return name;
    }

    public int size() {
        return table.size();
    }
}