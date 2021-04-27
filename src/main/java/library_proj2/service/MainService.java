package library_proj2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library_proj2.dao.BookCountDao;
import library_proj2.dao.BookDao;
import library_proj2.dao.UserDao;
import library_proj2.dao.impl.BookCountDaoImpl;
import library_proj2.dao.impl.BookDaoImpl;
import library_proj2.dao.impl.UserDaoImpl;
import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;
import library_proj2.dto.BookCount;
import library_proj2.dto.RentalStatus;
import library_proj2.dto.User;
import library_proj2.util.JdbcUtil;

public class MainService {
	UserDao daoUser = UserDaoImpl.getInstance();
	BookCountDao daoBookCount = BookCountDaoImpl.getInstance();
	BookDao daoBook = BookDaoImpl.getInstance();
	
	
	public List<User> userList(){
		return daoUser.selectUserByAll();
	}
		
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
	
	public int userCount() {
		return daoUser.userCount();
	}
	
	public int canRentCount() {
		return daoBook.canRentCount();
	}

	public List<BookCount> bookList(){
		return daoBookCount.selectBookCount();
	}
	
	public List<BookCount> searchByBookNo(Book book) {
		return daoBookCount.selectBookCountByNo(book);
	}

	public List<BookCount> searchByBookTitle(Book book) {
		return daoBookCount.selectBookCountByTitle(book);
	}

	public List<BookCount> searchByBookCategory(BookCategory bookCategory) {
		return daoBookCount.selectBookCountByCategory(bookCategory);
	}
	
	public int isDelayCount() {
		String sql = "select count(bookno) from vw_all where isRented = 0 and delaydate > 0 and userreturndate is null";
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

	// main화면에서 사용 >> 책 더블클릭 할 때 넘어가는 용도
	public List<RentalStatus> mainToReturn(Book book, User user){
		String sql = "";
		if(user == null) {
			sql = "select rentalno, bookno, booktitle, bookcategory, categoryname, userno, rentaldate, userreturndate, delaydate from vw_all "
					+ "where bookno = ? and userreturndate is null";
		} else if(book == null){
			sql = "select rentalno, bookno, booktitle, bookcategory, categoryname, userno, rentaldate, userreturndate, delaydate from vw_all "
					+ "where userno = ? and userreturndate is null";
		}
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			if(user == null) {
				pstmt.setString(1, book.getBookNo());
			} else if(book == null) {
				pstmt.setInt(1, user.getUserNo());
			}
			
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					List<RentalStatus> list = new ArrayList<RentalStatus>();
					do {
						list.add(getRentalStatus(rs));
					} while(rs.next());
					return list;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private RentalStatus getRentalStatus(ResultSet rs) {
		int rentalNo = 0;
		Book bookNo = null;
		User userNo = null;
		Date rentalDate = null;
		Date userReturnDate = null;
		int delayDate = 0;
		
		try {
			rentalNo = rs.getInt("rentalno");
		} catch (SQLException e) {
		}

		try {
			bookNo = new Book(rs.getString("bookno"));
			bookNo.setBookTitle(rs.getString("booktitle"));
			bookNo.setBookCategory(new BookCategory(rs.getInt("bookcategory"), rs.getString("categoryname")));
		} catch (SQLException e) {
		}
		
		try {
			userNo = new User(rs.getInt("userno"));
		} catch (SQLException e) {
		}

		try {
			rentalDate = rs.getDate("rentaldate");
		} catch (SQLException e) {
		}

		try {
			userReturnDate = rs.getDate("userreturndate");
		} catch (SQLException e) {
		}

		try {
			delayDate = rs.getInt("delayDate");
		} catch (SQLException e) {
		}
		
		return new RentalStatus(rentalNo, bookNo, userNo, rentalDate, userReturnDate, delayDate);
	}


}
