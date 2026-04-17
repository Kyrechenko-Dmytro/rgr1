package com.example;

import org.mariuszgromada.math.mxparser.License;

public class Main {

    public static void main(String[] args) throws Exception {
        License.iConfirmNonCommercialUse("Student project");

        FunctionParameters parameters = new FunctionParameters();
        parameters.setFunctionType(FunctionType.STRING);
        parameters.setDifferentiationType(DifferentiationType.NUMERICAL);
        parameters.setExpression("exp(-x^2)*sin(x)");
        parameters.setA(1.0);
        parameters.setXStart(0.0);
        parameters.setXEnd(5.0);
        parameters.setStep(0.1);
        parameters.setCsvFilePath("data.csv");

        Function function = FunctionFactory.create(parameters);
        Differentiator differentiator = DifferentiatorFactory.create(parameters);

        FunctionEvaluator evaluator = new FunctionEvaluator(
                parameters.getXStart(),
                parameters.getXEnd(),
                parameters.getStep(),
                differentiator);

        FunctionProcessor processor = new FunctionProcessor(evaluator);
        processor.process(function, "result.txt");

        System.out.println("Готово");
        System.out.println("Для графічного інтерфейсу запускай PlotApp.");
    }
}