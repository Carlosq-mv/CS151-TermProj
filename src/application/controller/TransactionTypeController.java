package application.controller;


import application.Shared;
import application.DataAccessLayer.TransactionTypeDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TransactionTypeController implements ControllerInterface {
	
	@FXML private TextField transactionType;
	private Shared shared = Shared.getInstance();
	private TransactionTypeDAO transactionDAO = TransactionTypeDAO.getInstance();
	@FXML ListView<String> transactionTypeList;
	
	@Override
	@FXML public void initialize() {
		displayTransactionType();
	}
	
	@Override
	@FXML public void handleSaveOp() {
		String tType = transactionType.getText();
		
		if(transactionDAO.isDuplicate(tType)) {
			shared.flashMessage(AlertType.ERROR, "Transaction Type already exists", "Please enter a new transaction type.");
			return;
		}
		
		if(tType == null || tType == "" || tType.isBlank() ) {
			shared.flashMessage(AlertType.ERROR, "Empty Field", "Please provide a transaction type.");
			return;
		}
		
		transactionDAO.addRecord(tType);
		
		shared.flashMessage(AlertType.INFORMATION, "Success", "New transaction type added.");
		transactionType.clear();
		displayTransactionType();
	}
	
	@Override
	@FXML public void handleCancelOp() {
		// alert the user if they want to proceed with cancel action
		Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Warning");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to cancel this?");
  
        // user confirms to cancel
	    alert.showAndWait().ifPresent(res -> {
	    	
	    	if(res == ButtonType.OK) {
	    		transactionType.clear();
	    	} 
	    });
	}
	
	public void displayTransactionType() {
		transactionTypeList.setItems(FXCollections.observableArrayList(
				transactionDAO.getRecords()
		));
	}
}
