package library_proj2.content.cmb;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library_proj2.content.list.BookTable;
import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;
import library_proj2.dto.BookCount;
import library_proj2.exception.NotAvailableException;
import library_proj2.service.MainService;
import library_proj2.service.RentalService;

@SuppressWarnings("serial")
public class BookCmb extends JPanel implements ActionListener {

	private MainService mainService;
	private RentalService rentalService;
	private JTextField tfBook;
	private JComboBox<String> cmbBook;
	private BookTable pBookList;
	private JButton btnSearch;
	private int delimiter;
	private JLabel lblSearchBook;

	public BookCmb(int delimiter) {
		pBookList = new BookTable(delimiter);
		lblSearchBook = new JLabel();
		initialize(delimiter);
		cmbBook.setSelectedIndex(-1);
	}

	private void initialize(int delimiter) {
		setLayout(new GridLayout(0, 4, 10, 0));

		
		if (delimiter == 1) {
			lblSearchBook = new JLabel("빠른도서검색 :");
		} else if(delimiter == 2) {
			lblSearchBook = new JLabel("검색방법:");
		}
		lblSearchBook.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblSearchBook);

		cmbBook = new JComboBox<String>();
		cmbBook.setModel(new DefaultComboBoxModel<String>(new String[] { "도서번호", "도서제목", "도서분류" }));
		add(cmbBook);

		tfBook = new JTextField();
		add(tfBook);
		tfBook.setColumns(10);

		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		add(btnSearch);

	}

	public void setMainService(MainService service) {
		this.mainService = service;
	}
	
	public void setRentalService(RentalService service) {
		this.rentalService = service;
	}
	
	public void clearTf() {
		tfBook.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			if(delimiter == 1) {
				actionPerformedBtnSearchBookCount(e);
			} else if(delimiter == 2) {
				actionPerformedBtnSearchBook(e);
			}
		}
	}

	private void actionPerformedBtnSearchBookCount(ActionEvent e) {
		List<BookCount> list = new ArrayList<BookCount>();
		
		try {
			String searchItem = (String) cmbBook.getSelectedItem();
			list = switchList(searchItem);
			if(list != null) {
				pBookList.setBookCountList(list);
				pBookList.setList();
			} else {
				throw new NotAvailableException("해당 도서가 존재하지 않습니다.");
			}
		} catch (NotAvailableException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			clearTf();
			
		}
	}

	private void actionPerformedBtnSearchBook(ActionEvent e) {
		List<Book> list = new ArrayList<Book>();
		
		try {
			String searchItem = (String) cmbBook.getSelectedItem();
			list = switchList(searchItem);
			if(list != null) {
				pBookList.setBookList(list);
				pBookList.setList();
			} else {
				throw new NotAvailableException("해당 도서가 존재하지 않습니다.");
			}
		} catch (NotAvailableException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			clearTf();
			
		}
		
	}

	private List switchList(String searchItem) {
		List list = null;
		String text = tfBook.getText();

		if (delimiter == 1) {
			list = new ArrayList<BookCount>();
			switch (searchItem) {
			case "도서번호":
				list = mainService.searchByBookNo(new Book(text));
				break;
			case "도서제목":
				list = mainService.searchByBookTitle(new Book(null, text));
				break;
			case "도서분류":
				list = mainService.searchByBookNo(new Book(new BookCategory(Integer.parseInt(tfBook.getText()))));
				break;
			}
			return list;
		} else if (delimiter == 2) {
			list = new ArrayList<Book>();
			switch (searchItem) {
			case "도서번호":
				list = rentalService.searchByBookNo(new Book(text));
				break;
			case "도서제목":
				list = mainService.searchByBookTitle(new Book(null, text));
				break;
			case "도서분류":
				list = mainService.searchByBookNo(new Book(new BookCategory(Integer.parseInt(tfBook.getText()))));
				break;
			}
			return list;
		}
		
		return null;
		
	}

	public BookTable getpBookList() {
		return pBookList;
	}

	public void setpBookList(BookTable pBookList) {
		this.pBookList = pBookList;
	}
	
	

}
