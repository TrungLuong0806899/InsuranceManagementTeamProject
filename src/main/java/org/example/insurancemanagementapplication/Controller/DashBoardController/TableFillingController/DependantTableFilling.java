package org.example.insurancemanagementapplication.Controller.DashBoardController.TableFillingController;

import Entity.Customer;
import Entity.Dependant;
import Entity.SystemAdmin;
import Entity.User;
import jakarta.persistence.EntityManager;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.insurancemanagementapplication.Controller.CreationPageController.CreationPageController_Dependant;
import org.example.insurancemanagementapplication.Interfaces.CustomerCreateRemove;
import org.example.insurancemanagementapplication.MainEntryPoint;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Luong Thanh Trung
 * @version ${}
 * @created 01/05/2024 15:33
 * @project InsuranceManagementTeamProject
 */
public class DependantTableFilling extends ClaimTableFilling {
    @FXML
    private TableView<Dependant> dependantTable;
    @FXML
    private TableColumn<Dependant, String> dependantId;
    @FXML
    private TableColumn<Dependant, String> dependantFullName;
    @FXML
    private TableColumn<Dependant, String> dependantAddress;
    @FXML
    private TableColumn<Dependant, String> dependantPhoneNumber;
    @FXML
    private TableColumn<Dependant, String> dependantEmail;
    @FXML
    private TableColumn<Dependant, String> dependantPassword;
    @FXML
    private TableColumn<Dependant, String> policyOwnerDependantTable;
    @FXML
    private TableColumn<Dependant, String> cardNumberDependantTable;
    @FXML
    private TableColumn<Dependant, Button> dependantUpdateInfoButton;
    @FXML
    private TableColumn<Dependant, Button> dependantAddClaimButton;

    @FXML
    private TableColumn<Dependant, Button> dependantRemoveButton;
    @FXML
    private TableColumn<Dependant, String> policyHolderDependantTable;
    @FXML
    private TextField dependantSearchField;

