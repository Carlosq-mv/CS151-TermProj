package application.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.Constants;
import application.models.Account;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccountsController {
	@FXML private TableView<Account> accTable;
    @FXML private TableColumn<Account, String> accNameCol;
    @FXML private TableColumn<Account, Double> balanceCol;
    @FXML private TableColumn<Account, LocalDate> dateCol;
    
    @FXML 
    public void initialize() {
    	// Set up the columns
        accNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccountName()));
        balanceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBalance()).asObject());
        dateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getOpeningDate()));

        // Load accounts from the CSV file
        getAccountRecords();
    }
    
    public void getAccountRecords() {
    	List<Account> accounts = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(Constants.ACC_FILE_PATH))) {
			String line;
			String header = reader.readLine();
			
			while((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				String accName = parts[0].trim();
				double balance = Double.parseDouble(parts[1].trim());
	            LocalDate date = LocalDate.parse(parts[2].trim());
	          
	            accounts.add(new Account(accName, balance, date));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		// Add accounts to the TableView
        accTable.getItems().addAll(accounts);
    }
	
}
