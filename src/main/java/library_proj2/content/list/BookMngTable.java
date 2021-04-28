package library_proj2.content.list;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import library_proj2.dto.Book;
import library_proj2.dto.BookCategory;
import library_proj2.exception.NotSelectedException;
import library_proj2.service.BookService;

@SuppressWarnings("serial")
public class BookMngTable extends AbstractCustomTable<Book>{

	private BookService service;
	
	
	public void setService(BookService service) {
		this.service = service;
	}

	public void setPopupMenu(JPopupMenu popupMenu) {
		table.setComponentPopupMenu(popupMenu);
	}
	
	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);
		setTableCellWidth(100, 250, 100, 80, 100);
		setTableCellCondition(4);
	}

	@Override
	protected Object[] toArray(Book b) {
		String categoryName = service.searchCategoryName(new BookCategory(b.getBookCategory().getBookCategory()));
		
		return new Object[] {
				b.getBookNo(),
				b.getBookTitle(),
				categoryName,
				b.getRentalRange(),
				b.getIsRented() == 1 ? "대여가능" : "대여불가"
				};
	}

	@Override
	protected void initList() {
		list = service.bookList();
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"도서번호", "도서제목", "도서구분", "대여기간", "대여상태"};
	}

	public Book getItem() {
		int idx = table.getSelectedRow();
		String bookNo = "";
		
		try {
			bookNo = (String) table.getValueAt(idx, 0);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}
		
		if (idx == -1) {
			throw new NotSelectedException();
		}
		System.out.println("selected book >> " + service.searchByBookNo(new Book(bookNo)));
		return service.searchByBookNo(new Book(bookNo));
	}
	
	@SuppressWarnings("serial")
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
			String isRented = String.valueOf(table.getValueAt(row, 4));
			if (isRented.equals("대여가능")) {
				setBackground(Color.green);
			} else {
				setBackground(Color.red);
			}
			setHorizontalAlignment(SwingConstants.CENTER);
			return this;
		}
	}
	
	public void setTableCellCondition(int...idx) {
		ConditionTableCellRender ctcr = new ConditionTableCellRender();
		TableColumnModel tcm = super.getTable().getColumnModel();
		
		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(ctcr);
		}
	}
	
}
