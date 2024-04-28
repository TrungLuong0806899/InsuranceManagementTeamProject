package org.example.insurancemanagementapplication.DAO;


import Entity.InsuranceSurveyor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceSurveyorDAO {
    private Connection connection;
    //establish JDBC connection
    public InsuranceSurveyorDAO(Connection connection) {
        this.connection = connection;
    }

    public InsuranceSurveyor authenticate(String id, String password) throws SQLException {
        PreparedStatement statement = null; //create SQL statements with placeholders for parameters
        ResultSet resultSet = null; //get result of SQL query
        InsuranceSurveyor insuranceSurveyor = null;

        try {
            String query = "SELECT * FROM insurance_surveyor WHERE id = ? AND password = ?"; //"?": actual values with be replaced later on
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                //If the ResultSet contains at least one row (i.e., the authentication is successful),
                // we create a new SystemAdmin object and set its properties based on the retrieved data.
                insuranceSurveyor = new InsuranceSurveyor();
                insuranceSurveyor.setId(resultSet.getString("id"));
                insuranceSurveyor.setPassword(resultSet.getString("password"));
                insuranceSurveyor.setFullName(resultSet.getString("full_name"));
                insuranceSurveyor.setEmail(resultSet.getString("email"));
                insuranceSurveyor.setAddress(resultSet.getString("address"));
                insuranceSurveyor.setPhoneNumber(resultSet.getString("phone_number"));

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
        return insuranceSurveyor;
    }
}
//How to optimize the code:
//Make a parent class (UserDAO)

//Other DAO classes (SystemAdminDAO, PolicyHolderDAO ,...) all extend from UserDAO
//same process, only over-write how a new object's attributes are added