    public void fillingDependantTable(EntityManager entityManager, User user, List<Dependant> dependants, ObservableList<Dependant> dependantObservableList){
        ListIterator<Dependant> dependantListIterator = dependants.listIterator();
        while (dependantListIterator.hasNext()){
            Dependant dependant = dependantListIterator.next();
            Button buttonUpdateInfo = new Button();
            Button buttonAddClaim = new Button();
            Button buttonRemove = new Button();
            if (user instanceof SystemAdmin || user instanceof Customer){
                buttonUpdateInfo.setText("Update Info");
                buttonUpdateInfo.setOnAction(event -> {
                    CreationPageController_Dependant creationPageControllerDependant = new CreationPageController_Dependant(entityManager, user, dependant);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(MainEntryPoint.class.getResource("DependantCreationPage.fxml"));
                    fxmlLoader.setController(creationPageControllerDependant);
                    try {
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = (Stage) buttonUpdateInfo.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                buttonRemove.setText("Remove");
                dependant.setRemoveButton(buttonRemove);
                buttonRemove.setOnAction(event -> {
                    CustomerCreateRemove.removeDependant(entityManager, dependant );
                });

                buttonUpdateInfo.setUserData(dependant);
                dependant.setUpdateInfoButton(buttonUpdateInfo);


                if (!(user instanceof SystemAdmin)){
                    buttonAddClaim.setText("Add Claim");
                    dependant.setAddClaimButton(buttonAddClaim);
                }

                dependantObservableList.add(dependant);

            }


        }
        FilteredList<Dependant> filteredDependantList = new FilteredList<>(dependantObservableList, b -> true);
        dependantSearchField.textProperty().addListener((observable, oldValue, newValue)->{
            filteredDependantList.setPredicate(dependant -> {
                if (newValue.isEmpty() || newValue == null || newValue.isBlank()){
                    return true;
                }
                String searchValue = newValue.toLowerCase();
                if (dependant.getId().equals(searchValue)){
                    return true;
                }
                else if (dependant.getFullName().equals(searchValue)){
                    return true;
                }
                else if (dependant.getAddress().equals(searchValue)){
                    return true;
                }
                else if (dependant.getEmail().equals(searchValue)){
                    return true;
                }
                else if (dependant.getPhoneNumber().equals(searchValue)){
                    return true;
                }
                else if (dependant.getPolicyOwnerId().equals(searchValue)){
                    return true;
                }
                else if(dependant.getPolicyOwner().getFullName().equals(searchValue)) {
                    return true;
                }
                else if (dependant.getPolicyHolderId().equals(searchValue)){
                    return true;
                }
                else if(dependant.getPolicyHolder().getFullName().equals(searchValue)) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });
        dependantId.setCellValueFactory(new PropertyValueFactory<Dependant, String>("id"));
        dependantFullName.setCellValueFactory(new PropertyValueFactory<Dependant, String>("fullName"));
        dependantAddress.setCellValueFactory(new PropertyValueFactory<Dependant, String>("address"));
        dependantEmail.setCellValueFactory(new PropertyValueFactory<Dependant, String>("email"));
        dependantPassword.setCellValueFactory(new PropertyValueFactory<Dependant, String>("password"));
        policyOwnerDependantTable.setCellValueFactory(new PropertyValueFactory<Dependant, String>("policyOwnerId"));
        cardNumberDependantTable.setCellValueFactory(new PropertyValueFactory<Dependant, String>("cardNumber"));
        policyHolderDependantTable.setCellValueFactory(new PropertyValueFactory<Dependant, String>("policyHolderId"));
        if (user instanceof SystemAdmin || user instanceof Customer){
            dependantUpdateInfoButton.setCellValueFactory(new PropertyValueFactory<Dependant, Button>("updateInfoButton"));
            dependantRemoveButton.setCellValueFactory(new PropertyValueFactory<Dependant, Button>("removeButton"));
            if (!(user instanceof SystemAdmin)){
                dependantAddClaimButton.setCellValueFactory(new PropertyValueFactory<Dependant, Button>("addClaim"));
            }
        }

        dependantTable.getItems().setAll(dependantObservableList);
    }

    public DependantTableFilling() {
    }

    public TableView<Dependant> getDependantTable() {
        return dependantTable;
    }

    public void setDependantTable(TableView<Dependant> dependantTable) {
        this.dependantTable = dependantTable;
    }

    public TableColumn<Dependant, String> getDependantId() {
        return dependantId;
    }

    public void setDependantId(TableColumn<Dependant, String> dependantId) {
        this.dependantId = dependantId;
    }

    public TableColumn<Dependant, String> getDependantFullName() {
        return dependantFullName;
    }

    public void setDependantFullName(TableColumn<Dependant, String> dependantFullName) {
        this.dependantFullName = dependantFullName;
    }

    public TableColumn<Dependant, String> getDependantAddress() {
        return dependantAddress;
    }

    public void setDependantAddress(TableColumn<Dependant, String> dependantAddress) {
        this.dependantAddress = dependantAddress;
    }

    public TableColumn<Dependant, String> getDependantPhoneNumber() {
        return dependantPhoneNumber;
    }

    public void setDependantPhoneNumber(TableColumn<Dependant, String> dependantPhoneNumber) {
        this.dependantPhoneNumber = dependantPhoneNumber;
    }

    public TableColumn<Dependant, String> getDependantEmail() {
        return dependantEmail;
    }

    public void setDependantEmail(TableColumn<Dependant, String> dependantEmail) {
        this.dependantEmail = dependantEmail;
    }

    public TableColumn<Dependant, String> getDependantPassword() {
        return dependantPassword;
    }

    public void setDependantPassword(TableColumn<Dependant, String> dependantPassword) {
        this.dependantPassword = dependantPassword;
    }

    public TableColumn<Dependant, String> getPolicyOwnerDependantTable() {
        return policyOwnerDependantTable;
    }

    public void setPolicyOwnerDependantTable(TableColumn<Dependant, String> policyOwnerDependantTable) {
        this.policyOwnerDependantTable = policyOwnerDependantTable;
    }

    public TableColumn<Dependant, String> getCardNumberDependantTable() {
        return cardNumberDependantTable;
    }

    public void setCardNumberDependantTable(TableColumn<Dependant, String> cardNumberDependantTable) {
        this.cardNumberDependantTable = cardNumberDependantTable;
    }

    public TableColumn<Dependant, Button> getDependantUpdateInfoButton() {
        return dependantUpdateInfoButton;
    }

    public void setDependantUpdateInfoButton(TableColumn<Dependant, Button> dependantUpdateInfoButton) {
        this.dependantUpdateInfoButton = dependantUpdateInfoButton;
    }

    public TableColumn<Dependant, Button> getDependantAddClaimButton() {
        return dependantAddClaimButton;
    }

    public void setDependantAddClaimButton(TableColumn<Dependant, Button> dependantAddClaimButton) {
        this.dependantAddClaimButton = dependantAddClaimButton;
    }

    public TableColumn<Dependant, Button> getDependantRemoveButton() {
        return dependantRemoveButton;
    }

    public void setDependantRemoveButton(TableColumn<Dependant, Button> dependantRemoveButton) {
        this.dependantRemoveButton = dependantRemoveButton;
    }

    public TableColumn<Dependant, String> getPolicyHolderDependantTable() {
        return policyHolderDependantTable;
    }

    public void setPolicyHolderDependantTable(TableColumn<Dependant, String> policyHolderDependantTable) {
        this.policyHolderDependantTable = policyHolderDependantTable;
    }

    public TextField getDependantSearchField() {
        return dependantSearchField;
    }

    public void setDependantSearchField(TextField dependantSearchField) {
        this.dependantSearchField = dependantSearchField;
    }
}