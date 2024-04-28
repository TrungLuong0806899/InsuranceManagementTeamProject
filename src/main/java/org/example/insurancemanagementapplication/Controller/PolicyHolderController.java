package org.example.insurancemanagementapplication.Controller;

import Entity.Claim;
import Entity.Dependant;
import Entity.PolicyHolder;
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
import org.example.insurancemanagementapplication.DAO.PolicyHolderDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Luong Thanh Trung
 * @version ${}
 * @created 27/04/2024 04:54
 * @project InsuranceManagementTeamProject
 */
public class PolicyHolderController implements Initializable {
    private final EntityManager entityManager;
    private final PolicyHolder policyHolder;
    private Dependant dependant;
    private Claim claim;

    public PolicyHolderController(PolicyHolder policyHolder, EntityManager entityManager) {
        this.policyHolder = policyHolder;
        this.entityManager = entityManager;
    }

    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public Dependant getDependant() {
        return dependant;
    }

    public void setDependant(Dependant dependant) {
        this.dependant = dependant;
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
    @FXML
    private Button logInButton;
    @FXML
    private Label errorContainer;
    @FXML
    private TextField userIdField;
    @FXML
    private TextField passwordField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton.setOnAction(event -> {
            String userId = userIdField.getText();
            String password = passwordField.getText();
            PolicyHolderDAO PolicyHolderDAO = new PolicyHolderDAO((Connection) entityManager);
            try {
                PolicyHolder authenticatedPolicyHolder = PolicyHolderDAO.authenticate(userId, password);
                if (authenticatedPolicyHolder != null) {
                    // Authentication successful, navigate to the System Admin dashboard
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoard_PolicyHolder.fxml"));
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
}
