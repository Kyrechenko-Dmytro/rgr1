package com.example;

import java.util.ArrayList;

/**
 * Клас-контейнер для зберігання результатів обчислення функції та її похідної
 * на заданому відрізку з певним кроком.
 *
 * Зберігає масиви значень x, f(x) та f'(x) у вигляді ArrayList.
 */
public class FunctionData {

    /** Назва функції */
    private final String functionName;

    /** Список значень аргументу x */
    private final ArrayList<Double> xValues;

    /** Список значень функції f(x) */
    private final ArrayList<Double> fValues;

    /** Список значень похідної f'(x) */
    private final ArrayList<Double> dfValues;

    /**
     * Конструктор: ініціалізує порожні списки.
     *
     * @param functionName назва функції
     */
    public FunctionData(String functionName) {
        this.functionName = functionName;
        this.xValues = new ArrayList<>();
        this.fValues = new ArrayList<>();
        this.dfValues = new ArrayList<>();
    }

    /**
     * Додає нову точку з відповідними значеннями.
     *
     * @param x   значення аргументу
     * @param fx  значення функції
     * @param dfx значення похідної
     */
    public void addRow(double x, double fx, double dfx) {
        xValues.add(x);
        fValues.add(fx);
        dfValues.add(dfx);
    }

    /** @return список значень x */
    public ArrayList<Double> getXValues() {
        return xValues;
    }

    /** @return список значень f(x) */
    public ArrayList<Double> getFValues() {
        return fValues;
    }

    /** @return список значень f'(x) */
    public ArrayList<Double> getDfValues() {
        return dfValues;
    }

    /** @return кількість точок */
    public int size() {
        return xValues.size();
    }

    /** @return назва функції */
    public String getFunctionName() {
        return functionName;
    }
}