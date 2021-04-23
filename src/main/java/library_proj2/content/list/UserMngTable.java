package library_proj2.content.list;

import javax.swing.SwingConstants;

import library_proj2.dto.User;
import library_proj2.exception.NotSelectedException;
import library_proj2.service.UserService;

public class UserMngTable extends AbstractCustomTable<User>{

	private UserService service;

	public void setService(UserService service) {
		this.service = service;
	}

	public UserMngTable() {
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);
		setTableCellWidth(100, 100, 250, 250, 250, 100);
		
	}

	@Override
	protected Object[] toArray(User u) {
		return new Object[] {
				u.getUserNo(), 
				u.getUserName(), 
				u.getUserBirth(),
				u.getTel(), 
				u.getPhone(),
				u.getAddress()
				};
	}

	@Override
	protected void initList() {
//		list = new ArrayList<User>();
		list = service.userList();
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"회원번호", "이름", "생년월일", "전화번호", "휴대전화", "주소"};
	}

	public User getItem() {
		int idx = table.getSelectedRow();
		int userNo = (int) table.getValueAt(idx, 0);
		
		if (idx == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new User(userNo)));
	}

}
