package application.controller;

import application.Shared;
import javafx.fxml.FXML;

public class StartupController {
	private Shared shared = Shared.getInstance();	
	
	@FXML public void goToCreateAccOP() {
		shared.showPage("view/NewAccount.fxml");		
	}
		
}
