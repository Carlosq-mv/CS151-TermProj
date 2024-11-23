package application.controller;

import java.util.List;

import application.DataAccessLayer.ScheduledTransactionDAO;
import application.models.ScheduledTransaction;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewScheduledTransactionController {
	@FXML private TableView<ScheduledTransaction> stTable;
	@FXML private TableColumn<ScheduledTransaction, String> tNameCol;
	@FXML private TableColumn<ScheduledTransaction, String> tTypeCol;
	@FXML private TableColumn<ScheduledTransaction, Double> payCol;
	@FXML private TableColumn<ScheduledTransaction, String> accCol;
	@FXML private TableColumn<ScheduledTransaction, Integer> domCol;
	@FXML private TableColumn<ScheduledTransaction, String> freqCol;
	
	private ScheduledTransactionDAO stDAO = ScheduledTransactionDAO.getInstance();
	
	@FXML 
	public void initialize() {
    	// Set up the columns
		tNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		tTypeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTransactionType()));
		payCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPayAmount()).asObject());
		accCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccount()));
		domCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDueDate()).asObject());
		freqCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFrequency()));
		
		// load the scheduled transaction from database
		displayRecords();
	}
	
	public void displayRecords() {
		List<ScheduledTransaction> records = stDAO.getRecords();
		stTable.getItems().addAll(records);
	}
}
