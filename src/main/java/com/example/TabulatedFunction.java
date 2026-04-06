package com.example;

import java.util.ArrayList;

/**
 * Таблична функція: зберігає значення x і f(x) у вигляді ArrayList.
 * Значення функції обчислюється через лінійну інтерполяцію між вузлами таблиці.
 * Реалізує інтерфейс Function.
 *
 * Згідно з умовою, таблиця зберігає значення sin(x) — незалежна змінна
 * та значення функції зберігаються у відповідному класі у вигляді ArrayList.
 */
public class TabulatedFunction implements Function {

    /**
     * Внутрішній клас-запис, що зберігає одну точку таблиці (x, f(x)).
     */
    private static class TablePoint {
        double x; // значення аргументу
        double fx; // значення функції

        TablePoint(double x, double fx) {
            this.x = x;
            this.fx = fx;
        }
    }

    /** Таблиця значень функції, зберігається як ArrayList точок */
    private final ArrayList<TablePoint> table;

    /** Назва функції */
    private final String name;

    /**
     * Конструктор: приймає назву та порожній список — таблицю заповнюємо через
     * addPoint().
     *
     * @param name назва функції
     */
    public TabulatedFunction(String name) {
        this.name = name;
        this.table = new ArrayList<>();
    }

    /**
     * Додає нову точку (x, f(x)) до таблиці.
     * Точки мають додаватися у порядку зростання x для коректної інтерполяції.
     *
     * @param x  значення аргументу
     * @param fx значення функції
     */
    public void addPoint(double x, double fx) {
        table.add(new TablePoint(x, fx));
    }

    /**
     * Обчислює значення функції в точці x за допомогою лінійної інтерполяції.
     * Якщо x виходить за межі таблиці — повертає граничне значення.
     *
     * @param x аргумент функції
     * @return інтерпольоване значення f(x)
     * @throws IllegalStateException якщо таблиця порожня
     */
    @Override
    public double evaluate(double x) {
        if (table.isEmpty()) {
            throw new IllegalStateException("Таблиця значень функції порожня!");
        }

        int n = table.size();

        // Якщо x менше за перший вузол — повертаємо перше значення
        if (x <= table.get(0).x) {
            return table.get(0).fx;
        }

        // Якщо x більше за останній вузол — повертаємо останнє значення
        if (x >= table.get(n - 1).x) {
            return table.get(n - 1).fx;
        }

        // Шукаємо інтервал [xi, xi+1], де xi <= x < xi+1
        for (int i = 0; i < n - 1; i++) {
            TablePoint p1 = table.get(i);
            TablePoint p2 = table.get(i + 1);

            if (x >= p1.x && x <= p2.x) {
                // Лінійна інтерполяція: f(x) ≈ f(x_i) + (x - x_i) / (x_{i+1} - x_i) *
                // (f(x_{i+1}) - f(x_i))
                double t = (x - p1.x) / (p2.x - p1.x);
                return p1.fx + t * (p2.fx - p1.fx);
            }
        }

        // На випадок чисельних похибок
        return table.get(n - 1).fx;
    }

    /**
     * Повертає назву функції.
     *
     * @return назва таблиці
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Повертає кількість точок у таблиці.
     *
     * @return розмір таблиці
     */
    public int size() {
        return table.size();
    }

    /**
     * Повертає значення x за індексом у таблиці.
     *
     * @param index індекс точки
     * @return значення x
     */
    public double getX(int index) {
        return table.get(index).x;
    }

    /**
     * Повертає значення f(x) за індексом у таблиці.
     *
     * @param index індекс точки
     * @return значення f(x)
     */
    public double getFx(int index) {
        return table.get(index).fx;
    }
}