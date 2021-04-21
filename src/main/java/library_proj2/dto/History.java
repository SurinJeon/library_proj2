package library_proj2.dto;

import java.util.Date;

public class History {
	private int rentalNo;
	private int userNo;
	private String userName;
	private String bookNo;
	private String bookTitle;
	private String categoryName;
	private Date rentalDate;
	private Date returnDate;
	private Date userReturnDate;
	private int delayDate;
	
	public History() {
	}

	public History(int rentalNo, int userNo, String userName, String bookNo, String bookTitle, String categoryName,
			Date rentalDate, Date returnDate, Date userReturnDate, int delayDate) {
		this.rentalNo = rentalNo;
		this.userNo = userNo;
		this.userName = userName;
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.categoryName = categoryName;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.userReturnDate = userReturnDate;
		this.delayDate = delayDate;
	}

	public int getRentalNo() {
		return rentalNo;
	}

	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getUserReturnDate() {
		return userReturnDate;
	}

	public void setUserReturnDate(Date userReturnDate) {
		this.userReturnDate = userReturnDate;
	}

	public int getDelayDate() {
		return delayDate;
	}

	public void setDelayDate(int delayDate) {
		this.delayDate = delayDate;
	}

	@Override
	public String toString() {
		return String.format(
				"History [rentalNo=%s, userNo=%s, userName=%s, bookNo=%s, bookTitle=%s, categoryName=%s, rentalDate=%s, returnDate=%s, userReturnDate=%s, delayDate=%s]",
				rentalNo, userNo, userName, bookNo, bookTitle, categoryName, rentalDate, returnDate, userReturnDate,
				delayDate);
	}
	
}
