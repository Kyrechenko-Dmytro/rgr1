package com.example;

public class DifferentiatorFactory {

    public static Differentiator create(FunctionParameters params) {
        return switch (params.getDifferentiationType()) {
            case NUMERICAL -> new NumericalDifferentiator(params.getStep());
            case SYMBOLIC -> new SymbolicDifferentiator();
        };
    }
}