package com.example;

/**
 * Клас для чисельного обчислення похідної функції.
 *
 * Використовується центральна формула за трьома точками (метод скінченних
 * різниць):
 * f'(x_i) ≈ (f(x_{i+1}) - f(x_{i-1})) / (2 * h)
 *
 * де h — крок диференціювання (точність).
 */
public class NumericalDifferentiator {

    /** Крок диференціювання (точність обчислення похідної) */
    private final double h;

    /**
     * Конструктор: приймає крок диференціювання h.
     *
     * @param h крок (точність), наприклад 0.05
     */
    public NumericalDifferentiator(double h) {
        if (h <= 0) {
            throw new IllegalArgumentException("Крок диференціювання h має бути більше 0!");
        }
        this.h = h;
    }

    /**
     * Обчислює чисельну похідну функції f у точці x за центральною формулою:
     * f'(x) ≈ (f(x + h) - f(x - h)) / (2 * h)
     *
     * @param f функція, що реалізує інтерфейс Function
     * @param x точка, у якій обчислюється похідна
     * @return наближене значення f'(x)
     */
    public double differentiate(Function f, double x) {
        // Центральна різницева формула за трьома точками
        double fRight = f.evaluate(x + h); // f(x + h)
        double fLeft = f.evaluate(x - h); // f(x - h)
        return (fRight - fLeft) / (2.0 * h);
    }

    /**
     * Повертає поточний крок диференціювання.
     *
     * @return значення h
     */
    public double getH() {
        return h;
    }
}
