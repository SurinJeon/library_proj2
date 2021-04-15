package library_proj2.dao;

import library_proj2.dto.BookCategory;

public interface BookCategoryDao {

	// 코드관리 때 필요할 수도 있음....(0407의 내가...)
	int insertBookCategory(BookCategory bookcategory);
	int updateBookCategory(BookCategory bookcategory);
	int deleteBookCategory(BookCategory bookcategory);
	
}
