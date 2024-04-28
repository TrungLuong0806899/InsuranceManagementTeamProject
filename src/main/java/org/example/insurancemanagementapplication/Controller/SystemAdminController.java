package org.example.insurancemanagementapplication.Controller;

import Entity.*;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.insurancemanagementapplication.DAO.SystemAdminDAO;
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
 * @created 27/04/2024 04:55
 * @project InsuranceManagementTeamProject
 */
public class SystemAdminController implements EmployeeCreateRemove, CustomerCreateRemove, Initializable, EmployeeAnalytics {
    private final EntityManager entityManager;
    private final SystemAdmin systemAdmin;
    private InsuranceSurveyor insuranceSurveyor;
    private InsuranceManager insuranceManager;
    private Customer customer;
    private Claim claim;

    @FXML
    private TextField userIdField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField passwordValidationField;
    @FXML
    private Button updateInfo;
    @FXML
    private Button addSurveyor;
    @FXML
    private Button addManager;
    @FXML
    private Button logInButton;

    @FXML
    private Label errorContainer;


    @FXML
    private TableView<InsuranceManager> managerTable;
    @FXML
    private TableColumn<InsuranceManager, String> managerId;
    @FXML
    private TableColumn<InsuranceManager, String> managerFullName;
    @FXML
    private TableColumn<InsuranceManager, TextField> managerAddress;
    @FXML
    private TableColumn<InsuranceManager, TextField> managerPhoneNumber;
    @FXML
    private TableColumn<InsuranceManager, TextField> managerEmail;
    @FXML
    private TableColumn<InsuranceManager, TextField> managerPassword;
    private SystemAdmin authenticatedAdmin;
   //constructor

    public SystemAdminController(EntityManager entityManager, SystemAdmin systemAdmin) {
        this.entityManager = entityManager;
        this.systemAdmin = systemAdmin;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton.setOnAction(event -> {
            String userId = userIdField.getText();
            String password = passwordField.getText();
            SystemAdminDAO systemAdminDao = new SystemAdminDAO((Connection) entityManager);
            try {
                SystemAdmin authenticatedAdmin = systemAdminDao.authenticate(userId, password);
                if (authenticatedAdmin != null) {
                    // Authentication successful, navigate to the System Admin dashboard
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoard_SystemAdmin.fxml"));
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




//Create DAO object for login authentication




    public EntityManager getEntityManager() {
        return entityManager;
    }

    public SystemAdmin getSystemAdmin() {
        return systemAdmin;
    }

    public InsuranceSurveyor getInsuranceSurveyor() {
        return insuranceSurveyor;
    }

    public void setInsuranceSurveyor(InsuranceSurveyor insuranceSurveyor) {
        this.insuranceSurveyor = insuranceSurveyor;
    }

    public InsuranceManager getInsuranceManager() {
        return insuranceManager;
    }

    public void setInsuranceManager(InsuranceManager insuranceManager) {
        this.insuranceManager = insuranceManager;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }


}
