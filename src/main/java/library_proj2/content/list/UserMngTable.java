package library_proj2.content.list;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import library_proj2.dto.User;
import library_proj2.exception.NotSelectedException;
import library_proj2.service.UserService;

public class UserMngTable extends AbstractCustomTable<User>{

	private UserService service;

	public void setService(UserService service) {
		this.service = service;
	}

	public void setPopupMenu(JPopupMenu popupMenu) {
		table.setComponentPopupMenu(popupMenu);
	}

	public UserMngTable() {
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);
		setTableCellWidth(100, 100, 150, 150, 150, 100, 100);
		setTableCellCondition(6);
	}

	@Override
	protected Object[] toArray(User u) {
		return new Object[] {
				u.getUserNo(), 
				u.getUserName(), 
				u.getUserBirth(),
				u.getTel(), 
				u.getPhone(),
				u.getAddress(),
				u.getIsBlackList() == 1 ? "O" : "X"
				};
	}

	@Override
	protected void initList() {
		list = service.userList();
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"회원번호", "이름", "생년월일", "전화번호", "휴대전화", "주소", "블랙리스트"};
	}

	public User getItem() {
		int idx = table.getSelectedRow();
		int userNo = 0;
		
		try {
			userNo = (int) table.getValueAt(idx, 0);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}
		
		if (idx == -1) {
			throw new NotSelectedException();
		}
		return service.searchByUserNo(new User(userNo));
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
			String isBlackList = String.valueOf(table.getValueAt(row, 6));
			if (isBlackList.equals("O")) {
				setBackground(Color.red);
			} else {
				setBackground(Color.white);
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
