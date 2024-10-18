package application.controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainController {
	
	@FXML HBox mainBox;
	
	@FXML
	public void initialize() {
		showStartUpOp();
	}
	
	@FXML public void showStartUpOp() {
		URL url = getClass().getResource("/view/Startup.fxml");
		
		// read the file & convert to anchor pane
		try {
			AnchorPane startUp = (AnchorPane) FXMLLoader.load(url);
			
			// remove the previous child if there is one
			if(mainBox.getChildren().size() > 1) {
				mainBox.getChildren().remove(1);
			}
			
			// get the children of mainBox & add pane1
			mainBox.getChildren().add(startUp);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML public void goHomeOp() {
		showStartUpOp();
	}
	
}
