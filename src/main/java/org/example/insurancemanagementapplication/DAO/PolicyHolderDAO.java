package org.example.insurancemanagementapplication.DAO;


import Entity.PolicyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PolicyHolderDAO {
    private Connection connection;
    //establish JDBC connection
    public PolicyHolderDAO(Connection connection) {
        this.connection = connection;
    }

    public PolicyHolder authenticate(String id, String password) throws SQLException {
        PreparedStatement statement = null; //create SQL statements with placeholders for parameters
        ResultSet resultSet = null; //get result of SQL query
        PolicyHolder policyHolder = null;

        try {
            String query = "SELECT * FROM insurance_surveyor WHERE id = ? AND password = ?"; //"?": actual values with be replaced later on
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                //If the ResultSet contains at least one row (i.e., the authentication is successful),
                // we create a new SystemAdmin object and set its properties based on the retrieved data.
                policyHolder = new PolicyHolder();
                policyHolder.setId(resultSet.getString("id"));
                policyHolder.setPassword(resultSet.getString("password"));
                policyHolder.setFullName(resultSet.getString("full_name"));
                policyHolder.setEmail(resultSet.getString("email"));
                policyHolder.setAddress(resultSet.getString("address"));
                policyHolder.setPhoneNumber(resultSet.getString("phone_number"));

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
        return policyHolder;
    }
}
//How to optimize the code:
//Make a parent class (UserDAO)

//Other DAO classes (SystemAdminDAO, PolicyHolderDAO ,...) all extend from UserDAO
//same process, only over-write how a new object's attributes are added
