package library_proj2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library_proj2.dao.HistoryDao;
import library_proj2.dto.History;
import library_proj2.util.JdbcUtil;

public class HistoryDaoImpl implements HistoryDao {
	private static HistoryDaoImpl instance = new HistoryDaoImpl();
	
	private HistoryDaoImpl() {
	}
	
	public static HistoryDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<History> selectHistory(int userNo, String bookNo) {
		String sql1 ="select rentalno, userno, username, "
				+ "bookno, booktitle, categoryname, "
				+ "rentaldate, date(rentaldate + rentalrange) as returndate, userreturndate, delaydate "
				+ "from vw_all ";
		String sql2;
		
		Object obj;
		
		if(userNo != 0 && bookNo == null) { // userNo로 검색할 때
			sql2 = "where userno = ? order by rentalno asc";
		} else{
			sql2 = "where left(bookno, 5) = ? order by rentalno asc";
		}
		
		String sql = sql1 + sql2;
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
				if(userNo != 0 && bookNo == null) {
					pstmt.setInt(1, userNo);
				} else {
					pstmt.setString(1, bookNo);
				}
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					List<History> list = new ArrayList<History>();
					do {
						list.add(getHistory(rs));
					} while(rs.next());
					return list;
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	private History getHistory(ResultSet rs) {
		 int rentalNo = 0;
		 int userNo = 0;
		 String userName = "";
		 String bookNo = "";
		 String bookTitle = "";
		 String categoryName = "";
		 Date rentalDate = null;
		 Date returnDate = null;
		 Date userReturnDate = null;
		 int delayDate = 0;
		
		try {
			rentalNo = rs.getInt("rentalno");
			userNo = rs.getInt("userno");
			userName = rs.getString("username");
			bookNo = rs.getString("bookno");
			bookTitle = rs.getString("booktitle");
			categoryName = rs.getString("categoryname");
			rentalDate = rs.getDate("rentaldate");
			returnDate = rs.getDate("date(rentaldate + rentalrange)");
			userReturnDate = rs.getDate("userreturndate");
			delayDate = rs.getInt("delaydate");		
		} catch (Exception e) {
		}
		
		return new History(rentalNo, userNo, userName, bookNo, bookTitle, categoryName, rentalDate, returnDate, userReturnDate, delayDate);
	}
}
