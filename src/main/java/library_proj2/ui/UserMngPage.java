package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library_proj2.content.UserPanel;
import library_proj2.content.list.UserTable;
import library_proj2.dto.User;
import library_proj2.service.HistoryService;
import library_proj2.service.UserService;
import library_proj2.content.list.UserMngTable;
import library_proj2.content.list.UserHistoryTable;
import library_proj2.content.cmb.HistoryCmb;

public class UserMngPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private String id;
	private String pass;
	private UserPanel pDetail;
	private JButton btnAdd;
	private UserService userService;
	private HistoryService hisService;
	private UserTable pUserListMain;
	private UserMngTable pList;
	private JButton btnClear;
	private HistoryCmb pCmb;
	private UserHistoryTable pHistoryList;
	private JTabbedPane tabbedPane;
	private JPopupMenu popMenu;
	
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

	public UserPanel getpDetail() {
		return pDetail;
	}
	public void setpDetail(UserPanel pDetail) {
		this.pDetail = pDetail;
	}
	
	public UserMngTable getpList() {
		return pList;
	}
	public void setpList(UserMngTable pList) {
		this.pList = pList;
	}
	
	public UserMngPage() {
		userService = new UserService();
		hisService = new HistoryService();
		initialize();
		pCmb.setpHistoryList(pHistoryList);
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		pList = new UserMngTable();
		pList.setService(userService);
		pList.loadData();
		tabbedPane.addTab("????????????", null, pList, null);
		
		JPanel pInsert = new JPanel();
		tabbedPane.addTab("????????????", null, pInsert, null);
		pInsert.setLayout(new BorderLayout(0, 0));
		
		pDetail = new UserPanel();
		pDetail.getTfUserNo().setText(userService.nextUserNo() + "");
		pInsert.add(pDetail, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		pInsert.add(pBtn, BorderLayout.SOUTH);
		
		btnAdd = new JButton("??????");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnClear = new JButton("??????");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
		
		JPanel pHistory = new JPanel();
		tabbedPane.addTab("????????????", null, pHistory, null);
		pHistory.setLayout(new BorderLayout(0, 0));
		
		pCmb = new HistoryCmb(4);
		pHistory.add(pCmb, BorderLayout.NORTH);
		
		pHistoryList = new UserHistoryTable();
		pHistoryList.setService(hisService);
		pHistoryList.loadData();
		pHistory.add(pHistoryList, BorderLayout.CENTER);
		
//		popMenu = new JPopupMenu();
//		pList.setComponentPopupMenu(createPopMenu(popMenu));
//		pDetail.setComponentPopupMenu(createPopMenu(popMenu));
//		pHistoryList.setComponentPopupMenu(createPopMenu(popMenu));
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		
		if(e.getActionCommand().equals("??????")) {
			actionPerformedUpdateMenu(e);
		}
		
		if(e.getActionCommand().equals("??????")) {
			actionPerformedDeleteMenu(e);
		}
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		String pass = new String(pDetail.getPfPass().getPassword());
		if(pass.equals(getPass())) {
			User user = pDetail.getUser();
			userService.insertUser(user);
			
			JOptionPane.showMessageDialog(null, "?????????????????????.");
			pDetail.clearTf();
			pList.loadData();
			pUserListMain.loadData();
			tabbedPane.setSelectedIndex(0);
		} else {
			JOptionPane.showMessageDialog(null, "????????? ??????????????? ????????????.");
			pDetail.clearTf();
		}
	}
	protected void actionPerformedBtnClear(ActionEvent e) {
		pDetail.clearTf();
	}
	
	
	private void actionPerformedUpdateMenu(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void actionPerformedDeleteMenu(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JPopupMenu   createPopMenu(/* JPopupMenu popMenu */) {
		JPopupMenu popMenu = new JPopupMenu();
		JMenuItem update = new JMenuItem("??????");
		update.setHorizontalAlignment(SwingConstants.CENTER);
		update.addActionListener(this);
		popMenu.add(update);
		
		JSeparator separator = new JSeparator();
		popMenu.add(separator);
		
		JMenuItem delete = new JMenuItem("??????");
		delete.setHorizontalAlignment(SwingConstants.CENTER);
		delete.addActionListener(this);
		popMenu.add(delete);
		
		return popMenu;
	}

	
}



















