package application.DataAccessLayer;

import java.util.List;

public interface DAOInterface<T> {
	void addRecord(T data);
	
	List<T> getRecords();
	default boolean isDuplicate(T data) {
		return false;
	}
}
