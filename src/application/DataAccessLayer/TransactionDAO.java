package application.DataAccessLayer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.models.Transaction;

public class TransactionDAO {
	private static TransactionDAO instance = new TransactionDAO();
	private DbConnection dbConnection = DbConnection.getInstance();
	
	private TransactionDAO() {}
	
	public static TransactionDAO getInstance() {
		return instance;
	}
	
	public void addTransaction(Transaction transaction) {
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
}
