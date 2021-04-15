package library_proj2.content.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
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
	
	public BookTable() {
	}

	public BookTable(int delimiter) {
		this.delimiter = delimiter;
		table.addMouseListener(this);
		pBookDetail = new BookDetail();
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
			return new Object[] {
				((BookCount) b).getBookNo(),
				((BookCount) b).getBookTitle(),
				((BookCount) b).getCanRent() > 0 ? "대여가능" : "대여불가"
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
				setBackground(Color.white);
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
		
		if(delimiter == 1 && e.getClickCount() == 2) {
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			
			String bookNo = (String)table.getValueAt(idx, 0);
			
			Book bookDetail = rentalService.searchByBookNo(new Book(bookNo)).get(0);
			
			
			if (bookDetail != null && bookDetail.getIsRented() != 0) { // 대여
				RentalPage frame = new RentalPage();

				frame.setpBookList(this);
				BookTable bookPanel = frame.getpBookList();

				int tableIdx = 0;
				for (int i = 0; i < bookPanel.getList().size(); i++) {
					if (bookPanel.table.getValueAt(i, 0).equals(bookNo)) {
						tableIdx = i;
						break;
					}
				}
				bookPanel.table.setRowSelectionInterval(tableIdx, tableIdx);
				bookPanel.table.scrollRectToVisible(new Rectangle(bookPanel.table.getCellRect(tableIdx, 0, true)));
				
				frame.getpBookDetail().setBook(bookDetail);
				
				frame.setVisible(true);
			} else{ // 반납
				List<RentalStatus> user = mainService.mainToReturn(new Book(bookNo), null);
				
				ReturnPage frame = new ReturnPage();
				frame.setpBookList(this);
				
//				UserTable userPanel = frame.getpUserList();
//				int tableIdx = 0;
//				for (int i = 0; i < userPanel.getList().size(); i++) {
//					if (userPanel.table.getValueAt(i, 0) == user.get(0).getUserNo()) {
//						tableIdx = i;
//						break;
//					}
//				}
//				userPanel.table.setRowSelectionInterval(tableIdx, tableIdx);
//				userPanel.table.scrollRectToVisible(new Rectangle(userPanel.table.getCellRect(tableIdx, 0, true)));
				
				User userForDetail = mainService.searchByUserNo(user.get(0).getUserNo()).get(0);
				
				frame.getpUserDetail().setUser(userForDetail);
				
				// bookRentalDatail에 값 채우기
				frame.getpBookDetail().setBook(bookDetail);
				
				frame.setVisible(true);
			}
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












