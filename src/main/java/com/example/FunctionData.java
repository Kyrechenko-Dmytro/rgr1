package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Незмінний контейнер результатів обчислення функції та її похідної.
 *
 * Публічний API повертає List (а не конкретний ArrayList) — це дозволяє
 * змінити внутрішню реалізацію без зміни клієнтського коду (DIP).
 * Списки повертаються через Collections.unmodifiableList, щоб запобігти
 * зовнішній модифікації стану об'єкта.
 */
public class FunctionData {

    private final String functionName;
    private final List<Double> xValues;
    private final List<Double> fValues;
    private final List<Double> dfValues;

    public FunctionData(String functionName) {
        this.functionName = functionName;
        this.xValues  = new ArrayList<>();
        this.fValues  = new ArrayList<>();
        this.dfValues = new ArrayList<>();
    }

    public void addRow(double x, double fx, double dfx) {
        xValues.add(x);
        fValues.add(fx);
        dfValues.add(dfx);
    }

    public List<Double> getXValues()  { return Collections.unmodifiableList(xValues);  }
    public List<Double> getFValues()  { return Collections.unmodifiableList(fValues);  }
    public List<Double> getDfValues() { return Collections.unmodifiableList(dfValues); }

    public int size() { return xValues.size(); }

    public String getFunctionName() { return functionName; }
}
