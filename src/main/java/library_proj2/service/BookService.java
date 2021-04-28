package library_proj2.service;

import java.util.List;

import library_proj2.dao.BookCategoryDao;
import library_proj2.dao.BookDao;
import library_proj2.dao.impl.BookCategoryDaoImpl;
import library_proj2.dao.impl.BookDaoImpl;
import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;

public class BookService {
	BookDao daoBook = BookDaoImpl.getInstance();
	BookCategoryDao daoBC = BookCategoryDaoImpl.getInstance();
	
	public void insertBook(Book book) {
		daoBook.insertBook(book);
	}
	
	public void updateBook(Book book) {
		daoBook.updateBook(book);
	}
	
	public void deleteBook(Book book) {
		daoBook.deleteBook(book);
	}
	
	public List<Book> bookList(){
		return daoBook.selectBookByAll();
	}
	
	public Book searchByBookNo(Book book) {
		return daoBook.selectBookByNo(book).get(0);
	}
	
	public String searchCategoryName(BookCategory bookcategory) {
		return daoBC.searchCategoryName(bookcategory);
	}
	
	public List<BookCategory> categoryNames() {
		return daoBC.getCategoryNames();
	}
}
