package com.example;

import java.io.IOException;

/**
 * Головний клас програми.
 *
 * Виконує:
 * 1. Тестування трьох функцій на відрізку x ∈ [1.5, 6.5] із кроком 0.05
 * 2. Чисельне диференціювання кожної функції
 * 3. Збереження результатів у текстові файли
 *
 * Функції:
 * 1) f(x) = exp(-x^2) * sin(x)
 * 2) f(x) = exp(-a*x^2) * sin(x) для a = 0.5, 1.0, 1.5
 * 3) Таблична функція sin(x)
 */
public class Main {

    // Параметри відрізку та кроку (задані в умові)
    private static final double X_START = 1.5;
    private static final double X_END = 6.5;
    private static final double STEP = 0.05;

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        System.out.println("=== Програмне рішення для диференціювання функцій ===");
        System.out.printf("Відрізок: [%.1f, %.1f], крок: %.2f%n%n", X_START, X_END, STEP);

        // Створюємо диференціатор із кроком h = STEP
        NumericalDifferentiator differentiator = new NumericalDifferentiator(STEP);

        // Створюємо обчислювач на заданому відрізку
        FunctionEvaluator evaluator = new FunctionEvaluator(
                X_START, X_END, STEP, differentiator);

        // ──────────────────────────────────────────────────────────
        // ФУНКЦІЯ 1: f(x) = exp(-x^2) * sin(x)
        // ──────────────────────────────────────────────────────────
        System.out.println("--- Функція 1: f(x) = exp(-x^2) * sin(x) ---");
        processFunction(
                new AnalyticalFunction1(),
                evaluator,
                "results_function1.txt");

        // ──────────────────────────────────────────────────────────
        // ФУНКЦІЯ 2: f(x) = exp(-a*x^2) * sin(x) для різних a
        // ──────────────────────────────────────────────────────────
        double[] aValues = { 0.5, 1.0, 1.5 };

        for (double a : aValues) {
            System.out.printf("%n--- Функція 2: f(x) = exp(-%.1f*x^2) * sin(x) ----%n", a);
            processFunction(
                    new AnalyticalFunction2(a),
                    evaluator,
                    String.format("results_function2_a%.1f.txt", a));
        }

        // ──────────────────────────────────────────────────────────
        // ФУНКЦІЯ 3: таблична функція sin(x)
        // ──────────────────────────────────────────────────────────
        System.out.println("\n--- Функція 3: таблична функція sin(x) ---");

        // Завантажуємо таблицю sin(x) на розширеному відрізку
        // (розширюємо на STEP з кожного боку для коректного диференціювання)
        TabulatedFunction sinTable = TabulatedFunctionLoader.loadSinTable(
                X_START - STEP, X_END + STEP, STEP);

        processFunction(
                sinTable,
                evaluator,
                "results_function3_sin_tabulated.txt");

        System.out.println("\n=== Виконання завершено. Результати збережено у файли. ===");
    }

    /**
     * Допоміжний метод: обчислює функцію на відрізку та зберігає у файл.
     * Також виводить перші кілька рядків таблиці на консоль.
     *
     * @param f         функція (аналітична або таблична)
     * @param evaluator об'єкт для обчислення на відрізку
     * @param fileName  ім'я файлу для збереження
     */
    private static void processFunction(Function f, FunctionEvaluator evaluator,
            String fileName) {
        // Обчислюємо значення функції та похідної на відрізку
        FunctionData data = evaluator.evaluate(f);

        // Виводимо перші 5 рядків таблиці для перевірки
        System.out.printf("%-12s %-20s %-20s%n", "x", "f(x)", "f'(x)");
        System.out.println("-".repeat(52));

        int previewRows = Math.min(5, data.size());
        for (int i = 0; i < previewRows; i++) {
            System.out.printf("%-12.4f %-20.6f %-20.6f%n",
                    data.getXValues().get(i),
                    data.getFValues().get(i),
                    data.getDfValues().get(i));
        }

        if (data.size() > previewRows) {
            System.out.println("... (ще " + (data.size() - previewRows) + " рядків)");
        }

        // Зберігаємо повні результати у файл
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(data);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл " + fileName + ": " + e.getMessage());
        }
    }
}