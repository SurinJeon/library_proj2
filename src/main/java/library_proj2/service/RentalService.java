package library_proj2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import library_proj2.dao.BookDao;
import library_proj2.dao.UserDao;
import library_proj2.dao.impl.BookDaoImpl;
import library_proj2.dao.impl.UserDaoImpl;
import library_proj2.dto.Book;
import library_proj2.dto.BookCount;
import library_proj2.dto.User;
import library_proj2.util.JdbcUtil;

public class RentalService {
	UserDao daoUser = UserDaoImpl.getInstance();
	BookDao daoBook = BookDaoImpl.getInstance();
	
	/*
	 * public List<User> searchByUserNo(User user) { return
	 * daoUser.selectUserByNo(user); }
	 * 
	 * public List<User> searchByUserName(User user) { return
	 * daoUser.selectUserByName(user); }
	 * 
	 * public List<User> searchByUserAccount(User user) { return
	 * daoUser.selectUserByAccount(user); }
	 * 
	 * public List<User> searchByUserPhone(User user) { return
	 * daoUser.selectUserByPhone(user); }
	 */

	public List<Book> bookList(){
		return daoBook.selectBookByAll();
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
	
	
	
	public void transRental(User user, Book book) {
		String bookSql = "insert into rentalstatus values (null, ?, ?, curdate(), null, default(delaydate))";
		String rentalstatusSql = "update book set isRented = 0 where bookno = ?";
		
		Connection con = null;
		PreparedStatement bPstmt = null;
		PreparedStatement rPstmt = null;
		
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			bPstmt = con.prepareStatement(bookSql);
			bPstmt.setString(1, book.getBookNo());
			bPstmt.setInt(2, user.getUserNo());
			bPstmt.executeUpdate();
			
			rPstmt = con.prepareStatement(rentalstatusSql);
			rPstmt.setString(1, book.getBookNo());
			rPstmt.executeUpdate();
			
			con.commit();
			
		} catch (SQLException e) {
			rollbackUtil(con);
		}
		
	}

	public void rollbackUtil(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
