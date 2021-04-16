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

import library_proj2.content.list.UserTable;
import library_proj2.dto.User;
import library_proj2.exception.NotAvailableException;
import library_proj2.service.MainService;

@SuppressWarnings("serial")
public class UserCmb extends JPanel implements ActionListener {
	private JTextField tfUser;
	private JButton btnSearch;
	private MainService service;
	private JComboBox<String> cmbUser;
	private List<User> list =  new ArrayList<User>();
	private UserTable pUserList;
	private JLabel lblUser;
	private int delimiter;

	public UserCmb(int delimiter) {
		initialize(delimiter);
		cmbUser.setSelectedIndex(-1);
	}
	private void initialize(int delimiter) {
		setLayout(new GridLayout(0, 4, 10, 0));
		
		if (delimiter == 1) {
			lblUser = new JLabel("빠른회원검색 :");
		} else if(delimiter == 2) {
			lblUser = new JLabel("검색방법:");
		}
		lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblUser);
		
		cmbUser = new JComboBox<String>();
		cmbUser.setModel(new DefaultComboBoxModel<String>(new String[] {"회원번호", "회원이름", "계정", "휴대전화"}));
		add(cmbUser);
		
		tfUser = new JTextField();
		add(tfUser);
		tfUser.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		add(btnSearch);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
	}
	protected void actionPerformedBtnSearch(ActionEvent e) {
		List<User> blankList = new ArrayList();
		try {
			String searchItem = (String) cmbUser.getSelectedItem();
			list = switchList(searchItem); 
			
			if(list != null) {
				pUserList.setList(list); // pUserList라는 패널에 list를 세팅
				pUserList.setList(); // 로드하는 메소드
			} else {
				throw new NotAvailableException("해당 회원이 존재하지 않습니다.");
			}
		} catch (NotAvailableException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			pUserList.setList(blankList);
			pUserList.setList();
			clearTf();
		}
	}
	
	public List<User> switchList(String searchItem) {
		try {
			switch(searchItem){
			case "회원번호":
				list = service.searchByUserNo(new User(Integer.parseInt(tfUser.getText())));		
				break;
			case "회원이름":
				list = service.searchByUserName(new User(tfUser.getText()));
				break;
			case "계정":
				list = service.searchByUserAccount(new User(tfUser.getText(), null));
				break;
			case "휴대전화":
				list = service.searchByUserPhone(new User(null, tfUser.getText()));
				break;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "형식에 알맞게 입력해주세요.");
		}
		return list;
	}
	
	public void setService(MainService service) {
		this.service = service;
	}
	
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public void clearTf() {
		tfUser.setText("");
	}
	
	public UserTable getpUserList() {
		return pUserList;
	}
	public void setpUserList(UserTable pUserList) {
		this.pUserList = pUserList;
	}
}
