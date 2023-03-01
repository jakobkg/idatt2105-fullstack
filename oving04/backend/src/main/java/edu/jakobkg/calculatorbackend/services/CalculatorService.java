package edu.jakobkg.calculatorbackend.services;

import org.springframework.stereotype.Service;

import edu.jakobkg.calculatorbackend.models.CalculationResult;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Service
public class CalculatorService {
    public CalculationResult calculate(String expression) {
        Expression e = new ExpressionBuilder(expression).build();

        if (e.validate().isValid()) {
            //LOGG
            return new CalculationResult(e.evaluate(), "OK");
        } else {
            //LOGG
            return new CalculationResult(0., "Invalid expression");
        }
    }
}
