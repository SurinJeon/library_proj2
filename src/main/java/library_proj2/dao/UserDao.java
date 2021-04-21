package library_proj2.dao;

import java.util.List;

import library_proj2.dto.User;

public interface UserDao {

	// select user (리스트 초기화)
	List<User> selectUserByAll();
	
	// 콤보박스 검색 (리스트로 뜨게)
	List<User> selectUserByNo(User user);
	List<User> selectUserByName(User user);
	List<User> selectUserByPhone(User user);
	List<User> selectUserByAccount(User user);
	
	// user 관리
	int insertUser(User user);
	int updateUser(User user);
	int deleteUser(User user);
	int nextUserNo(); /* 회원 추가 시 자동 증가하도록 */
}
