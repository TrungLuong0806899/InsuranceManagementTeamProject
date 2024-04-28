package org.example.insurancemanagementapplication.Controller;

import Entity.Claim;
import Entity.Dependant;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.insurancemanagementapplication.DAO.DependantDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Luong Thanh Trung
 * @version ${}
 * @created 27/04/2024 04:53
 * @project InsuranceManagementTeamProject
 */
public class DependantController implements Initializable {
    private final EntityManager entityManager;
    private final Dependant dependant;
    private Claim claim;

    public DependantController(Dependant dependant, EntityManager entityManager) {
        this.dependant = dependant;
        this.entityManager = entityManager;
    }
    @FXML
    private Button logInButton;

    @FXML
    private Label errorContainer;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField userIdField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton.setOnAction(event -> {
            String userId = userIdField.getText();
            String password = passwordField.getText();
            DependantDAO dependantDAO = new DependantDAO((Connection) entityManager);;

            try {
                Dependant authenticatedDependent = DependantDAO.authenticate(userId, password);
                if (authenticatedDependent != null) {
                    // Authentication successful, navigate to the System Admin dashboard
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoard_Dependant.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) logInButton.getScene().getWindow(); // Get the current stage
                    stage.setScene(scene);
                    stage.show();
                } else {
                    // Authentication failed, display error message
                    errorContainer.setText("User not found");
                }
            } catch (SQLException e) {
                // Handle SQLException
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Dependant getDependant() {
        return dependant;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
