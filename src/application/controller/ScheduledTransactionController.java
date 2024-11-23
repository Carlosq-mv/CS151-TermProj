package application.controller;


import java.util.List;

import application.Shared;
import application.DataAccessLayer.AccountDAO;
import application.DataAccessLayer.ScheduledTransactionDAO;
import application.DataAccessLayer.TransactionTypeDAO;
import application.models.ScheduledTransaction;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ScheduledTransactionController implements ControllerInterface {

	@FXML private ChoiceBox<String> accountDropDownMenu;
	@FXML private ChoiceBox<String> transactionDropDownMenu;
	@FXML private ChoiceBox<String> frequencyDropDownMenu;
	
	@FXML private TextField transactionName;
	@FXML private TextField paymentAmount;
	@FXML private TextField transactionDate;
	
	private ScheduledTransactionDAO stDao = ScheduledTransactionDAO.getInstance();
	private TransactionTypeDAO transactionTypeDao = TransactionTypeDAO.getInstance();
	private AccountDAO accountDao = AccountDAO.getInstance();
	
	private Shared shared = Shared.getInstance();
	private String msg = "No Records Available";
	
	
	@Override
	@FXML public void initialize() {
		List<String> transactionRecords = transactionTypeDao.getRecords();
		List<String> accountNameRecords = accountDao.getAccountNameRecords();
		
		transactionDropDownMenu.getItems().addAll(transactionRecords);
		accountDropDownMenu.getItems().addAll(accountNameRecords);
		
		
		// show the first value as default for both drop down menus
		if (!transactionRecords.isEmpty()) {
			transactionDropDownMenu.setValue(transactionRecords.get(0));
		} else {
			transactionDropDownMenu.setValue(msg);
		}
		if(!accountNameRecords.isEmpty() ) {
			accountDropDownMenu.setValue(accountNameRecords.get(0));
		} else {
			accountDropDownMenu.setValue(msg);
		}
	
		frequencyDropDownMenu.setValue("Monthly");
	}
	
	
	@Override
	@FXML public void handleSaveOp() {
		// get the values from fxml fields
		String tDate = transactionDate.getText();
		
		// if account or transaction drop down menu don't have a record (i.e., have "No Records Available" message)
		// then assign the value to null rather than the message. This will ensure a record won't be commited to databse
		// with error message
		
		String accountName = accountDropDownMenu.getValue() == msg ? null : accountDropDownMenu.getValue();
		String transactionType = transactionDropDownMenu.getValue() == msg ? null : transactionDropDownMenu.getValue();
		String freq = frequencyDropDownMenu.getValue();
		String tName = transactionName.getText();
		String payAmount = paymentAmount.getText();
		
		double amount = toDouble(payAmount);
		int date;
		
		// check if the required fields are empty
		if(hasEmptyFields(tDate, accountName, transactionType, freq, tName, payAmount)) {
			shared.flashMessage(AlertType.ERROR, "Empty Fields", "Please fill in all required fields.");
			return;
		}

		try {
		    date = Integer.parseInt(tDate); 
		    // Validate the day of the month range
		    if (date < 1 || date > 31) { 
		        shared.flashMessage(AlertType.ERROR, "Invalid Day", "Please enter a day between 1 and 31.");
		        return;
		    }
		} catch (NumberFormatException e) {
		    shared.flashMessage(AlertType.ERROR, "Invalid Input", "Please enter a valid numerical day of the month.");
		    return;
		}
		
		if(amount < 0) {
			shared.flashMessage(AlertType.ERROR, "Invalid Amount", "Please ensure the amount is a valid positive monetary value.");
			return;
		}
		
		ScheduledTransaction transaction = new ScheduledTransaction(tName, accountName, transactionType, freq, date, amount);
		
		if(stDao.isDuplicate(transaction)) {
			shared.flashMessage(AlertType.ERROR, "Reccuring transaction name already exists", "Please enter a new reccuring transaction.");
			return;
		}
		
		
		
		// add the transaction to database
		stDao.addRecord(transaction);
		
		// show the user a success message
		shared.flashMessage(AlertType.INFORMATION, "Success", "New Scheduled Transaction Added.");
				
		clearInputs();
	}
	
	@Override 
	@FXML public void handleCancelOp() {
		// alert the user if they want to proceed with cancel action
		Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Warning");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to cancel this transaction?");
  
        // user confirms to cancel
	    alert.showAndWait().ifPresent(res -> {
	    	
	    	if(res == ButtonType.OK) {
	    		clearInputs();
	    	} 
	    });
	}
	
	@Override
	public void clearInputs() {
		transactionName.clear();
		paymentAmount.clear();
		transactionDate.clear();
	}
	
	private boolean hasEmptyFields(String tDate, String accountName, String transactionType, String freq, String tName, String payAmount) {
	    return (tDate == null || tDate.isBlank() || 
	    		accountName == null || accountName.isBlank() ||
	            transactionType == null || transactionType.isBlank() ||
	            freq == null || freq.isBlank() ||
	            tName == null || tName.isBlank() ||
	            payAmount == null || payAmount.isBlank());
	}
	
}
