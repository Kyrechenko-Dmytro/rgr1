package com.example;

/**
 * Точка входу. Тільки конфігурує об'єкти та запускає обробку — більше нічого.
 */
public class Main {

    private static final double X_START = 1.5;
    private static final double X_END   = 6.5;
    private static final double STEP    = 0.05;

    public static void main(String[] args) {
        System.out.println("=== Диференціювання функцій ===");
        System.out.printf("Відрізок: [%.1f, %.1f], крок: %.2f%n%n", X_START, X_END, STEP);

        Differentiator  diff      = new NumericalDifferentiator(STEP);
        FunctionEvaluator evaluator = new FunctionEvaluator(X_START, X_END, STEP, diff);
        FunctionProcessor processor = new FunctionProcessor(evaluator);

        // Функція 1
        System.out.println("--- f(x) = exp(-x^2) * sin(x) ---");
        processor.process(new AnalyticalFunction1(), "results_function1.txt");

        // Функція 2 — три значення параметра a
        for (double a : new double[]{ 0.5, 1.0, 1.5 }) {
            System.out.printf("%n--- f(x) = exp(-%.1f*x^2) * sin(x) ---%n", a);
            processor.process(
                    new AnalyticalFunction2(a),
                    String.format("results_function2_a%.1f.txt", a));
        }

        // Функція 3 — таблична sin(x)
        System.out.println("\n--- sin(x) [таблична] ---");
        TabulatedFunction sinTable = TabulatedFunction.forSin(
                X_START - STEP, X_END + STEP, STEP);
        processor.process(sinTable, "results_function3_sin_tabulated.txt");

        System.out.println("\n=== Виконання завершено. ===");
    }
}
