package org.example.insurancemanagementapplication.Controller;

import Entity.Beneficiaries;
import Entity.Claim;
import Entity.PolicyOwner;
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
import org.example.insurancemanagementapplication.DAO.PolicyOwnerDAO;
import org.example.insurancemanagementapplication.Interfaces.CustomerCreateRemove;
import org.example.insurancemanagementapplication.Interfaces.EmployeeAnalytics;
import org.example.insurancemanagementapplication.Interfaces.EmployeeCreateRemove;

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
public class PolicyOwnerController implements CustomerCreateRemove, EmployeeCreateRemove, Initializable, EmployeeAnalytics {
    private final EntityManager entityManager;
    private final PolicyOwner policyOwner;
    private Beneficiaries beneficiary;
    private Claim claim;

    @FXML
    private Button logInButton;
    @FXML
    private Label errorContainer;
    @FXML
    private TextField userIdField;
    @FXML
    private TextField passwordField;

    public PolicyOwnerController(PolicyOwner policyOwner, EntityManager entityManager) {
        this.policyOwner = policyOwner;
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton.setOnAction(event -> {
            String userId = userIdField.getText();
            String password = passwordField.getText();
           PolicyOwnerDAO policyOwnerDAO = new PolicyOwnerDAO((Connection) entityManager);
            try {
                PolicyOwner authenticatedPolicyOwner = policyOwnerDAO.authenticate(userId, password);
                if (authenticatedPolicyOwner != null) {
                    // Authentication successful, navigate to the System Admin dashboard
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoard_PolicyOwner.fxml"));
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

    public PolicyOwner getPolicyOwner() {
        return policyOwner;
    }

    public Beneficiaries getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiaries beneficiary) {
        this.beneficiary = beneficiary;

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
