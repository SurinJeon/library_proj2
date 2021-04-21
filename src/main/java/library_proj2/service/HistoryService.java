package library_proj2.service;

import java.util.List;

import library_proj2.dao.HistoryDao;
import library_proj2.dao.impl.HistoryDaoImpl;
import library_proj2.dto.History;

public class HistoryService {
	HistoryDao dao = HistoryDaoImpl.getInstance();
	
	public List<History> userHistory(int userNo){
		return dao.selectHistory(userNo, null);
	}
	
	public List<History> bookHistory(String bookNo){
		return dao.selectHistory(0, bookNo);
	}

	
}
