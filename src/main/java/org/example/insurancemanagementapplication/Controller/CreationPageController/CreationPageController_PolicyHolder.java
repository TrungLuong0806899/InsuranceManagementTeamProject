package org.example.insurancemanagementapplication.Controller.CreationPageController;

import Entity.InsuranceCard;
import Entity.PolicyHolder;
import Entity.PolicyOwner;
import Entity.User;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.example.insurancemanagementapplication.Interfaces.Controller;
import org.example.insurancemanagementapplication.Interfaces.CustomerCreateRemove;
import org.example.insurancemanagementapplication.Interfaces.CustomerUpdate;
import org.example.insurancemanagementapplication.Utility.InputValidator;
import org.example.insurancemanagementapplication.Utility.RepeatedCode;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author
 * @version ${}
 * @created 29/04/2024 11:50
 * @project InsuranceManagementTeamProject
 */
public class CreationPageController_PolicyHolder extends CreationPageController implements CustomerCreateRemove, CustomerUpdate, Initializable, Controller {

    private PolicyOwner policyOwner;
    @FXML
    private TextField lengthOfContractField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //See the CreationPageController class for this method
        setActionReturnButton();
        //when the controller is in update mode
        if (selectedUser != null){
            lengthOfContractField.setDisable(true);
            //See the CreationPageController class for this method
            fillingFormAuto();
            //See the CreationPageController class for this method
            setHandlerForSubmitButtonInUserUpdateMode();

        }
        //when the controller is in creation mode
        else {
            submitButton.setOnAction(event -> {
                String message = InputValidator.validatingUser("Policy Holder", entityManager, fullNameField.getText(), emailField.getText(), passwordField.getText(), addressField.getText(), phoneNumberField.getText(), passwordValidationField.getText());
                if (message.equals("Success")){
                    //See the RepeatedCode class for this method.
                    //This method generates an ID.
                    String id = RepeatedCode.idGenerate("PH");
                    String cardNumber = RepeatedCode.idGenerate("");
                    Date utilDate = new Date();
                    int lengthOfContract;
                    try{
                        lengthOfContract = Integer.parseInt(lengthOfContractField.getText());
                        Date expiryUtilDate = new Date(utilDate.getYear() + lengthOfContract - 1, utilDate.getMonth(), utilDate.getDay());
                        InsuranceCard insuranceCard = new InsuranceCard();
                        insuranceCard.setCardNumber(cardNumber);
                        insuranceCard.setExpirationDate(new java.sql.Date(expiryUtilDate.getTime()));
                        CustomerCreateRemove.createPolicyHolder(entityManager, id, insuranceCard, fullNameField.getText(), addressField.getText(), phoneNumberField.getText(), emailField.getText(), passwordField.getText(), policyOwner);

                    } catch (NumberFormatException exception){
                        errorContainer.setText("Length of contract must be in numerical format");
                    }
                }
                else {
                    errorContainer.setText(message);
                }

            });
        }


    }

    //Calling this constructor when the controller is to be open in creation mode
    public CreationPageController_PolicyHolder(EntityManager entityManager, User user, PolicyOwner policyOwner) {
       super(entityManager, user);
        this.policyOwner = policyOwner;
    }

    //Call this constructor when the controller is to be open in update mode
    public CreationPageController_PolicyHolder(EntityManager entityManager, User user, PolicyHolder policyHolder) {
        super(entityManager, user, policyHolder);
    }
}
