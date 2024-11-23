package application;

public class Constants {
	// database file with driver
	public static final String DATABASE = "jdbc:sqlite:WalletWise.db";
		
	// SQL Table Creation Statements
	public static final String ACCOUNT_TABLE = """ 
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
	
	public static final String SCHEDULED_TRANSACTION_TABLE = """
		CREATE TABLE IF NOT EXISTS ScheduledTransaction (
			ID INTEGER PRIMARY KEY AUTOINCREMENT,
			name TEXT NOT NULL,
			account TEXT NOT NULL,
			transaction_type TEXT NOT NULL,
			frequency TEXT NOT NULL,
			date INTEGER NOT NULL,
			pay_amount DECIMAL NOT NULL
		)
	""";
}
