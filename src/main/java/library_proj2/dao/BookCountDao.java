package library_proj2.dao;

import java.util.List;

import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;
import library_proj2.dto.BookCount;

public interface BookCountDao {

	// main화면에서 책 보이는 부분 table
	
	List<BookCount> selectBookCount();
	List<BookCount> selectBookCountByNo(Book book);
	List<BookCount> selectBookCountByTitle(Book book);
	List<BookCount> selectBookCountByCategory(BookCategory bookCategory);
	
}
