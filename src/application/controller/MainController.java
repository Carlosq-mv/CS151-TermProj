package application.controller;

import java.io.IOException;
import java.net.URL;

import application.Shared;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainController {
	private Shared shared = Shared.getInstance();
	
	@FXML HBox mainBox;
	@FXML private Button homeBtn, transactionTypeBtn, accountsBtn,
	enterTransactionBtn, scheduledTransaction, viewScheduledTransaction, viewAccountsBtn;
	
	@FXML public void initialize() {
		showStartUpOp();
	}
	
	@FXML public void showStartUpOp() {
		URL url = getClass().getClassLoader().getResource("view/Startup.fxml");
		
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
	
	@FXML public void goHomeOp() {
		setSelectedButton(homeBtn);
		shared.showPage("view/Startup.fxml");
	}
	
	@FXML public void goToAccountsOp() {
		setSelectedButton(accountsBtn);
		shared.showPage("view/NewAccount.fxml");	
		
	}
	
	@FXML public void goToTransactionsTypeOp() {
		setSelectedButton(transactionTypeBtn);
		shared.showPage("view/TransactionType.fxml");
	}
	
	@FXML public void goToEnterTransactionOp() {
		setSelectedButton(enterTransactionBtn);
		shared.showPage("view/EnterTransaction.fxml");
	}
	
	@FXML public void goToScheduledTransactionOp() {
		setSelectedButton(scheduledTransaction);
		shared.showPage("view/ScheduledTransaction.fxml");
	}
	
	@FXML public void goToViewScheduledTransactionOp() {
		setSelectedButton(viewScheduledTransaction);
		shared.showPage("view/ViewScheduledTransaction.fxml");	
	}
	
	@FXML public void goToViewAccountsOp() {
		setSelectedButton(viewAccountsBtn);
		shared.showPage("view/Accounts.fxml");
	}
	
	
	private void setSelectedButton(Button selectedButton) {
	    // Remove 'selected' class from all buttons
		homeBtn.getStyleClass().remove("selected");
		transactionTypeBtn.getStyleClass().remove("selected");
		accountsBtn.getStyleClass().remove("selected");
		enterTransactionBtn.getStyleClass().remove("selected");
		scheduledTransaction.getStyleClass().remove("selected");
		viewScheduledTransaction.getStyleClass().remove("selected");
		viewAccountsBtn.getStyleClass().remove("selected");

	    // Add 'selected' class to the clicked button
	    selectedButton.getStyleClass().add("selected");
	}
}
