package library_proj2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public String searchCategoryName(BookCategory bookcategory) {
		String sql = "select categoryname from bookcategory where bookcategory = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookcategory.getBookCategory());
			try (ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return rs.getString(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookCategory> getCategoryNames() {
		String sql = "select bookcategory, categoryname from bookcategory";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				List<BookCategory> list = new ArrayList<BookCategory>();
				do {
					list.add(getBookCategory(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BookCategory getBookCategory(ResultSet rs) {
		int bookCategory = 0;
		String categoryName = "";
		try {
			bookCategory = rs.getInt("bookcategory");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			categoryName = rs.getString("categoryname");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new BookCategory(bookCategory, categoryName);
	}

}
