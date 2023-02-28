package edu.jakobkg.calculatorbackend.services;

import org.springframework.stereotype.Service;

import edu.jakobkg.calculatorbackend.models.CalculationResult;

@Service
public class CalculatorService {
    public CalculationResult calculate(String expression) {
        return new CalculationResult(5.0, "");
    }
}
