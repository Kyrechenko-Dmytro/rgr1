package com.example;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class StringFunction implements Function {

    private final String expressionText;
    private final double parameterA;
    private final Argument xArgument;
    private final Argument aArgument;
    private final Expression expression;

    public StringFunction(String expressionText) {
        this(expressionText, 1.0);
    }

    public StringFunction(String expressionText, double parameterA) {
        this.expressionText = expressionText;
        this.parameterA = parameterA;
        this.xArgument = new Argument("x", 0.0);
        this.aArgument = new Argument("a", parameterA);
        this.expression = new Expression(expressionText, xArgument, aArgument);
    }

    @Override
    public double evaluate(double x) {
        xArgument.setArgumentValue(x);
        aArgument.setArgumentValue(parameterA);
        return expression.calculate();
    }

    @Override
    public String getName() {
        return "f(x) = " + expressionText;
    }

    public String getExpressionText() {
        return expressionText;
    }

    public double getParameterA() {
        return parameterA;
    }
}