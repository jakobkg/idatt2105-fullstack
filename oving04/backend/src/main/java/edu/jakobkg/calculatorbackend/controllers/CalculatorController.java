package edu.jakobkg.calculatorbackend.controllers;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.jakobkg.calculatorbackend.models.CalculationRequest;
import edu.jakobkg.calculatorbackend.models.CalculationResult;
import edu.jakobkg.calculatorbackend.services.CalculatorService;

@RestController
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @CrossOrigin(origins = "*")
    @PostMapping("/calc")
    CalculationResult calculate(@RequestBody CalculationRequest request) {
        logger.info("Mottok forespørsel med regneuttrykk " + request.expression());

        CalculationResult result = calculatorService.calculate(request.expression());

        logger.info("Svarer med " + result.toString());

        return result;
    }
}
