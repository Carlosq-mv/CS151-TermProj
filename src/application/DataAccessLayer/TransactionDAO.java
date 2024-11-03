package application.DataAccessLayer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.models.Account;
import application.models.Transaction;

public class TransactionDAO implements DAOInterface<Transaction> {
	private static TransactionDAO instance = new TransactionDAO();
	private DbConnection dbConnection = DbConnection.getInstance();
	
	private TransactionDAO() {}
	
	public static TransactionDAO getInstance() {
		return instance;
	}
	
	public void addRecord(Transaction transaction) {
		String sql =  """
			INSERT into "Transaction" (account, transaction_type, date, description, pay_amount, deposit_amount) VALUES (?, ?, ?, ?, ?, ?)
		""";
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {

        	preparedStatement.setString(1, transaction.getAccountName());
        	preparedStatement.setString(2, transaction.getTransactionType());
        	preparedStatement.setDate(3, Date.valueOf(transaction.getDate()));
        	preparedStatement.setString(4, transaction.getDescription());
        	preparedStatement.setDouble(5, transaction.getPaymentAmonunt());
        	preparedStatement.setDouble(6, transaction.getDepositAmount());
            preparedStatement.executeUpdate();
            System.out.println("TransactionType added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public List<Transaction> getRecords() {
		List<Transaction> transactions = new ArrayList<>();
		String sql = "SELECT * FROM \"Transaction\"";
		
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Iterate through the result set
        	while (resultSet.next()) { 
        		String name = resultSet.getString("account");
                String transactionType = resultSet.getString("transaction_type");
                LocalDate date = resultSet.getDate("date").toLocalDate(); 
                String description = resultSet.getString("description");
                double payAmount = resultSet.getDouble("pay_amount");
                double depositAmount = resultSet.getDouble("deposit_amount");
                

                // Add to the list
                Transaction transaction = new Transaction(name, transactionType, date, description);
                transaction.setPaymentAmonunt(payAmount);
                transaction.setDepositAmount(depositAmount);
                
                transactions.add(transaction); 
        	}
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return transactions;
		
	}
	
}
