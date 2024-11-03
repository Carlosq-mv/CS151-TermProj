package application.DataAccessLayer;

import java.util.List;

public interface DAOInt {
	List<String> getRecords();
	boolean isDuplicate(String data);
	
}
