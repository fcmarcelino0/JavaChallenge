package com.challenge.javaCalculator.model;

public class Calculator {
    private String id;
    private double a;
    private double b;
    private String operation;
    private double result;
    private String error;

    public Calculator() {
    }

    public Calculator(String id,double a, double b, String operation, double result, String error) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.operation = operation;
        this.result = result;
        this.error = error;
    }

    public Calculator(String id, double result) {
        this.id = id;
        this.result = result;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null && !error.isEmpty();
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
