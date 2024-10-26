package application;

public class Constants {
	// file paths for CSV files
	public static final String ACC_FILE_PATH = "lib/Accounts.csv";
	
	// database file with driver
	public static final String ACC_DB = "jdbc:sqlite:lib/Account.db";
		
	// SQL Table Creation Statements
	public static final String SQL_ACC_TABLE = """ 
		CREATE TABLE IF NOT EXISTS Account (
			ID INTEGER PRIMARY KEY AUTOINCREMENT,
			name TEXT NOT NULL UNIQUE,
			balance DECIMAL NOT NULL,
			date DATE NOT NULL
		);
			
	""";

}
