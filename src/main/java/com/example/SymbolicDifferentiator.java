package com.example;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class SymbolicDifferentiator implements Differentiator {

    @Override
    public double differentiate(Function f, double x) {
        if (!(f instanceof StringFunction sf)) {
            throw new IllegalArgumentException(
                    "Символьне диференціювання підтримується тільки для StringFunction");
        }

        Argument xArg = new Argument("x", x);
        Argument aArg = new Argument("a", sf.getParameterA());

        Expression derivative = new Expression(
                "der(" + sf.getExpressionText() + ", x)",
                xArg,
                aArg);

        return derivative.calculate();
    }
}