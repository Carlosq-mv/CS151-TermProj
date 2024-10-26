package application;
	
import application.controller.DataAccessLayer.DbConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			HBox mainBox = (HBox)FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
			Scene scene = new Scene(mainBox);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// keep a reference of the mainBox inside the shared object
			Shared shared = Shared.getInstance();
			shared.setMainBox(mainBox);
			
			// create csv file
			DbConnection.CSVInit();
		} catch(Exception e) {
			//e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
