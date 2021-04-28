package library_proj2.content;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;
import library_proj2.service.BookService;
import javax.swing.JPasswordField;

public class BookPanel extends JPanel {
	private JTextField tfBookNo;
	private JTextField tfBookTitle;
	private JTextField tfRentalRange;
	private BookService service;
	private JComboBox cmbCategory;
	private JPasswordField pfPass;
	
	public JPasswordField getPfPass() {
		return pfPass;
	}

	public void setService(BookService service) {
		this.service = service;
		
		List<BookCategory> bcList = service.categoryNames();
		
		DefaultComboBoxModel<BookCategory> bcModel = new DefaultComboBoxModel<BookCategory>(new Vector<>(bcList));
		cmbCategory.setModel(bcModel);
		cmbCategory.setSelectedIndex(-1);
	}
	
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
		
		cmbCategory = new JComboBox();
		add(cmbCategory);
		
		JLabel lblRentalRange = new JLabel("대여기간: ");
		lblRentalRange.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblRentalRange);
		
		tfRentalRange = new JTextField();
		add(tfRentalRange);
		tfRentalRange.setColumns(10);
		
		JLabel lblPass = new JLabel("관리자 비밀번호: ");
		lblPass.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblPass);
		
		pfPass = new JPasswordField();
		add(pfPass);
	}
	
	public Book getBook() {
		/* validCheck 필요 */
		String bookNo = tfBookNo.getText().trim();
		String bookTitle = tfBookTitle.getText().trim();
		BookCategory bookCategory = (BookCategory)cmbCategory.getSelectedItem();
		int count = 1;
		int rentalRange = Integer.parseInt(tfRentalRange.getText().trim());
		
		return new Book(bookNo, bookTitle, 1, bookCategory, count, rentalRange);
	}
	
	public void setBook(Book book) {
		tfBookNo.setText(book.getBookNo());
		tfBookTitle.setText(book.getBookTitle());
		cmbCategory.setSelectedItem(book.getBookCategory());
		tfRentalRange.setText(book.getRentalRange() + "");
	}
	
	public void clearTf() {
		tfBookNo.setText("");
		tfBookTitle.setText("");
		cmbCategory.setSelectedIndex(-1);
		tfRentalRange.setText("");
	}

}
