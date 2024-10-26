package application.controller;

import java.time.LocalDate;

import application.DataAccessLayer.AccountDAO;
import application.DataAccessLayer.CSVOperations;
import application.models.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class NewAccountController {
	
	@FXML private TextField accountName;
	@FXML private TextField openingBalance;
	@FXML private DatePicker openingDate;
	private CSVOperations csvOp = new CSVOperations();
	private AccountDAO accountDAO = AccountDAO.getInstance();

	
	@FXML public void initialize() {
		openingDate.setValue(LocalDate.now());
	}
	
	@FXML public void handleSaveOp() {
		String accName = accountName.getText();
		String opBalStr = openingBalance.getText();
		LocalDate opDate = openingDate.getValue();
		
		// convert balance String to type double
		double opBal = convertOpeningBalanceToDouble(opBalStr);
		Account acc = new Account(accName, opBal, opDate);
		
		// check if there is any missing input data
		if(isEmptyFields(accName, opBalStr, opDate)) {
			flashMessage(AlertType.ERROR, "Empty Fields", "Please fill in all fields.");
			return;
		}
		
		// check if balance is valid (Must be greater than 0)
		if(opBal <= 0.0) {
			flashMessage(AlertType.ERROR, "Invalid Balance Value", "Please enter a valid monetary balance.");
			return;
		}
	
		// check if there are any existing account names
		if(accountDAO.isDuplicate(acc)) {
			flashMessage(AlertType.ERROR, "Account already exists", "Please enter a new account.");
			return;
		}
		
		// add a new account to database
		accountDAO.addAccount(acc);
		
		// show some success message to user
		flashMessage(AlertType.INFORMATION, "Success", "New Account Added.");
		clearInputs();

		System.out.println(acc.toCSV());
	}
	
	@FXML public void handleCancelOp() {
		// alert the user if they want to proceed with cancel action
		Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Warning");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to cancel this?");
  
        // user confirms to cancel
	    alert.showAndWait().ifPresent(res -> {
	    	
	    	if(res == ButtonType.OK) {
	    		clearInputs();
	    	} 
	    });
	}
	
	private void clearInputs() {
		accountName.clear();
		openingBalance.clear();
		openingDate.setValue(LocalDate.now());
	}
	
	private void flashMessage(AlertType type, String title, String content ) {
		Alert alert = new Alert(type);
	    alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(content);
  
	    alert.show();	
	}
	
	private double convertOpeningBalanceToDouble(String opBalStr) {
		double opBal = 0.0;
		try {
			opBal = Double.parseDouble(opBalStr);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			return -1;
		}
		return opBal;
	}
	
	private boolean isEmptyFields(String accName, String opBal, LocalDate opDate) {
		return (accName == null || accName == "" || opBal == null || opBal == "" || opDate == null);
	}
	
}
