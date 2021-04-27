package library_proj2.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library_proj2.content.UserPanel;
import library_proj2.content.list.UserMngTable;
import library_proj2.content.list.UserTable;
import library_proj2.service.HistoryService;
import library_proj2.service.UserService;

public class UserMngPage2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private String id;
	private String pass;
	private UserService userService;
	private HistoryService hisService;
	private UserTable pUserListMain;
	private JPopupMenu popMenu;
	private UserMngTable pList;
	private JPanel pSwitch;
	
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
		this.userService = service;
	}
	public UserMngTable getpList() {
		return pList;
	}
	public void setpList(UserMngTable pList) {
		this.pList = pList;
	}
	
	public UserMngPage2() {
		userService = new UserService();
		hisService = new HistoryService();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pList = new UserMngTable();
		pList.setService(userService);
		pList.loadData();
		pList.setComponentPopupMenu(createPopMenu());
		contentPane.add(pList);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		switch (cmd) {
		case "추가":
			actionPerformedInsertMenu(e);
			break;
		case "수정":
			actionPerformedUpdateMenu(e);
			break;
		case "삭제":
			actionPerformedDeleteMenu(e);
			break;
		case "대여내역":
			actionPerformedHistoryMenu(e);
			break;

		}

	}
	
	private void actionPerformedInsertMenu(ActionEvent e) {
		pSwitch = new UserPanel();
		contentPane.add(pSwitch);
	}

	private void actionPerformedUpdateMenu(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void actionPerformedDeleteMenu(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void actionPerformedHistoryMenu(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JPopupMenu  createPopMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem insert = new JMenuItem("추가");
		insert.setHorizontalAlignment(SwingConstants.CENTER);
		insert.addActionListener(this);
		popMenu.add(insert);
		
		JSeparator separator1 = new JSeparator();
		popMenu.add(separator1);
		
		JMenuItem update = new JMenuItem("수정");
		update.setHorizontalAlignment(SwingConstants.CENTER);
		update.addActionListener(this);
		popMenu.add(update);
		
		JSeparator separator2 = new JSeparator();
		popMenu.add(separator2);
		
		JMenuItem delete = new JMenuItem("삭제");
		delete.setHorizontalAlignment(SwingConstants.CENTER);
		delete.addActionListener(this);
		popMenu.add(delete);
		
		JSeparator separator3 = new JSeparator();
		popMenu.add(separator3);
		
		JMenuItem history = new JMenuItem("대여내역");
		history.setHorizontalAlignment(SwingConstants.CENTER);
		history.addActionListener(this);
		popMenu.add(history);
		
		return popMenu;
	}

	
}



















