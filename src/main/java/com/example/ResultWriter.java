package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Зберігає FunctionData у текстовий файл.
 *
 * Перейменовано з FileWriter → ResultWriter, щоб уникнути конфлікту
 * з java.io.FileWriter та не плутати читача коду.
 */
public class ResultWriter {

    private final String filePath;

    public ResultWriter(String filePath) {
        this.filePath = filePath;
    }

    public void write(FunctionData data) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            pw.println("# Функція: " + data.getFunctionName());
            pw.println("# Стовпці: x | f(x) | f'(x)");
            pw.printf("%-15s %-20s %-20s%n", "x", "f(x)", "f'(x)");
            pw.println("-".repeat(55));

            for (int i = 0; i < data.size(); i++) {
                pw.printf("%-15.4f %-20.6f %-20.6f%n",
                        data.getXValues().get(i),
                        data.getFValues().get(i),
                        data.getDfValues().get(i));
            }

            pw.println("-".repeat(55));
            pw.println("# Кількість точок: " + data.size());
        }
        System.out.println("Дані збережено у файл: " + filePath);
    }

    public String getFilePath() { return filePath; }
}
