package org.example.insurancemanagementapplication.Controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Luong Thanh Trung
 * @version ${}
 * @created 27/04/2024 04:50
 * @project InsuranceManagementTeamProject
 */

//Controller Classes: get user input => call Input Validation and DAO inside
    // Input Validation Class: Validate input
    //DAO (data access object): authenticate user logins (verify if they are who they said to be). Avoid exposing database

public class MainController implements Initializable {
    EntityManager entityManager;

    public MainController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @FXML
    private TextField userIdField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private ChoiceBox<String> roleSelectionBox;
    @FXML
    private Button logInButton;
    @FXML
    private Label errorContainer;
    private String[] roles = {"System Admin", "Insurance Manager", "Insurance Surveyor", "Policy Owner", "Policy Holder", "Dependant"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Hello World");
        roleSelectionBox.getItems().addAll(roles);
        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String email = emailField.getText();
                String userId = userIdField.getText();
                String password = passwordField.getText();
                String role = roleSelectionBox.getValue();
                EntityTransaction transaction = entityManager.getTransaction();
                try{
                    transaction.begin();
                    /**
                     * ông lấy từ database một role mà có password = password và email = email, userId = userID.
                     * Lấy từ database nào thì tuỳ thuộc vào role
//                     * Nếu query không tìm được một entry thì set Text của errorContainer thành "User Not Found"
//                     * Nếu tìm được thì tuỳ thuộc vào role mà cast. Ví dụ nếu role = "System Admin" thì tạo một variable System Admin.
//                     * Intialize cái controller của cái user type vừa tạo bằng EntityManager và cái variable vừa tạo.
//                     * Nêú là System Admin thì initialize SystemAdminController
//                     * Load dashboard FXML tương ứng
//                     * set Controller cho dashboard đó
                     * **/

                }finally {
                    transaction.rollback();
                }

            }
        });

    }

    public void logIn(){
        String email = emailField.getText();
        String userId = userIdField.getText();
        String password = passwordField.getText();
        String role = roleSelectionBox.getValue();
        EntityTransaction transaction = entityManager.getTransaction();
        System.out.println(email);
        /**
        try{
            transaction.begin();
        }finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
         **/
    }

}
