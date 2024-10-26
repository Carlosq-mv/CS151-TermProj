package application.controller.DataAccessLayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import application.Constants;

public class DbConnection {
	
	public static void CSVInit() {
		createAccFile();
	}
	
	public static void createAccFile() {
		File file = new File(Constants.ACC_FILE_PATH);
		
		// check if the file already exists
	    if (file.exists()) {
	        System.out.println("File already exists: " + Constants.ACC_FILE_PATH);
	        return; 
	    }
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write("Name,Opening Balance,Date");
			writer.newLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
