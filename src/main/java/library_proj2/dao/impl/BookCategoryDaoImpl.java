package library_proj2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import library_proj2.dao.BookCategoryDao;
import library_proj2.dto.BookCategory;
import library_proj2.util.JdbcUtil;

public class BookCategoryDaoImpl implements BookCategoryDao {
	private static final BookCategoryDaoImpl instance = new BookCategoryDaoImpl();

	public static BookCategoryDaoImpl getInstance() {
		return instance;
	}

	private BookCategoryDaoImpl() {};

	@Override
	public int insertBookCategory(BookCategory bookcategory) {
		String sql = "insert into bookcategory values(?, ?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookcategory.getBookCategory());
			pstmt.setString(2, bookcategory.getCategoryName());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBookCategory(BookCategory bookcategory) {
		String sql = "update bookcategory set bookcategory = ?, categoryname = ? where bookcategory = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookcategory.getBookCategory());
			pstmt.setString(2, bookcategory.getCategoryName());
			pstmt.setInt(3, bookcategory.getBookCategory());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteBookCategory(BookCategory bookcategory) {
		String sql = "delete from bookcategory where bookcategory = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookcategory.getBookCategory());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
