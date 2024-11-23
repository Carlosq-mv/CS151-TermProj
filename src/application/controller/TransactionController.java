package application.controller;

import java.time.LocalDate;
import java.util.List;

import application.Shared;
import application.DataAccessLayer.AccountDAO;
import application.DataAccessLayer.TransactionDAO;
import application.DataAccessLayer.TransactionTypeDAO;
import application.models.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TransactionController implements ControllerInterface {
	
	@FXML private DatePicker transactionDate;
	@FXML private TextField paymentAmount;
	@FXML private TextField depositAmount;
	@FXML private TextArea transactionDescription;
	
	@FXML private ChoiceBox<String> transactionDropDownMenu;
	@FXML private ChoiceBox<String> accountDropDownMenu;
	
	private TransactionTypeDAO transactionTypeDao = TransactionTypeDAO.getInstance();
	private TransactionDAO transactionDao = TransactionDAO.getInstance();
	private AccountDAO accountDao = AccountDAO.getInstance();
	private Shared shared = Shared.getInstance();
	private String msg = "No Records Available";
	

	@Override
	@FXML public void initialize() {
		List<String> transactionRecords = transactionTypeDao.getRecords();
		List<String> accountNameRecords = accountDao.getAccountNameRecords();
		
		transactionDropDownMenu.getItems().addAll(transactionRecords);
		accountDropDownMenu.getItems().addAll(accountNameRecords);
		transactionDate.setValue(LocalDate.now());
		
		
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
	}
	
	@Override
	@FXML public void handleSaveOp() {
		// get values of fxml fields
		LocalDate tDate = transactionDate.getValue();
		String tDescription = transactionDescription.getText();	
		String transactionType = transactionDropDownMenu.getValue() == msg ? null : transactionDropDownMenu.getValue();
		String accountName = accountDropDownMenu.getValue() == msg ? null : accountDropDownMenu.getValue();
		
		double selectedAmount;
		
		
		// checks if the required fields (date, description, transaction type, account name) are empty
		if(hasEmptyFields(tDate, tDescription, transactionType, accountName)) {
			shared.flashMessage(AlertType.ERROR, "Empty Fields", "Please fill in all required fields.");
			return;
		}
		
		Transaction transaction = new Transaction(accountName, transactionType, tDate, tDescription);
		
		// get the value of whether user chose deposit or payment
		if(!depositAmount.getText().isBlank() && !paymentAmount.getText().isBlank()) {
			// if both are filed, flash an error message to user
			shared.flashMessage(AlertType.ERROR, "Input Error", "Please fill just one of Payment Amount or Deposit Amount.");
			return;
		} else if(!paymentAmount.getText().isBlank()) {
			// only payment has an input
			selectedAmount = toDouble(paymentAmount.getText());
			transaction.setPaymentAmonunt(selectedAmount);
		} else if(!depositAmount.getText().isBlank()) {
			// only deposit has an input
			selectedAmount = toDouble(depositAmount.getText());
			transaction.setDepositAmount(selectedAmount);
		}  else {
			// neither fields have an input, so flash an error message to user
			shared.flashMessage(AlertType.ERROR, "Input Error", "Please fill in either Payment Amount or Deposit Amount.");
			return;
		}	
		
		if(selectedAmount < 0) {
			shared.flashMessage(AlertType.ERROR, "Invalid Amount", "Please ensure the amount is a valid positive monetary value.");
			return;
		}
		
		// add the transaction to database
		transactionDao.addRecord(transaction);
		
		// show the user a success message
		shared.flashMessage(AlertType.INFORMATION, "Success", "New Transaction Added.");
		
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
		transactionDescription.clear();
		depositAmount.clear();
		paymentAmount.clear();
	}
	
	@Override
	public double toDouble(String value) {
		double amount = 0.0;
		try {
			amount = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			return -1;
		}
		return amount;
	}
	
	private boolean hasEmptyFields(LocalDate tDate, String tDescription, String transactionSelectedVal, String accountSelectedVal) {
		return (tDate == null || tDescription.isBlank() || accountSelectedVal.isBlank() || transactionSelectedVal.isBlank());
	}
}
