package com.example;

/**
 * Клас для обчислення значень функції та її чисельної похідної
 * на заданому відрізку [xStart, xEnd] із кроком step.
 *
 * Використовує об'єкт Function та NumericalDifferentiator.
 */
public class FunctionEvaluator {

    /** Ліва межа відрізку */
    private final double xStart;

    /** Права межа відрізку */
    private final double xEnd;

    /** Крок по x */
    private final double step;

    /** Диференціатор для обчислення похідної */
    private final NumericalDifferentiator differentiator;

    /**
     * Конструктор: задає відрізок, крок та диференціатор.
     *
     * @param xStart         початок відрізку
     * @param xEnd           кінець відрізку
     * @param step           крок по x
     * @param differentiator об'єкт для чисельного диференціювання
     */
    public FunctionEvaluator(double xStart, double xEnd, double step,
            NumericalDifferentiator differentiator) {
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.step = step;
        this.differentiator = differentiator;
    }

    /**
     * Обчислює значення функції та її похідної на відрізку.
     * Повертає об'єкт FunctionData з усіма точками.
     *
     * @param f функція для обчислення
     * @return об'єкт FunctionData з масивами x, f(x), f'(x)
     */
    public FunctionData evaluate(Function f) {
        FunctionData data = new FunctionData(f.getName());

        // Перебираємо x від xStart до xEnd із кроком step
        for (double x = xStart; x <= xEnd + 1e-9; x += step) {
            // Округлення для уникнення чисельних артефактів
            double xRounded = Math.round(x / step) * step;

            double fx = f.evaluate(xRounded); // значення функції
            double dfx = differentiator.differentiate(f, xRounded); // значення похідної

            data.addRow(xRounded, fx, dfx);
        }

        return data;
    }
}