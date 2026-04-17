package com.example;

/**
 * Обчислює значення функції та її похідної на відрізку [xStart, xEnd] з кроком step.
 *
 * Залежить від інтерфейсу Differentiator, а не від конкретного класу — DIP.
 */
public class FunctionEvaluator {

    private final double xStart;
    private final double xEnd;
    private final double step;
    private final Differentiator differentiator;

    public FunctionEvaluator(double xStart, double xEnd, double step,
                             Differentiator differentiator) {
        this.xStart        = xStart;
        this.xEnd          = xEnd;
        this.step          = step;
        this.differentiator = differentiator;
    }

    public FunctionData evaluate(Function f) {
        FunctionData data = new FunctionData(f.getName());

        for (double x = xStart; x <= xEnd + 1e-9; x += step) {
            double xr  = Math.round(x / step) * step;
            double fx  = f.evaluate(xr);
            double dfx = differentiator.differentiate(f, xr);
            data.addRow(xr, fx, dfx);
        }

        return data;
    }
}
