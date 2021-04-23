package library_proj2.content;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;

public class BookPanel extends JPanel {
	private JTextField tfBookNo;
	private JTextField tfBookTitle;
	private JTextField tfRentalRange;

	public BookPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblBookNo = new JLabel("도서번호: ");
		lblBookNo.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblBookNo);
		
		tfBookNo = new JTextField();
		add(tfBookNo);
		tfBookNo.setColumns(10);
		
		JLabel lblBookTitle = new JLabel("도서제목: ");
		lblBookTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblBookTitle);
		
		tfBookTitle = new JTextField();
		add(tfBookTitle);
		tfBookTitle.setColumns(10);
		
		JLabel lblCategory = new JLabel("도서구분: ");
		lblCategory.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblCategory);
		
		JComboBox cmbCategory = new JComboBox();
		add(cmbCategory);
		
		JLabel lblRentalRange = new JLabel("대여기간: ");
		lblRentalRange.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblRentalRange);
		
		tfRentalRange = new JTextField();
		add(tfRentalRange);
		tfRentalRange.setColumns(10);
	}
	
	public Book getBook() {
		String bookNo = "";
		String bookTitle = "";
		int categoryName = 0;
		BookCategory bookCategory = new BookCategory(categoryName);
		int count = 0;
		int rentalRange = 0;
		return new Book(bookNo, bookTitle, 1, bookCategory, count, rentalRange);
	}
	

}
