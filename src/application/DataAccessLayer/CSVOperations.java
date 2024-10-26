package application.DataAccessLayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVOperations {
	
	public void addReccord(String data, String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.write(data);
			writer.newLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean isDuplicate(String data, String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			
			while((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				
				if(parts[0].trim().equalsIgnoreCase(data)) {
					return true;
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	

}
