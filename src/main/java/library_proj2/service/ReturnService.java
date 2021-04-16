package library_proj2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library_proj2.dao.BookDao;
import library_proj2.dao.UserDao;
import library_proj2.dao.impl.BookDaoImpl;
import library_proj2.dao.impl.UserDaoImpl;
import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;
import library_proj2.dto.RentalStatus;
import library_proj2.dto.User;
import library_proj2.util.JdbcUtil;

public class ReturnService {
	UserDao daoUser = UserDaoImpl.getInstance();
	BookDao daoBook = BookDaoImpl.getInstance();

	public List<User> searchByUserNo(User user) {
		return daoUser.selectUserByNo(user);
	}

	public List<User> searchByUserName(User user) {
		return daoUser.selectUserByName(user);
	}

	public List<User> searchByUserAccount(User user) {
		return daoUser.selectUserByAccount(user);
	}

	public List<User> searchByUserPhone(User user) {
		return daoUser.selectUserByPhone(user);
	}

	public List<Book> searchByBookNo(Book book) {
		return daoBook.selectBookByNo(book);
	}

	public List<Book> searchByBookTitle(Book book) {
		return daoBook.selectBookByTitle(book);
	}

	public List<Book> searchByBookCategory(Book book) {
		return daoBook.selectBookByCategory(book);
	}
	
	public Book bookDetail(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, categoryname, count, rentalrange"
				+ " from vw_all"
				+ " where bookno like ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, "%" + book.getBookNo() + "%");
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getBook(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void transReturn(Book book) {
		String upRentalSql = "update rentalstatus set userreturndate = curdate() where bookno = ?";
		String upBookSql = "update book set isRented = 1 where bookno = ?";

		Connection con = null;
		PreparedStatement sPstmt = null;
		PreparedStatement uPstmt = null;

		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);

			sPstmt = con.prepareStatement(upRentalSql);
			sPstmt.setString(1, book.getBookNo());
			sPstmt.executeUpdate();

			uPstmt = con.prepareStatement(upBookSql);
			uPstmt.setString(1, book.getBookNo());
			uPstmt.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			rollbackUtil(con);
		}
	}
	
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
			bookCategory.setCategoryName(rs.getString("categoryname"));
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
	
	public void rollbackUtil(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
