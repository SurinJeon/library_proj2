package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import library_proj2.content.UserPanel;
import library_proj2.content.list.UserTable;
import library_proj2.dto.User;
import library_proj2.service.UserService;
import library_proj2.content.list.UserMngTable;

public class UserMngPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private String id;
	private String pass;
	private UserPanel pDetail;
	private JButton btnAdd;
	private UserService service;
	private UserTable pUserListMain;
	private UserMngTable pList;
	
	public void setId(String id) {
		this.id = id;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getId() {
		return id;
	}
	public String getPass() {
		return pass;
	}
	public UserTable getpUserListMain() {
		return pUserListMain;
	}
	public void setpUserListMain(UserTable pUserListMain) {
		this.pUserListMain = pUserListMain;
	}

	public void setService(UserService service) {
		this.service = service;
	}
	public UserMngPage() {
		initialize();
	}
	
	public UserPanel getpDetail() {
		return pDetail;
	}
	public void setpDetail(UserPanel pDetail) {
		this.pDetail = pDetail;
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		pList = new UserMngTable();
		pList.setService(service);
		pList.loadData();
		tabbedPane.addTab("회원목록", null, pList, null);
		
		JPanel pInsert = new JPanel();
		tabbedPane.addTab("회원추가", null, pInsert, null);
		pInsert.setLayout(new BorderLayout(0, 0));
		
		pDetail = new UserPanel();
		pInsert.add(pDetail, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		pInsert.add(pBtn, BorderLayout.SOUTH);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		JButton btnClear = new JButton("삭제");
		pBtn.add(btnClear);
		
		JPanel pHistory = new JPanel();
		tabbedPane.addTab("대여내역", null, pHistory, null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	
	protected void actionPerformedBtnAdd(ActionEvent e) {
		String pass = new String(pDetail.getPfPass().getPassword());
		if(pass.equals(getPass())) {
			User user = pDetail.getUser();
			service.insertUser(user);
			
			JOptionPane.showMessageDialog(null, "추가되었습니다.");
			pDetail.clearTf();
			pUserListMain.loadData();
		} else {
			JOptionPane.showMessageDialog(null, "관리자 비밀번호가 아닙니다.");
			pDetail.clearTf();
		}
	}
}



















