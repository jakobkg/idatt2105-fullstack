package edu.jakobkg.calculatorbackend.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.jakobkg.calculatorbackend.Model.Calculation;
import edu.jakobkg.calculatorbackend.Model.CalculationHistory;

public class DBWrapper {
    private final String getHistoryQuery = "select * from calculations where username = ?";
    private final String insertCalcQuery = "insert into calculations (expression, result, username) values (?, ?, ?)";
    private final String checkCredentialsQuery = "select * from users where username = ? and password = ?";
    private final Connection connection;

    public DBWrapper(Connection connection) {
        this.connection = connection;
    }

    public static Connection createConnection() throws Exception {
        return DriverManager
                .getConnection("jdbc:h2:file:/home/jakob/code/idatt2105-fullstack/oving05/backend/data/db", "sa", "password");
    }

    public boolean checkCreds(String username, String password) {
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
            return false;
        }
    }

    public CalculationHistory getHistory(String username) {
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
            return null;
        }
    }

    public void addToHistory(Calculation calculation, String username) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(insertCalcQuery);
            statement.setString(1, calculation.expression());
            statement.setDouble(2, calculation.result());
            statement.setString(3, username);

            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
