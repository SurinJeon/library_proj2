package library_proj2.dto;

public class BookCategory {

	private int bookCategory;
	private String categoryName;

	public BookCategory() {
	}

	public BookCategory(int bookCategory) {
		this.bookCategory = bookCategory;
	}

	public BookCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public BookCategory(int bookCategory, String categoryName) {
		this.bookCategory = bookCategory;
		this.categoryName = categoryName;
	}

	public int getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(int bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return String.format("BookCategory [%s, %s]", bookCategory, categoryName);
	}

}
