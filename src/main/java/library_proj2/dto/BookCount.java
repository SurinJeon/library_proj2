package library_proj2.dto;

public class BookCount {

	private String bookNo;
	private String bookTitle;
	private int canRent;

	public BookCount() {
	}

	public BookCount(String bookNo) {
		this.bookNo = bookNo;
	}

	public BookCount(String bookNo, String bookTitle, int canRent) {
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.canRent = canRent;
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

	public int getCanRent() {
		return canRent;
	}

	public void setCanRent(int canRent) {
		this.canRent = canRent;
	}

	@Override
	public String toString() {
		return String.format("BookCount [bookNo=%s, bookTitle=%s, canRent=%s]", bookNo, bookTitle, canRent);
	}

}
