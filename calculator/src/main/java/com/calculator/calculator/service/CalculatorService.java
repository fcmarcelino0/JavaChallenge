package com.calculator.calculator.service;

import com.challenge.javaCalculator.model.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Calculator calculate(Calculator request) {
        double res = switch (request.getOperation().toLowerCase()) {
            case "add" -> request.getA() + request.getB();
            case "subtract" -> request.getA() - request.getB();
            case "multiply" -> request.getA() * request.getB();
            case "divide" -> {
                if (request.getB() == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                yield request.getA() / request.getB();
            }
            default -> throw new IllegalArgumentException("Invalid operation: " + request.getOperation());
        };
        return new Calculator(request.getId(), res);
    }

}
