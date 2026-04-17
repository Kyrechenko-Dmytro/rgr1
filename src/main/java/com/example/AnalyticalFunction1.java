package com.example;

/** f(x) = exp(-x^2) * sin(x) */
public class AnalyticalFunction1 implements Function {

    @Override
    public double evaluate(double x) {
        return Math.exp(-x * x) * Math.sin(x);
    }

    @Override
    public String getName() {
        return "f(x) = exp(-x^2) * sin(x)";
    }
}
