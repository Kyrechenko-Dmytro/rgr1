package com.example;

/**
 * Клас для завантаження (ініціалізації) таблиці значень функції sin(x).
 *
 * Згідно з умовою задачі, третя функція задана табличним чином:
 * незалежна змінна та значення sin(x) зберігаються у текстовому файлі,
 * але у даному класі таблиця формується програмно для демонстрації.
 *
 * Таблиця зберігається у вигляді об'єкта TabulatedFunction (ArrayList).
 */
public class TabulatedFunctionLoader {

    /**
     * Створює та повертає таблицю значень функції sin(x)
     * на відрізку [xStart, xEnd] із кроком step.
     *
     * @param xStart початок відрізку
     * @param xEnd   кінець відрізку
     * @param step   крок таблиці
     * @return об'єкт TabulatedFunction із заповненою таблицею
     */
    public static TabulatedFunction loadSinTable(double xStart, double xEnd, double step) {
        // Створюємо таблицю з назвою "sin(x) [таблична]"
        TabulatedFunction func = new TabulatedFunction("sin(x) [таблична]");

        // Заповнюємо таблицю значеннями sin(x)
        for (double x = xStart; x <= xEnd + 1e-9; x += step) {
            double xRounded = Math.round(x / step) * step;
            double fx = Math.sin(xRounded); // значення sin(x)
            func.addPoint(xRounded, fx);
        }

        System.out.println("Таблицю sin(x) завантажено: " + func.size() + " точок.");
        return func;
    }

    /**
     * Альтернативний метод: завантажує таблицю з масивів x[] та fx[].
     * Може використовуватися, якщо дані зчитуються з файлу.
     *
     * @param name назва функції
     * @param x    масив значень аргументу
     * @param fx   масив значень функції
     * @return об'єкт TabulatedFunction
     * @throws IllegalArgumentException якщо розміри масивів не збігаються
     */
    public static TabulatedFunction loadFromArrays(String name, double[] x, double[] fx) {
        if (x.length != fx.length) {
            throw new IllegalArgumentException(
                    "Розміри масивів x та f(x) мають збігатися!");
        }

        TabulatedFunction func = new TabulatedFunction(name);
        for (int i = 0; i < x.length; i++) {
            func.addPoint(x[i], fx[i]);
        }

        return func;
    }
}