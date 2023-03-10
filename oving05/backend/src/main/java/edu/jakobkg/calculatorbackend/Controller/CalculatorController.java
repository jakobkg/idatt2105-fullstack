package edu.jakobkg.calculatorbackend.Controller;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jakobkg.calculatorbackend.DAO.DBWrapper;
import edu.jakobkg.calculatorbackend.Model.Calculation;
import edu.jakobkg.calculatorbackend.Model.CalculationRequest;
import edu.jakobkg.calculatorbackend.Model.CalculationResult;
import edu.jakobkg.calculatorbackend.services.CalculatorService;

@CrossOrigin
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/calc")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @CrossOrigin(origins = "*")
    @PostMapping(value = "")
    CalculationResult calculate(@RequestBody CalculationRequest request) {
        logger.info("Mottok forespørsel med regneuttrykk " + request.expression());

        CalculationResult result = calculatorService.calculate(request.expression());

        DBWrapper db;
        try {
            db = new DBWrapper(DBWrapper.createConnection());
            db.addToHistory(new Calculation(request.expression(), result.result()), "jakob");
        } catch (Exception e) {
            logger.warn("Kunne ikke skrive resultat til databasen");
            logger.warn(e.toString());
        }

        logger.info("Svarer med " + result.toString());

        return result;
    }
}
