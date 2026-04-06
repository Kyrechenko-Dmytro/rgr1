package com.example;

/**
 * Перша аналітична функція: f(x) = exp(-x^2) * sin(x).
 * Реалізує інтерфейс Function.
 */
public class AnalyticalFunction1 implements Function {

    /**
     * Обчислює значення f(x) = exp(-x^2) * sin(x).
     *
     * @param x аргумент функції
     * @return значення функції в точці x
     */
    @Override
    public double evaluate(double x) {
        return Math.exp(-x * x) * Math.sin(x);
    }

    /**
     * Повертає назву функції.
     *
     * @return рядок з описом функції
     */
    @Override
    public String getName() {
        return "f(x) = exp(-x^2) * sin(x)";
    }
}