package library_proj2.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import library_proj2.dao.impl.UserDaoImpl;
import library_proj2.dto.User;

public class UserDaoTest {
	UserDao dao = UserDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectUserByAll() {
		List<User> userList = dao.selectUserByAll();
		
		Assert.assertNotNull(userList);
		userList.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectUserByNo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectUserByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectUserByPhone() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectUserByAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

}
