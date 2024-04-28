package org.example.insurancemanagementapplication.DAO;

import Entity.Dependant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DependantDAO {
    private static Connection connection;
    //establish JDBC connection
    public DependantDAO(Connection connection) {
        this.connection = connection;
    }

    public static Dependant authenticate(String id, String password) throws SQLException {
        PreparedStatement statement = null; //create SQL statements with placeholders for parameters
        ResultSet resultSet = null; //get result of SQL query
        Dependant dependant= null;

        try {
            String query = "SELECT * FROM beneficiaries WHERE user_type = 'dependent' AND id = ? AND password = ?"; //"?": actual values with be replaced later on
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                //If the ResultSet contains at least one row (i.e., the authentication is successful),
                // we create a new dependent object and set its properties based on the retrieved data.
                dependant = new Dependant();
                dependant.setId(resultSet.getString("id"));
                dependant.setPassword(resultSet.getString("password"));
                dependant.setFullName(resultSet.getString("full_name"));
                dependant.setAddress(resultSet.getString("address"));
                dependant.setEmail(resultSet.getString("email"));
                dependant.setPhoneNumber(resultSet.getString("phone_number"));
                dependant.setType(resultSet.getString("user_type"));
                //set policy holder?
                // set policy owner ?
                //set card number?



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
        return dependant;
    }
}
//How to optimize the code:
//Make a parent class (UserDAO)

//Other DAO classes (SystemAdminDAO, PolicyHolderDAO ,...) all extend from UserDAO
//same process, only over-write how a new object's attributes are added
