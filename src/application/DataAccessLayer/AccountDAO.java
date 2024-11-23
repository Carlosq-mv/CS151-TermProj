package application.DataAccessLayer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.models.Account;

public class AccountDAO implements DAOInterface<Account>{

    private static AccountDAO instance = new AccountDAO();
    private DbConnection dbConnection = DbConnection.getInstance();

    private AccountDAO() {}

    public static AccountDAO getInstance() {
        return instance;
    }
	
    @Override
	public void addRecord(Account account) {
		// add a new account to database
		String sql = "INSERT INTO Account (name, balance, date) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {

        	preparedStatement.setString(1, account.getAccountName());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setDate(3, Date.valueOf(account.getOpeningDate()));
            preparedStatement.executeUpdate();
            System.out.println("Account added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
    @Override
	public boolean isDuplicate(Account account) {
		// check if account to be inserted already exists
		String sql = "SELECT COUNT(*) FROM Account WHERE name = ?";
		
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {

        	preparedStatement.setString(1, account.getAccountName());
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
	
    @Override
	public List<Account> getRecords() {
		// get all of the accounts in database
		List<Account> accounts = new ArrayList<>();
		String sql = "SELECT name, balance, date FROM Account ORDER BY date DESC";
		
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {

        	ResultSet resultSet = preparedStatement.executeQuery(); 
        	
        	// Iterate through the result set
        	while (resultSet.next()) { 
        		String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");
                LocalDate date = resultSet.getDate("date").toLocalDate(); 
                

                // Add to the list
                accounts.add(new Account(name, balance, date)); 
            }
     
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return accounts;
	}
	
	public List<String> getAccountNameRecords() {
		// get all of the accounts in database
		List<String> accounts = new ArrayList<>();
		String sql = "SELECT name FROM Account ORDER BY id DESC";
		
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {

        	ResultSet resultSet = preparedStatement.executeQuery(); 
        	
        	// Iterate through the result set
        	while (resultSet.next()) { 
        		String name = resultSet.getString("name");
                
                // Add to the list
                accounts.add(name); 
            }
     
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return accounts;
	}
}
