package com.example;

import java.io.IOException;

/**
 * Відповідає за повний цикл обробки однієї функції:
 * обчислення → вивід на консоль → збереження у файл.
 *
 * Виділено з Main відповідно до принципу єдиної відповідальності (SRP):
 * Main тільки конфігурує та запускає, FunctionProcessor тільки обробляє.
 */
public class FunctionProcessor {

    private final FunctionEvaluator evaluator;

    public FunctionProcessor(FunctionEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    /**
     * Обчислює функцію, друкує перші рядки таблиці та зберігає результат у файл.
     *
     * @param f        функція
     * @param fileName ім'я вихідного файлу
     */
    public void process(Function f, String fileName) {
        FunctionData data = evaluator.evaluate(f);
        printPreview(data);
        saveToFile(data, fileName);
    }

    // ── Приватні допоміжні методи ───────────────────────────────────────────

    private void printPreview(FunctionData data) {
        System.out.printf("%-12s %-20s %-20s%n", "x", "f(x)", "f'(x)");
        System.out.println("-".repeat(52));

        int rows = Math.min(5, data.size());
        for (int i = 0; i < rows; i++) {
            System.out.printf("%-12.4f %-20.6f %-20.6f%n",
                    data.getXValues().get(i),
                    data.getFValues().get(i),
                    data.getDfValues().get(i));
        }
        if (data.size() > rows) {
            System.out.println("... (ще " + (data.size() - rows) + " рядків)");
        }
    }

    private void saveToFile(FunctionData data, String fileName) {
        try {
            new ResultWriter(fileName).write(data);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл " + fileName + ": " + e.getMessage());
        }
    }
}
