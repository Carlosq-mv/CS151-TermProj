package application.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import application.Constants;

public class DbConnection {
	
	// create a connection to SQLite
	public static Connection connect() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(Constants.ACC_DB);
			return connection;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// creates all database tables (called in main)
	public static void DBInit() {
		createAccTable();
		// add other tables here
	}

	
    public static void createAccTable() {
    	// create the Account table
        String sql = Constants.SQL_ACC_TABLE;
        
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
             
            statement.execute(sql);
            System.out.println("table 'Account' has been created or exists.");
        } catch (SQLException e) {
            System.err.println("error creating table: " + e.getMessage());
        }
    }
	
	
//  csv stuff below
// 	NOTE: delete later maybe
//	public static void CSVInit() {
//		createAccFile();
//	}
//	
//	public static void createAccFile() {
//		File file = new File(Constants.ACC_FILE_PATH);
//		
//		// check if the file already exists
//	    if (file.exists()) {
//	        System.out.println("File already exists: " + Constants.ACC_FILE_PATH);
//	        return; 
//	    }
//		
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//			writer.write("Name,Opening Balance,Date");
//			writer.newLine();
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
}
