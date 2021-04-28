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
		return String.format("%s(%d)", categoryName, bookCategory);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookCategory;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCategory other = (BookCategory) obj;
		if (bookCategory != other.bookCategory)
			return false;
		return true;
	}

}
