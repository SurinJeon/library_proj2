package library_proj2.dao;

import java.util.List;

import library_proj2.dto.History;

public interface HistoryDao {

	List<History> selectHistory(int userNo, String bookNo);
	
}
