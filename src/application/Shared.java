package application;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

// all references to share between many controllers lie here (uses singleton pattern)
public class Shared {
	private static Shared shared = new Shared();
	private HBox mainBox;
	
	private Shared() {}
	
	
	public static Shared getInstance() {
		return shared;
	}


	public HBox getMainBox() {
		return mainBox;
	}


	public void setMainBox(HBox mainBox) {
		this.mainBox = mainBox;
	}
	
	public void flashMessage(AlertType type, String title, String content ) {
		Alert alert = new Alert(type);
	    alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(content);
  
	    alert.show();	
	}
	
	public void showPage(String filePath) {
		URL url = getClass().getClassLoader().getResource(filePath);
		
		try {
			AnchorPane page = (AnchorPane) FXMLLoader.load(url);
			
			if(mainBox.getChildren().size() > 1) {
				mainBox.getChildren().remove(1);
			}
			
			mainBox.getChildren().add(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
