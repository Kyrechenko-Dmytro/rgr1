package com.example;

/**
 * Друга аналітична функція: f(x) = exp(-a*x^2) * sin(x),
 * де a — параметр, що задається при створенні об'єкта.
 * Реалізує інтерфейс Function.
 */
public class AnalyticalFunction2 implements Function {

    /** Параметр a у формулі exp(-a*x^2) */
    private final double a;

    /**
     * Конструктор: приймає значення параметра a.
     *
     * @param a коефіцієнт при x^2 у показнику експоненти
     */
    public AnalyticalFunction2(double a) {
        this.a = a;
    }

    /**
     * Обчислює значення f(x) = exp(-a*x^2) * sin(x).
     *
     * @param x аргумент функції
     * @return значення функції в точці x
     */
    @Override
    public double evaluate(double x) {
        return Math.exp(-a * x * x) * Math.sin(x);
    }

    /**
     * Повертає назву функції з підставленим значенням a.
     *
     * @return рядок з описом функції
     */
    @Override
    public String getName() {
        return "f(x) = exp(-" + a + "*x^2) * sin(x)";
    }

    /**
     * Повертає значення параметра a.
     *
     * @return параметр a
     */
    public double getA() {
        return a;
    }
}