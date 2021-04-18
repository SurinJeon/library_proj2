package library_proj2.content.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import library_proj2.content.detail.BookDetail;
import library_proj2.content.detail.UserDetail;
import library_proj2.dto.Book;
import library_proj2.dto.BookCount;
import library_proj2.dto.RentalStatus;
import library_proj2.dto.User;
import library_proj2.exception.NotAvailableException;
import library_proj2.service.MainService;
import library_proj2.service.RentalService;
import library_proj2.service.ReturnService;
import library_proj2.ui.RentalPage;
import library_proj2.ui.ReturnPage;

@SuppressWarnings("serial")
public class BookTable extends AbstractCustomTable implements MouseListener{

	private MainService mainService;
	private RentalService rentalService;
	private ReturnService returnService;
	private BookDetail pBookDetail;
	private int delimiter;
	private BookTable pBookListMain;
	
	public BookTable() {
	}

	public BookTable(int delimiter) {
		this.delimiter = delimiter;
		table.addMouseListener(this);
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}
	
	public void setRentalService(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	public void setReturnService(ReturnService returnService) {
		this.returnService = returnService;
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		setTableCellWidth(150, 400, 200);
		setTableCellCondition(2);
	}

	@Override
	protected void initList() {
		switch (delimiter) {
		case 1:
			list = mainService.bookList();
			break;
		case 2:
			list = rentalService.bookList();
			break;
		}
		
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"도서번호", "도서제목", "대출여부"};
	}
	
	@Override
	protected Object[] toArray(Object b) {
		if(b instanceof Book) {
			return new Object[] {
				((Book) b).getBookNo(),
				((Book) b).getBookTitle(),
				((Book) b).getIsRented() == 1 ? "대여가능" : "대여불가"
				};
		} else if(b instanceof BookCount) {
			int size = rentalService.searchByBookNo(new Book(((BookCount) b).getBookNo())).size();
			return new Object[] {
				((BookCount) b).getBookNo(),
				((BookCount) b).getBookTitle(),
				((BookCount) b).getCanRent() > 0 ?
						"대여 가능 권수" + "(" + ((BookCount) b).getCanRent() + ")" + " / " + "총 권수" + "(" + size + ")"
						: "대여불가"
					};
		}
		return null;
	}
	
	public void setTableCellCondition(int...idx) {
		ConditionTableCellRender ctcr = new ConditionTableCellRender();
		TableColumnModel tcm = super.getTable().getColumnModel();
		
		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(ctcr);
		}
	}

	
	private class ConditionTableCellRender extends JLabel implements TableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(
				JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row,
				int column) {
			setText(value == null ? "" : value.toString());
			setOpaque(true);
			String isRented = (String) table.getValueAt(row, 2);
			if (isRented.equals("대여불가")) {
				setBackground(Color.red);
			} else {
				setBackground(Color.green);
			}
			
			setHorizontalAlignment(SwingConstants.CENTER);
			return this;
		}
	}

	public void setBookList(List<Book> list) {
		this.list = list;
	}
	
	public void setBookCountList(List<BookCount> list) {
		this.list = list;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if (delimiter == 1 && e.getClickCount() == 2) {
				JTable table = (JTable) e.getSource();
				int idx = table.getSelectedRow();

				String bookNo = (String) table.getValueAt(idx, 0);

				BookCount selectBook = mainService.searchByBookNo(new Book(bookNo)).get(0);
				if (selectBook != null && selectBook.getCanRent() > 0) { // 대여
					RentalPage frame = new RentalPage();

					frame.getpBookList().setDelimiter(2); // rentalpage로 넘기고 delimiter 설정

					List<Book> bookList = rentalService.searchByBookNo(new Book(bookNo));
					frame.getpBookList().setBookList(bookList);
					frame.getpBookList().setList();

					BookTable bookPanel = frame.getpBookList();

					frame.setpBookListMain(this);
					frame.setVisible(true);

					this.delimiter = 1; // 다시 목록 계속 더블클릭 할 수 있도록
				} else {
					throw new NotAvailableException("대출할 수 없는 도서입니다.");
				}
			} else if ((delimiter == 2 || delimiter == 3) && e.getClickCount() == 1) {
				JTable table = (JTable) e.getSource();
				int idx = table.getSelectedRow();

				String bookNo = (String) table.getValueAt(idx, 0);

				Book bookDetail = rentalService.bookDetail(new Book(bookNo));
				pBookDetail.setBook(bookDetail);
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	public BookDetail getpBookDetail() {
		return pBookDetail;
	}

	public void setpBookDetail(BookDetail pBookDetail) {
		this.pBookDetail = pBookDetail;
	}

	public int getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(int delimiter) {
		this.delimiter = delimiter;
	}
	
}












