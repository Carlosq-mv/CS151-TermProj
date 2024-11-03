package application;

public class Constants {
	// database file with driver
	public static final String DATABASE = "jdbc:sqlite:db/WalletWise.db";
		
	// SQL Table Creation Statements
	public static final String SQL_ACC_TABLE = """ 
		CREATE TABLE IF NOT EXISTS Account (
			ID INTEGER PRIMARY KEY AUTOINCREMENT,
			name TEXT NOT NULL UNIQUE,
			balance DECIMAL NOT NULL,
			date DATE NOT NULL
		);
			
	""";
	
	public static final String TRANSACTION_TYPE_TABLE = """
		CREATE TABLE IF NOT EXISTS TransactionType (
			ID INTEGER PRIMARY KEY AUTOINCREMENT,
			transaction_type TEXT NOT NULL UNIQUE
		);		
	""";
	
	public static final String TRANSACTION_TABLE = """
		CREATE TABLE IF NOT EXISTS "Transaction" (
			ID INTEGER PRIMARY KEY AUTOINCREMENT,
			account TEXT NOT NULL,
			transaction_type TEXT NOT NULL,
			date DATE NOT NULL,
			description TEXT NOT NULL,
			pay_amount DECIMAL,
			deposit_amount DECIMAL
		);	
	""";
}
