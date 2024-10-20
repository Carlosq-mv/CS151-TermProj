package application.controller;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

//TODO: Save data to a database
//TODO: Duplicate validation (prevent duplicates for AccountName)
public class NewAccountController {
	
	@FXML private TextField accountName;
	@FXML private TextField openingBalance;
	@FXML private DatePicker openingDate;
	
	
	@FXML public void initialize() {
		openingDate.setValue(LocalDate.now());
	}
	
	@FXML public void handleSaveOp() {
		String accName = accountName.getText();
		String opBalStr = openingBalance.getText();
		LocalDate opDate = openingDate.getValue();
		
		// convert balance String to type double
		double opBal = convertOpeningBalanceToDouble(opBalStr);
		
		if(isEmptyFields(accName, opBalStr, opDate)) {
			flashMessage(AlertType.ERROR, "Empty Fields", "Please fill in all fields.");
			return;
		}
		
		if(opBal <= 0.0) {
			flashMessage(AlertType.ERROR, "Invalid Balance Value", "Please enter a valid monetary balance.");
			return;
		}
		
		flashMessage(AlertType.INFORMATION, "Success", "New Account Added.");
		clearInputs();
		
		System.out.println("Input: " + accName + ", " + opBal + ", " + opDate);
	}
	
	@FXML public void handleCancelOp() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Warning");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to cancel this?");
  
	    alert.showAndWait().ifPresent(res -> {
	    	// user confirms to cancel
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
