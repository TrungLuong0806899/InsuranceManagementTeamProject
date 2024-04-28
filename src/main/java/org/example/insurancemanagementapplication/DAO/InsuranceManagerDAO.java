package org.example.insurancemanagementapplication.DAO;

import Entity.InsuranceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceManagerDAO {
    private Connection connection;
    //establish JDBC connection
    public InsuranceManagerDAO(Connection connection) {
        this.connection = connection;
    }

    public InsuranceManager authenticate(String id, String password) throws SQLException {
        PreparedStatement statement = null; //create SQL statements with placeholders for parameters
        ResultSet resultSet = null; //get result of SQL query
        InsuranceManager insuranceManager = null;

        try {
            String query = "SELECT * FROM insurance_manager WHERE id = ? AND password = ?"; //"?": actual values with be replaced later on
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                //If the ResultSet contains at least one row (i.e., the authentication is successful),
                // we create a new SystemAdmin object and set its properties based on the retrieved data.
                insuranceManager = new InsuranceManager();
                insuranceManager.setId(resultSet.getString("id"));
                insuranceManager.setPassword(resultSet.getString("password"));
                insuranceManager.setFullName(resultSet.getString("full_name"));
                insuranceManager.setEmail(resultSet.getString("email"));
                insuranceManager.setAddress(resultSet.getString("address"));
                insuranceManager.setPhoneNumber(resultSet.getString("phone_number"));

                //list of insurance surveyors its manages?

            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
//return system admin object
        return insuranceManager;
    }
}
//How to optimize the code:
//Make a parent class (UserDAO)

//Other DAO classes (SystemAdminDAO, PolicyHolderDAO ,...) all extend from UserDAO
//same process, only over-write how a new object's attributes are added
