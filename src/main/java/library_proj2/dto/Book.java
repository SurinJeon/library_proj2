package library_proj2.dto;

public class Book {

	private String bookNo;
	private String bookTitle;
	private int isRented;
	private BookCategory bookCategory;
	private int count;
	private int rentalRange;
	private RentalStatus rentalstatus;

	public Book() {
	}

	public Book(String bookNo) {
		this.bookNo = bookNo;
	}

	public Book(String bookNo, String bookTitle) {
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
	}

	public Book(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public Book(String bookNo, String bookTitle, BookCategory bookCategory) {
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookCategory = bookCategory;
	}

	public Book(String bookNo, String bookTitle, RentalStatus rentalstatus) {
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.rentalstatus = rentalstatus;
	}

	public Book(String bookNo, String bookTitle, int isRented, BookCategory bookCategory, int count, int rentalRange) {
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.isRented = isRented;
		this.bookCategory = bookCategory;
		this.count = count;
		this.rentalRange = rentalRange;
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

	public int getIsRented() {
		return isRented;
	}

	public void setIsRented(int isRented) {
		this.isRented = isRented;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRentalRange() {
		return rentalRange;
	}

	public void setRentalRange(int rentalRange) {
		this.rentalRange = rentalRange;
	}

	@Override
	public String toString() {
		return String.format("Book [bookNo=%s, bookTitle=%s, isRented=%s, bookCategory=%s, count=%s, rentalRange=%s]",
				bookNo, bookTitle, isRented, bookCategory, count, rentalRange);
	}

}
