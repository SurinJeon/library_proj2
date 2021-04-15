package library_proj2.dao;

import library_proj2.dto.Manager;

public interface ManagerDao {
	
	// 로그인용
	Manager selectManagerById(Manager manager);
	
}
