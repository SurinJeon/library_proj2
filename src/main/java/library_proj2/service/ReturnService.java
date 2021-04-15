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

	public void rollbackUtil(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
