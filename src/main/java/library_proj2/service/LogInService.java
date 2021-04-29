package library_proj2.service;

import library_proj2.dao.ManagerDao;
import library_proj2.dao.RentalStatusDao;
import library_proj2.dao.UserDao;
import library_proj2.dao.impl.ManagerDaoImpl;
import library_proj2.dao.impl.RentalStatusDaoImpl;
import library_proj2.dao.impl.UserDaoImpl;
import library_proj2.dto.Manager;

public class LogInService {
	ManagerDao daoMng = ManagerDaoImpl.getInstance();
	RentalStatusDao daoRs = RentalStatusDaoImpl.getInstance();
	UserDao daoUser = UserDaoImpl.getInstance();
	
	public Manager managerLogIn(Manager manager) {
		return daoMng.selectManagerById(manager);
	}
	
	public void updateDelayDate() {
		daoRs.updateRentalStatusLogIn();
	}
	
	public void updateBlackList() {
		daoRs.updateBlackList();
	}
}
