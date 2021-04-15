package library_proj2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library_proj2.dao.BookCountDao;
import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;
import library_proj2.dto.BookCount;
import library_proj2.util.JdbcUtil;

public class BookCountDaoImpl implements BookCountDao {
	private static BookCountDaoImpl instance = new BookCountDaoImpl();

	public static BookCountDaoImpl getInstance() {
		return instance;
	}

	private BookCountDaoImpl() {};
	
	private BookCount getBookCount(ResultSet rs) throws SQLException {
		String bookNo = rs.getString("bookno");
		String bookTitle = rs.getString("booktitle");
		int canRent = rs.getInt("canRent");
		return new BookCount(bookNo, bookTitle, canRent);
	}
	
	@Override
	public List<BookCount> selectBookCount() {
		String sql = "select left(bookno, 5) as bookno, booktitle, count(case when isRented = 1 then 1 end) as canRent "
				+ "from book group by left(bookno, 5)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				List<BookCount> list = new ArrayList<BookCount>(); 
				do {
					list.add(getBookCount(rs));
				} while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public List<BookCount> selectBookCountByNo(Book book) {
		String sql = "select left(bookno, 5), booktitle, count(case when isRented = 1 then 1 end) as canRent "
				+ "from book "
				+ "where bookno like ? "
				+ "group by left(bookno, 5)";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, "%" + book.getBookNo() + "%");
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<BookCount> list = new ArrayList<BookCount>();
					do {
						list.add(getBookCount(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookCount> selectBookCountByTitle(Book book) {
		String sql = "select left(bookno, 5), booktitle, count(case when isRented = 1 then 1 end) as canRent "
				+ "from book "
				+ "where booktitle like ? "
				+ "group by left(bookno, 5)";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, "%" + book.getBookTitle() + "%");
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<BookCount> list = new ArrayList<BookCount>();
					do {
						list.add(getBookCount(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookCount> selectBookCountByCategory(BookCategory bookCategory) {
		String sql = "select left(bookno, 5), booktitle, count(case when isRented = 1 then 1 end) as canRent "
				+ "from vw_all "
				+ "where categoryname like ? "
				+ "group by left(bookno, 5)";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, "%" + bookCategory.getCategoryName() + "%");
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<BookCount> list = new ArrayList<BookCount>();
					do {
						list.add(getBookCount(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
