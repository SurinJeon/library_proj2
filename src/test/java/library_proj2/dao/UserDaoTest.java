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
		System.out.println("testSelectUserByAll()");
		List<User> userList = dao.selectUserByAll();
		
		Assert.assertNotNull(userList);
		userList.stream().forEach(System.out::println);
	}

	@Test
	public void testNextUserNo() {
		System.out.println("testNextUserNo()");
		int res = dao.nextUserNo();
		
		Assert.assertEquals(12015, res);
		System.out.println("res >> " + res);
	}

}
