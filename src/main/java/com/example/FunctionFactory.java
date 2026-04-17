package com.example;

import java.io.IOException;

import com.example.*;

public class FunctionFactory {

    public static Function create(FunctionParameters params) throws IOException {
        return switch (params.getFunctionType()) {
            case ANALYTICAL_1 -> new AnalyticalFunction1();
            case ANALYTICAL_2 -> new AnalyticalFunction2(params.getA());
            case STRING -> new StringFunction(params.getExpression());
            case CSV -> CsvTableLoader.load(params.getCsvFilePath(), "CSV Function");
        };
    }

    public static Function analytical1() {
        return new AnalyticalFunction1();
    }

    public static Function analytical2(double a) {
        return new AnalyticalFunction2(a);
    }

    public static Function fromString(String expr) {
        return new StringFunction(expr);
    }
}