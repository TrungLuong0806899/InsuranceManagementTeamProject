package org.example.insurancemanagementapplication.DAO;


import Entity.PolicyOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PolicyOwnerDAO {
    private Connection connection;
    //establish JDBC connection
    public PolicyOwnerDAO(Connection connection) {
        this.connection = connection;
    }

    public PolicyOwner authenticate(String id, String password) throws SQLException {
        PreparedStatement statement = null; //create SQL statements with placeholders for parameters
        ResultSet resultSet = null; //get result of SQL query
        PolicyOwner policyOwner= null;

        try {
            String query = "SELECT * FROM policy_owner WHERE id = ? AND password = ?"; //"?": actual values with be replaced later on
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                //If the ResultSet contains at least one row (i.e., the authentication is successful),
                // we create a new SystemAdmin object and set its properties based on the retrieved data.
                policyOwner = new PolicyOwner();
                policyOwner.setId(resultSet.getString("id"));
                policyOwner.setPassword(resultSet.getString("password"));
                policyOwner.setAddress(resultSet.getString("address"));
                policyOwner.setEmail(resultSet.getString("email"));
                policyOwner.setFullName(resultSet.getString("full_name"));
                policyOwner.setPhoneNumber(resultSet.getString("phone_number"));

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
        return policyOwner;
    }
}
//How to optimize the code:
//Make a parent class (UserDAO)

//Other DAO classes (SystemAdminDAO, PolicyHolderDAO ,...) all extend from UserDAO
//same process, only over-write how a new object's attributes are added

