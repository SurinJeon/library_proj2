package library_proj2.dto;

import java.util.Date;

public class RentalStatus {

	private int rentalNo;
	private Book bookNo;
	private User userNo;
	private Date rentalDate;
	private Date userReturnDate;
	private int delayDate;

	public RentalStatus() {
	}

	public RentalStatus(int rentalNo) {
		this.rentalNo = rentalNo;
	}

	public RentalStatus(Book bookNo, User userNo, Date rentalDate) {
		this.bookNo = bookNo;
		this.userNo = userNo;
		this.rentalDate = rentalDate;
	}

	public RentalStatus(Book bookNo, Date rentalDate, int delayDate) {
		this.bookNo = bookNo;
		this.rentalDate = rentalDate;
		this.delayDate = delayDate;
	}

	public RentalStatus(int rentalNo, Book bookNo, User userNo, Date rentalDate, Date userReturnDate, int delayDate) {
		this.rentalNo = rentalNo;
		this.bookNo = bookNo;
		this.userNo = userNo;
		this.rentalDate = rentalDate;
		this.userReturnDate = userReturnDate;
		this.delayDate = delayDate;
	}

	public int getRentalNo() {
		return rentalNo;
	}

	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}

	public Book getBookNo() {
		return bookNo;
	}

	public void setBookNo(Book bookNo) {
		this.bookNo = bookNo;
	}

	public User getUserNo() {
		return userNo;
	}

	public void setUserNo(User userNo) {
		this.userNo = userNo;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
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
				"RentalStatus [rentalNo=%s, bookNo=%s, userNo=%s, rentalDate=%s, userReturnDate=%s, delayDate=%s]",
				rentalNo, bookNo, userNo, rentalDate, userReturnDate, delayDate);
	}

}
