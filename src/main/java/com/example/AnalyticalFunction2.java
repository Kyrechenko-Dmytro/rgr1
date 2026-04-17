package com.example;

/** f(x) = exp(-a*x^2) * sin(x), де a — параметр */
public class AnalyticalFunction2 implements Function {

    private final double a;

    public AnalyticalFunction2(double a) {
        this.a = a;
    }

    @Override
    public double evaluate(double x) {
        return Math.exp(-a * x * x) * Math.sin(x);
    }

    @Override
    public String getName() {
        return "f(x) = exp(-" + a + "*x^2) * sin(x)";
    }

    public double getA() {
        return a;
    }
}
