package edu.jakobkg.calculatorbackend.services;

import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import edu.jakobkg.calculatorbackend.Model.CalculationResult;

import org.slf4j.LoggerFactory;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Service
public class CalculatorService {

    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    public CalculationResult calculate(String expression) {
        Expression exp;

        try {
            exp = new ExpressionBuilder(expression).build();
        } catch (Exception e) {
            logger.warn("Kunne ikke konstruere Expression-objekt fra uttrykket, svarer med advarsel");
            return new CalculationResult(0., "Ugyldig uttrykk!");
        }

        if (exp.validate().isValid()) {
            logger.info("Uttrykket er gyldig, evaluerer det og svarer med status \"OK\"");
            return new CalculationResult(exp.evaluate(), "OK");
        } else {
            logger.warn("Uttrykket er ikke gyldig, svarer med en advarsel om dette");
            return new CalculationResult(0., "Ugyldig uttrykk");
        }
    }
}
