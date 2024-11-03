package application.controller;

import java.time.LocalDate;
import java.util.List;

import application.DataAccessLayer.AccountDAO;
import application.models.Account;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccountsController {
	@FXML private TableView<Account> accTable;
    @FXML private TableColumn<Account, String> accNameCol;
    @FXML private TableColumn<Account, Double> balanceCol;
    @FXML private TableColumn<Account, LocalDate> dateCol;
	private AccountDAO accountDAO = AccountDAO.getInstance();
    
    @FXML 
    public void initialize() {
    	// Set up the columns
        accNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccountName()));
        balanceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBalance()).asObject());
        dateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getOpeningDate()));

        // Load accounts from the database
        displayAccount();
    }

    public void displayAccount() {
    	List<Account> accounts = accountDAO.getAccountRecords();
    	accTable.getItems().addAll(accounts);
    }
    
}
