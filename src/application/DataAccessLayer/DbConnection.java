package application.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import application.Constants;

public class DbConnection {
	private static DbConnection dbConn = new DbConnection();
	private Connection SQLConnection;
	
	
	private DbConnection()  {
		SQLConnection = connect();
	}
	
	public static DbConnection getInstance() {
		return dbConn;
	}
	
	public Connection getSQLConnection()  {
		return SQLConnection;
	}
	
	// create a connection to SQLite
	private Connection connect() {
		
		try {
			return DriverManager.getConnection(Constants.DATABASE);
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// creates all database tables (called in main)
	public void DBInit() {
//		File file = new File("db/WalletWise.db");
//		if (file.exists()) return;
		
		createTable(Constants.ACCOUNT_TABLE);
		createTable(Constants.TRANSACTION_TYPE_TABLE);	
		createTable(Constants.TRANSACTION_TABLE);
		createTable(Constants.SCHEDULED_TRANSACTION_TABLE);
		// add other tables here
	}


    public void createTable(String sql) {
    	try (Statement statement = getSQLConnection().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.err.println("error creating table: " + e.getMessage());
        }
    }
	

}
