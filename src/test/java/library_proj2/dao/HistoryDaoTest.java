package library_proj2.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import library_proj2.dao.impl.HistoryDaoImpl;
import library_proj2.dto.History;

public class HistoryDaoTest {
	HistoryDao dao = HistoryDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectHistory() {
		System.out.println("testSelectHistory()");
		List<History> list = dao.selectHistory(0, "40001");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
	}

}
