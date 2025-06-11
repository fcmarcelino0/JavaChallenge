package com.calculator.calculator;

import com.calculator.calculator.service.CalculatorService;
import com.challenge.javaCalculator.model.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CalculatorApplicationTests {

    private final CalculatorService service = new CalculatorService();

    /*
    @Test
    void testSum() {
        Calculator result = service.sum(2, 3);
        assertEquals(5.0, result.getResult());
    }

    @Test
    void testSubtraction() {
        Calculator result = service.subtraction(10, 4);
        assertEquals(6.0, result.getResult());
    }

    @Test
    void testMultiplication() {
        Calculator result = service.multiplication(3, 5);
        assertEquals(15.0, result.getResult());
    }

    @Test
    void testDivision() {
        Calculator result = service.division(10, 2);
        assertEquals(5.0, result.getResult());
    }

    @Test
    void testDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            service.division(10, 0);
        });
        assertEquals("Division by zero", exception.getMessage());
    }
    */
}
