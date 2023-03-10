package edu.jakobkg.calculatorbackend.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import edu.jakobkg.calculatorbackend.Model.Calculation;
import edu.jakobkg.calculatorbackend.Model.CalculationHistory;

/**
 * DBWrapper
 * En wrapper rundt bruker- og kalkulatorlogg-databasetabellene for programmet
 * Selve database-funksjonaliteten eksponeres ikke, bare høyere-nivå
 * abstraksjoner for å gjøre koden enklere å bruke
 */
public class DBWrapper {
    private final String getHistoryQuery = "select * from calculations where username = ?";
    private final String insertCalcQuery = "insert into calculations (expression, result, username) values (?, ?, ?)";
    private final String checkCredentialsQuery = "select * from users where username = ? and password = ?";
    private final Connection connection;

    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    public DBWrapper(Connection connection) {
        this.connection = connection;
    }

    /**
     * Oppretter en Connection til databasen og returnerer den. sti, brukernavn og
     * passord er hardkodede, fy fy
     * 
     * @return Connection til H2-databasen
     * @throws Exception - hvis connection ikke kunne opprettes
     */
    public static Connection createConnection() throws Exception {
        return DriverManager
                .getConnection(
                        "jdbc:h2:file:/home/jakob/code/idatt2105-fullstack/oving05/backend/data/db",
                        "sa",
                        "password");
    }

    /**
     * Sjekker om angitt brukernavn og passord finnes i bruker-databasen
     * 
     * @param username - brukernavnet som skal sjekkes
     * @param password - passordet som skal sjekkes
     * @return true om kombinasjonen finnes i databasen, false ellers
     * @throws SQLException - dersom en feil ved kommunikasjon med databsen oppstår
     */
    public boolean checkCreds(String username, String password) throws SQLException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(checkCredentialsQuery);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return true;
            }
            
            return false;
        } catch (Exception e) {
            logger.error("Kunne ikke sjekke kredentialer mot databasen");
            logger.error(e.toString());
            throw new SQLException();
        }
    }

    /**
     * Henter kalkulator-loggen til en angitt bruker
     * 
     * @param username - brukernavnet til brukeren hvis logg skal hentes
     * @return - en CalculationHistory med alle beregningene til brukeren
     * @throws SQLException - dersom en feil ved kommunikasjon med databsen oppstår
     */
    public CalculationHistory getHistory(String username) throws SQLException {
        logger.info("Henter loggen til " + username + " fra databasen");
        try {
            PreparedStatement statement = this.connection.prepareStatement(getHistoryQuery);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            List<Calculation> calculations = new ArrayList<Calculation>();

            while (result.next()) {
                calculations.add(new Calculation(result.getString("expression"), result.getDouble("result")));
            }

            return new CalculationHistory(calculations);
        } catch (Exception e) {
            logger.error("Kunne ikke hente logg!");
            logger.error(e.toString());
            throw new SQLException();
        }
    }

    /**
     * Legger til en ny beregning i den angitte brukerens logg
     * 
     * @param calculation - beregningen som skal plassereis i databasen
     * @param username    - brukeren hvis logg regnestykket skal legges til i
     * @throws SQLException - hvis noe går skeis under kommunikasjon med databasen
     */
    public void addToHistory(Calculation calculation, String username) throws SQLException {
        logger.info("Legger til regnestykke i loggen til " + username);
        try {
            PreparedStatement statement = this.connection.prepareStatement(insertCalcQuery);
            statement.setString(1, calculation.expression());
            statement.setDouble(2, calculation.result());
            statement.setString(3, username);

            statement.execute();
        } catch (Exception e) {
            logger.error("Kunne ikke skrive til logg-databasen!");
            logger.error(e.toString());
            throw new SQLException();
        }
    }
}
