package application.DataAccessLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.models.Account;

public class TransactionTypeDAO {
	private static TransactionTypeDAO instance = new TransactionTypeDAO();
	private DbConnection dbConnection = DbConnection.getInstance();

	private TransactionTypeDAO() {}
	
	public static TransactionTypeDAO getInstance() {
		return instance;
	}
	
	public void addTransactionType(String transactionType) {
		String sql = "INSERT INTO TransactionType (transaction_type) VALUES (?)";
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {

        	preparedStatement.setString(1, transactionType);
            preparedStatement.executeUpdate();
            System.out.println("TransactionType added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public boolean isDuplicateTransactionType(String transactionType) {
		String sql = "SELECT COUNT(*) FROM TransactionType WHERE LOWER(transaction_type) = LOWER(?)";
		
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {

        	preparedStatement.setString(1, transactionType);
        	ResultSet resultSet = preparedStatement.executeQuery(); 
  
        	// check if resulting query has rows
        	if (resultSet.next()) {
        		// get count from the first column in resulting query
        		int count = resultSet.getInt(1); 
        		// if greater than 0, there is a duplicate
                return count > 0; 
             }
     
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public List<String> getTransactionTypeRecords() {
		List<String> transactionTypes = new ArrayList<>();
		String sql = "SELECT transaction_type FROM TransactionType ORDER BY id DESC";
		
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {

        	ResultSet resultSet = preparedStatement.executeQuery(); 
        	
        	// Iterate through the result set
        	while (resultSet.next()) { 
        		String tType = resultSet.getString("transaction_type");
        		transactionTypes.add(tType);
               
            }
     
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return transactionTypes;
		
	}
}