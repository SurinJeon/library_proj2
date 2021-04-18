package library_proj2.content.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import library_proj2.content.cmb.UserCmb;
import library_proj2.content.detail.UserDetail;
import library_proj2.dto.RentalStatus;
import library_proj2.dto.User;
import library_proj2.exception.NullListException;
import library_proj2.service.MainService;
import library_proj2.ui.RentalPage;

@SuppressWarnings("serial")
public class UserTable extends AbstractCustomTable<User> implements MouseListener{
	
	private MainService service;
	private UserCmb pCmbUser;
	private List<RentalStatus> rentList;
	private RentalTable pRentalList;
	private BookTable pBookList;
	private UserDetail pUserDetail;
	private int delimiter;
	private BookTable pBookListMain;
	
	public UserTable() {
	}
	
	public UserTable(int delimiter) {
		this.delimiter = delimiter;
		table.addMouseListener(this);
		pRentalList = new RentalTable(delimiter);
		pUserDetail = new UserDetail();
	}

	@Override
	protected void setAlignAndWidth() {
		
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3);
		setTableCellWidth(150, 100, 250, 250);
		
	}

	@Override
	protected Object[] toArray(User u) {
		return new Object[] {u.getUserNo(), u.getUserName(), u.getTel(), u.getPhone()};
	}

	@Override
	protected void initList() {
		list = service.userList();
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"회원번호", "이름", "전화번호", "휴대전화"};
	}

	public void setService(MainService service) {
		this.service = service;
	}
	
	public void setList(List<User> list) {
		this.list = list;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(delimiter == 1 && e.getClickCount() == 2) {
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			int userNo = (Integer)table.getValueAt(idx, 0);
		
			List<User> userList = service.searchByUserNo(new User(userNo));
			
			RentalPage frame = new RentalPage();
			frame.getpUserDetail().setUser(userList.get(0));
			
			frame.setpBookListMain(pBookListMain);
			frame.setVisible(true);
			
			List<User> searchUser = frame.getpUserList().getList()
					.stream().filter(user -> user.getUserNo()==userNo)
					.collect(Collectors.toList());
			User user = searchUser.get(0);
			int idxRent = frame.getpUserList().getList().indexOf(user);
			frame.getpUserList().table.setRowSelectionInterval(idxRent, idxRent);
			
		}
		
		
		if(delimiter == 1 && e.getClickCount() == 1) {
			try {
				List<RentalStatus> rentList = new ArrayList<RentalStatus>();
				JTable table = (JTable)e.getSource();
				int idx = table.getSelectedRow();
				int userNo = (int) table.getValueAt(idx, 0);
				rentList = service.mainToReturn(null, new User(userNo));
				
				if(rentList != null) {
					pRentalList.setList(rentList);
					pRentalList.setList();
				} else {
					List list = new ArrayList();
					pRentalList.setList(list);
					pRentalList.setList();
				}
			} catch (NullListException e1) {
				e1.printStackTrace();
			}
		}
		
		if((delimiter == 2 || delimiter == 3) && e.getClickCount() == 1) {
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			int userNo = (int)table.getValueAt(idx, 0);
			
			User searchUser = service.searchByUserNo(new User(userNo)).get(0);
			if (searchUser != null) {
				pUserDetail.setUser(searchUser);
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

	public RentalTable getpRentalList() {
		return pRentalList;
	}

	public void setpRentalList(RentalTable pRentalList) {
		this.pRentalList = pRentalList;
	}

	public void setpBookList(BookTable pBookList) {
		this.pBookList = pBookList;
	}

	public UserDetail getpUserDetail() {
		return pUserDetail;
	}

	public void setpUserDetail(UserDetail pUserDetail) {
		this.pUserDetail = pUserDetail;
	}

	public int getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(int delimiter) {
		this.delimiter = delimiter;
	}

	public BookTable getpBookListMain() {
		return pBookListMain;
	}

	public void setpBookListMain(BookTable pBookListMain) {
		this.pBookListMain = pBookListMain;
	}
	
}
