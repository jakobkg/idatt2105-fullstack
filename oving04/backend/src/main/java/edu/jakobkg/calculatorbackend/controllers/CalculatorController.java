package edu.jakobkg.calculatorbackend.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.jakobkg.calculatorbackend.models.CalculationRequest;
import edu.jakobkg.calculatorbackend.models.CalculationResult;

@RestController
public class CalculatorController {
    enum StatusCode {
        OK,
        ERR
    }

    Logger logger = Logger.getLogger(this.getClass().getName());

    @CrossOrigin(origins = "*")
    @PostMapping("/calc")
    CalculationResult calculate(@RequestBody CalculationRequest request) {
        logger.log(Level.INFO, "Mottok regnestykke: " + String.format("%.2f %c %.2f", request.operands()[0], request.operator(), request.operands()[1]));
        try {
            float result = 0;

            if (request.operands().length != 2) {
                logger.log(Level.WARNING, "Uventet antall tall i utregningen");
                throw new IllegalArgumentException();
            }

            switch (request.operator()) {
                case '+':
                    result = request.operands()[0] + request.operands()[1];
                    break;
                case '-':
                    result = request.operands()[0] - request.operands()[1];
                    break;
                case '*':
                    result = request.operands()[0] * request.operands()[1];
                    break;
                case '/':
                    result = request.operands()[0] / request.operands()[1];
                    break;

                default:
                    logger.log(Level.WARNING, "Ukjent operasjon: " + request.operator());
                    throw new IllegalArgumentException();
                }
                logger.log(Level.INFO, String.format("Svarer med %.2f", result));
                return new CalculationResult(result, StatusCode.OK.toString());
        } catch (Exception e) {
            return new CalculationResult(null, StatusCode.ERR.toString());
        }
    }
}
