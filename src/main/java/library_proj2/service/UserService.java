package library_proj2.service;

import java.util.List;

import library_proj2.dao.UserDao;
import library_proj2.dao.impl.UserDaoImpl;
import library_proj2.dto.User;

public class UserService {
	UserDao dao = UserDaoImpl.getInstance();
	
	public void insertUser(User user) {
		dao.insertUser(user);
	}
	
	public List<User> userList(){
		return dao.selectUserByAll();
	}
	
	public int nextUserNo() {
		return dao.nextUserNo();
	}
}
