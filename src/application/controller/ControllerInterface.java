package application.controller;

import javafx.fxml.FXML;

public interface ControllerInterface {
	@FXML void initialize();
	@FXML void handleSaveOp();
	@FXML void handleCancelOp();
	
	default void clearInputs() {}
	
	default double toDouble(String value) {
		double amount = 0.0;
		try {
			amount = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			return -1;
		}
		return amount;
	}	
}
