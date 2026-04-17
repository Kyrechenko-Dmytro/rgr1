package com.example;

/**
 * Чисельний диференціатор за центральною формулою:
 * f'(x) ≈ (f(x + h) - f(x - h)) / (2h)
 *
 * Реалізує інтерфейс Differentiator — нові методи диференціювання
 * можна додавати без зміни існуючого коду (OCP).
 */
public class NumericalDifferentiator implements Differentiator {

    private final double h;

    /**
     * @param h крок диференціювання (має бути > 0)
     */
    public NumericalDifferentiator(double h) {
        if (h <= 0) {
            throw new IllegalArgumentException("Крок диференціювання h має бути більше 0!");
        }
        this.h = h;
    }

    @Override
    public double differentiate(Function f, double x) {
        return (f.evaluate(x + h) - f.evaluate(x - h)) / (2.0 * h);
    }

    public double getH() {
        return h;
    }
}
