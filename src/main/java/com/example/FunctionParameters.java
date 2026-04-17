package com.example;

public class FunctionParameters {

    private FunctionType functionType;
    private DifferentiationType differentiationType;

    private double xStart;
    private double xEnd;
    private double step;

    private double a;
    private String expression;
    private String csvFilePath;

    public FunctionType getFunctionType() {
        return functionType;
    }

    public void setFunctionType(FunctionType functionType) {
        this.functionType = functionType;
    }

    public DifferentiationType getDifferentiationType() {
        return differentiationType;
    }

    public void setDifferentiationType(DifferentiationType differentiationType) {
        this.differentiationType = differentiationType;
    }

    public double getXStart() {
        return xStart;
    }

    public void setXStart(double xStart) {
        this.xStart = xStart;
    }

    public double getXEnd() {
        return xEnd;
    }

    public void setXEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }
}