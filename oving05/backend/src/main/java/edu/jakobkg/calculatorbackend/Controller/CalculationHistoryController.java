package edu.jakobkg.calculatorbackend.Controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.jakobkg.calculatorbackend.DAO.DBWrapper;
import edu.jakobkg.calculatorbackend.Model.Calculation;
import edu.jakobkg.calculatorbackend.Model.CalculationHistory;

@RestController
@RequestMapping(value = "/history")
@EnableAutoConfiguration
@CrossOrigin
public class CalculationHistoryController {
    
    @GetMapping(value = "/{username}")
    public CalculationHistory getHistory(@PathVariable("username") String username) {
        DBWrapper db;
        try {
            db = new DBWrapper(DBWrapper.createConnection());

            CalculationHistory history = db.getHistory(username);

            return history;
        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
