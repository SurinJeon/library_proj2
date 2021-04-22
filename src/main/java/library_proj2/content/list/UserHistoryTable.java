package library_proj2.content.list;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import library_proj2.dto.History;
import library_proj2.service.HistoryService;

@SuppressWarnings("serial")
public class UserHistoryTable extends AbstractCustomTable<History>{
	private HistoryService service;
//	private List<History> list;
	private int userNo;
	
	
	
	public void setList(int userNo) {
		this.userNo = userNo;
		list = service.userHistory(userNo);
	}

	public void setService(HistoryService service) {
		this.service = service;
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);
		setTableCellWidth(70, 250, 70, 100, 100, 100, 70);
		setTableCellCondition(6);
	}

	@Override
	protected Object[] toArray(History h) {
		return new Object[] {
				h.getBookNo(), 
				h.getBookTitle(), 
				h.getCategoryName(), 
				h.getRentalDate(), 
				h.getReturnDate(), 
				h.getUserReturnDate() == null ? "대여중" : h.getUserReturnDate(), 
				h.getDelayDate() > 0 ? h.getDelayDate() + "일" : ""
				};
	}

	@Override
	protected void initList() {
		list = new ArrayList<History>();
		System.out.println("init()");
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"도서번호", "도서제목", "도서구분", 
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
			String isDelayed =(String)table.getValueAt(row, 6);
			if (isDelayed.equals("")) {
				setBackground(Color.white);
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
