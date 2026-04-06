package com.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Клас для збереження результатів обчислення функції та її похідної
 * у текстовий файл даних.
 *
 * Формат файлу:
 * # Назва функції
 * x f(x) f'(x)
 * 1.5000 0.123456 0.234567
 * ...
 */
public class FileWriter {

    /** Шлях до файлу для збереження */
    private final String filePath;

    /**
     * Конструктор: задає шлях до вихідного файлу.
     *
     * @param filePath шлях до файлу (наприклад, "results_f1.txt")
     */
    public FileWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Записує дані функції у текстовий файл.
     * Якщо файл існує — перезаписує його.
     *
     * @param data об'єкт FunctionData з обчисленими значеннями
     * @throws IOException якщо виникла помилка при записі
     */
    public void write(FunctionData data) throws IOException {
        // Використовуємо PrintWriter через BufferedWriter для ефективного запису
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new java.io.FileWriter(filePath)))) {

            // Заголовок з назвою функції
            writer.println("# Функція: " + data.getFunctionName());
            writer.println("# Стовпці: x | f(x) | f'(x)");
            writer.printf("%-15s %-20s %-20s%n", "x", "f(x)", "f'(x)");
            writer.println("-".repeat(55));

            // Записуємо кожен рядок таблиці
            for (int i = 0; i < data.size(); i++) {
                double x = data.getXValues().get(i);
                double fx = data.getFValues().get(i);
                double dfx = data.getDfValues().get(i);

                // Форматований вивід: 4 знаки після коми
                writer.printf("%-15.4f %-20.6f %-20.6f%n", x, fx, dfx);
            }

            writer.println("-".repeat(55));
            writer.println("# Кількість точок: " + data.size());
        }

        System.out.println("Дані збережено у файл: " + filePath);
    }

    /**
     * Повертає шлях до файлу.
     *
     * @return рядок з шляхом
     */
    public String getFilePath() {
        return filePath;
    }
}
