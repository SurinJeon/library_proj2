package library_proj2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library_proj2.dao.BookDao;
import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;
import library_proj2.util.JdbcUtil;

public class BookDaoImpl implements BookDao {
	private static BookDaoImpl instance = new BookDaoImpl();

	public static BookDaoImpl getInstance() {
		return instance;
	}

	private BookDaoImpl() {};

	private Book getBook(ResultSet rs) throws SQLException {
		String bookNo = null;
		String bookTitle = null;
		int isRented = 0;
		BookCategory bookCategory = null;
		int count = 0;
		int rentalRange = 0;
		

		try {
			bookNo = rs.getString("bookno");
		} catch (SQLException e) {
		}

		try {
			bookTitle = rs.getString("booktitle");
		} catch (SQLException e) {
		}

		try {
			isRented = rs.getInt("isrented");
		} catch (SQLException e) {
		}
		
		try {
			bookCategory = new BookCategory(rs.getInt("bookcategory"));
		} catch (SQLException e) {
		}
		
		try {
			count = rs.getInt("count");
		} catch (SQLException e) {
		}
		
		try {
			rentalRange = rs.getInt("rentalrange");
		} catch (SQLException e) {
		}
		
		return new Book(bookNo, bookTitle, isRented, bookCategory, count, rentalRange);
	}
	
	@Override
	public List<Book> selectBookByAll() {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange from book";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				List<Book> list = new ArrayList<Book>(); 
				do {
					list.add(getBook(rs));
				} while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> selectBookByNo(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from book"
				+ " where bookno like ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, "%" + book.getBookNo() + "%");
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBook(rs));
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
	public List<Book> selectBookByTitle(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from book"
				+ " where booktitle like ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, "%" + book.getBookTitle() + "%");
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBook(rs));
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
	public List<Book> selectBookByCategory(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from book"
				+ " where bookcategory = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, book.getBookCategory().getBookCategory());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBook(rs));
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
	public int insertBook(Book book) {
		String sql ="insert into book"
				+ " values (?, ?, ?, ?, ?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			pstmt.setString(2, book.getBookTitle());
			pstmt.setInt(3, book.getIsRented());
			pstmt.setInt(4, book.getBookCategory().getBookCategory());
			pstmt.setInt(5, book.getCount());
			pstmt.setInt(6, book.getRentalRange());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBook(Book book) {
		String sql = "update book set bookno = ?, booktitle = ?, isRented = ?, bookcategory = ?, count = ?, rentalrange = ? where bookno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			pstmt.setString(2, book.getBookTitle());
			pstmt.setInt(3, book.getIsRented());
			pstmt.setInt(4, book.getBookCategory().getBookCategory());
			pstmt.setInt(5, book.getCount());
			pstmt.setInt(6, book.getRentalRange());
			pstmt.setString(7, book.getBookNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteBook(Book book) {
		String sql ="delete from book"
				+ " where bookno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int canRentCount() {
		String sql = "select count(bookno) from book where isRented = 1";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				int res = rs.getInt(1);
				return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
