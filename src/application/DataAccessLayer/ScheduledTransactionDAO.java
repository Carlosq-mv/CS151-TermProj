package application.DataAccessLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.models.ScheduledTransaction;

public class ScheduledTransactionDAO implements DAOInterface<ScheduledTransaction> {
	private static ScheduledTransactionDAO instance = new ScheduledTransactionDAO();
	private DbConnection dbConnection = DbConnection.getInstance();

	private ScheduledTransactionDAO() {}

	public static ScheduledTransactionDAO getInstance() {
		return instance;
	}
	
	@Override
	public void addRecord(ScheduledTransaction transaction) {
		String sql = """
			Insert INTO ScheduledTransaction (name, account, transaction_type, frequency, date, pay_amount) VALUES (?,?,?,?,?,?)
		""";
		
		try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {

        	preparedStatement.setString(1, transaction.getName());
        	preparedStatement.setString(2, transaction.getAccount());
        	preparedStatement.setString(3, transaction.getTransactionType());
        	preparedStatement.setString(4, transaction.getFrequency());
        	preparedStatement.setInt(5, transaction.getDueDate());
        	preparedStatement.setDouble(6, transaction.getPayAmount());
            preparedStatement.executeUpdate();
            System.out.println("TransactionType added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	

	@Override
	public List<ScheduledTransaction> getRecords() {
		List<ScheduledTransaction> transactions = new ArrayList<>();
		String sql = "SELECT * FROM ScheduledTransaction";

	try (PreparedStatement preparedStatement = dbConnection.getSQLConnection().prepareStatement(sql)) {
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Iterate through the result set
        	while (resultSet.next()) { 
        		String name = resultSet.getString("name");
				String account = resultSet.getString("account");
                String transactionType = resultSet.getString("transaction_type");
                String frequency = resultSet.getString("frequency");
                int date = resultSet.getInt("date"); 
                double payAmount = resultSet.getDouble("pay_amount");
                

                // Add to the list
                ScheduledTransaction transaction = new ScheduledTransaction(name, account, transactionType, frequency, date, payAmount);
                
                transactions.add(transaction); 
        	}
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return transactions;
	}

	
}
