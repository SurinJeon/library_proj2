package library_proj2.dao;

import java.util.List;

import library_proj2.dto.Book;
import library_proj2.dto.User;

public interface BookDao {

	// select user (리스트 초기화)
	List<Book> selectBookByAll();
	
	// 콤보박스 검색 (리스트로 뜨게)
	List<Book> selectBookByNo(Book book);
	List<Book> selectBookByTitle(Book book);
	List<Book> selectBookByCategory(Book book);

	// book 관리
	int insertBook(Book book);
	int updateBook(Book book);
	int deleteBook(Book book);
	
}
