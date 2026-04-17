package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Таблична функція з лінійною інтерполяцією.
 *
 * Містить статичний фабричний метод forSin() замість окремого класу
 * TabulatedFunctionLoader — зменшує кількість класів без втрати гнучкості (GRASP: Creator).
 * Якщо знадобиться завантаження з файлу — достатньо додати ще один фабричний метод.
 */
public class TabulatedFunction implements Function {

    private static class TablePoint {
        final double x;
        final double fx;
        TablePoint(double x, double fx) { this.x = x; this.fx = fx; }
    }

    private final List<TablePoint> table;
    private final String name;

    public TabulatedFunction(String name) {
        this.name  = name;
        this.table = new ArrayList<>();
    }

    // ── Фабричний метод (замінює TabulatedFunctionLoader) ──────────────────

    /**
     * Створює таблицю значень sin(x) на відрізку [xStart, xEnd] з кроком step.
     */
    public static TabulatedFunction forSin(double xStart, double xEnd, double step) {
        TabulatedFunction func = new TabulatedFunction("sin(x) [таблична]");
        for (double x = xStart; x <= xEnd + 1e-9; x += step) {
            double xr = Math.round(x / step) * step;
            func.addPoint(xr, Math.sin(xr));
        }
        System.out.println("Таблицю sin(x) завантажено: " + func.size() + " точок.");
        return func;
    }

    /**
     * Створює таблицю з готових масивів (наприклад, зчитаних з файлу).
     */
    public static TabulatedFunction fromArrays(String name, double[] x, double[] fx) {
        if (x.length != fx.length) {
            throw new IllegalArgumentException("Розміри масивів x та f(x) мають збігатися!");
        }
        TabulatedFunction func = new TabulatedFunction(name);
        for (int i = 0; i < x.length; i++) {
            func.addPoint(x[i], fx[i]);
        }
        return func;
    }

    // ── Основний API ────────────────────────────────────────────────────────

    public void addPoint(double x, double fx) {
        table.add(new TablePoint(x, fx));
    }

    @Override
    public double evaluate(double x) {
        if (table.isEmpty()) throw new IllegalStateException("Таблиця порожня!");

        int n = table.size();
        if (x <= table.get(0).x)     return table.get(0).fx;
        if (x >= table.get(n - 1).x) return table.get(n - 1).fx;

        for (int i = 0; i < n - 1; i++) {
            TablePoint p1 = table.get(i);
            TablePoint p2 = table.get(i + 1);
            if (x >= p1.x && x <= p2.x) {
                double t = (x - p1.x) / (p2.x - p1.x);
                return p1.fx + t * (p2.fx - p1.fx);
            }
        }
        return table.get(n - 1).fx;
    }

    @Override
    public String getName() { return name; }

    public int    size()           { return table.size(); }
    public double getX(int index)  { return table.get(index).x;  }
    public double getFx(int index) { return table.get(index).fx; }
}
