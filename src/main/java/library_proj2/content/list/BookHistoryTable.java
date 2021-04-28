package library_proj2.content.list;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import library_proj2.dto.History;
import library_proj2.service.HistoryService;

public class BookHistoryTable extends AbstractCustomTable<History> {
	private HistoryService service;
	private String bookNo;
	
	public void setList(String bookNo) {
		this.bookNo = bookNo;
		list = service.bookHistory(bookNo);
	}
	
	public void setService(HistoryService service) {
		this.service = service;
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);
		setTableCellWidth(70, 100, 100, 100, 100, 70);
		setTableCellCondition(5);
	}

	@Override
	protected Object[] toArray(History h) {
		if(h.getUserNo() == 0) {
			return null;
		}
		
		return new Object[] {
				h.getUserNo(),
				h.getUserName(),
				h.getRentalDate(),
				h.getReturnDate(),
				h.getUserReturnDate() == null ? "대여중" : h.getUserReturnDate(),
				h.getDelayDate() > 0 ? h.getDelayDate() + "일" : ""
		};
	}

	@Override
	public void initList() {
		list = new ArrayList<History>();
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"회원번호", "회원이름",
				"대여일", "반납기한", "반납일", "연체일수"};
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
			String isDelayed =(String)table.getValueAt(row, 5);
			if (isDelayed != null) {
				if (isDelayed.equals("")) {
					setBackground(Color.white);
				} else {
					setBackground(Color.red);
				}
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
