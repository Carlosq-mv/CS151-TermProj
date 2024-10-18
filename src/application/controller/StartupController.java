package application.controller;

import java.io.IOException;
import java.net.URL;

import application.Shared;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class StartupController {
	private Shared shared = Shared.getInstance();	
	
	@FXML public void goToCreateAccOP() {
		URL url = getClass().getResource("/view/NewAccount.fxml");
		
		try {
			AnchorPane accPane = (AnchorPane) FXMLLoader.load(url);
			
			HBox mainBox = shared.getMainBox();
			
			// remove the previous child if there is one
			if(mainBox.getChildren().size() > 1) {
				mainBox.getChildren().remove(1);
			}
			
			// get the children of mainBox & add pane1
			mainBox.getChildren().add(accPane);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
				
		
	}
}
