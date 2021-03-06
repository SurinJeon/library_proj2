package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library_proj2.content.UserPanel;
import library_proj2.content.cmb.HistoryCmb;
import library_proj2.content.list.UserHistoryTable;
import library_proj2.content.list.UserMngTable;
import library_proj2.content.list.UserTable;
import library_proj2.dto.User;
import library_proj2.service.HistoryService;
import library_proj2.service.UserService;

@SuppressWarnings("serial")
public class UserMngPage3 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private String id;
	private String pass;
	private UserService userService;
	private HistoryService hisService;
	private UserTable pUserListMain;
	private UserMngTable pList;
	private JPanel pContain;
	private JPanel pSwitch;
	private JPanel pBtn;
	private JButton btnCheck;
	private JButton btnClear;
	private UserPanel pDetail;
	private HistoryCmb pCmb;
	private UserHistoryTable pHistoryList;

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

	public UserMngPage3() {
		userService = new UserService();
		hisService = new HistoryService();
		initialize();
	}

	private void initialize() {
		setTitle("??????????????????");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pContain = new JPanel();
		contentPane.add(pContain, BorderLayout.CENTER);
		pContain.setLayout(new GridLayout(2, 1, 0, 0));

		pList = new UserMngTable();
		pList.setService(userService);
		pList.loadData();
		pList.setPopupMenu(createPopMenu());
		pContain.add(pList);

		pSwitch = new JPanel();
		pContain.add(pSwitch);
		pSwitch.setLayout(new BorderLayout(0, 0));

		pBtn = new JPanel();
		pSwitch.add(pBtn, BorderLayout.SOUTH);

		btnCheck = new JButton("??????");
		btnCheck.addActionListener(this);
		pBtn.add(btnCheck);

		btnClear = new JButton("??????");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
		pBtn.setVisible(false);

	}

	public JPopupMenu createPopMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem insert = new JMenuItem("??????");
		insert.setHorizontalAlignment(SwingConstants.CENTER);
		insert.addActionListener(this);
		popMenu.add(insert);

		JSeparator separator1 = new JSeparator();
		popMenu.add(separator1);

		JMenuItem update = new JMenuItem("??????");
		update.setHorizontalAlignment(SwingConstants.CENTER);
		update.addActionListener(this);
		popMenu.add(update);

		JSeparator separator2 = new JSeparator();
		popMenu.add(separator2);

		JMenuItem delete = new JMenuItem("??????");
		delete.setHorizontalAlignment(SwingConstants.CENTER);
		delete.addActionListener(this);
		popMenu.add(delete);

		JSeparator separator3 = new JSeparator();
		popMenu.add(separator3);

		JMenuItem history = new JMenuItem("????????????");
		history.setHorizontalAlignment(SwingConstants.CENTER);
		history.addActionListener(this);
		popMenu.add(history);

		return popMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		} else if (e.getSource() == btnCheck) {
			actionPerformedBtnCheck(e);
		} else {

			String cmd = e.getActionCommand();

			switch (cmd) {
			case "??????":
				actionPerformedInsertMenu(e);
				break;
			case "??????":
				btnCheck.setText("??????");
				actionPerformedUpdateMenu(e);
				break;
			case "??????":
				actionPerformedDeleteMenu(e);
				break;
			case "????????????":
				actionPerformedHistoryMenu(e);
				break;
			}
		}
	}

	private void actionPerformedInsertMenu(ActionEvent e) {
		removeComp();

		if (btnCheck.getText().equals("??????")) {
			btnCheck.setText("??????");
		}

		pDetail = new UserPanel();
		pDetail.getTfUserNo().setText(userService.nextUserNo() + "");
		pDetail.getTfUserNo().setEditable(false);
		pSwitch.add(pDetail, BorderLayout.CENTER);
		pBtn.setVisible(true);
		this.revalidate();

	}

	private void actionPerformedUpdateMenu(ActionEvent e) {
		removeComp();

		if (btnCheck.getText().equals("??????")) {
			btnCheck.setText("??????");
		}
		try {
			pDetail = new UserPanel();

			User user = pList.getItem();

			pDetail.setUser(user);
			pDetail.getTfUserNo().setEditable(false);

			pSwitch.add(pDetail, BorderLayout.CENTER);
			pBtn.setVisible(true);
			this.revalidate();

		} catch (IndexOutOfBoundsException e1) {
			removeComp();
			JOptionPane.showMessageDialog(null, "????????? ??????????????????.");
		}
	}

	private void actionPerformedDeleteMenu(ActionEvent e) {
		try {
			User user = pList.getItem();

			int reply = JOptionPane.showConfirmDialog(null,
					user.getUserName() + "(" + user.getUserNo() + ")" + "??? ?????????????????????????", "??????", JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				userService.deleteUser(user);
				JOptionPane.showMessageDialog(null, "?????????????????????.");
				pUserListMain.loadData();
				pList.loadData();
			} else {
				JOptionPane.showMessageDialog(null, "?????? ??????");
				pUserListMain.loadData();
				pList.loadData();
			}
		} catch (IndexOutOfBoundsException e1) {
			removeComp();
			JOptionPane.showMessageDialog(null, "????????? ??????????????????.");
		}

	}

	private void actionPerformedHistoryMenu(ActionEvent e) {
		try {
			removeComp();

			pBtn.setVisible(false);
			pCmb = new HistoryCmb(4);

			User user = pList.getItem();
			pCmb.getTfNo().setText(user.getUserNo() + "");

			pHistoryList = new UserHistoryTable();
			try {
				pHistoryList.setService(hisService);
				if (hisService.userHistory(user.getUserNo()) != null) {
					pHistoryList.setList(user.getUserNo());
				} else {
					pHistoryList.initList();
				}
				pHistoryList.setList();
			} catch (NullPointerException e1) {
				pHistoryList.loadData();
			}
			pSwitch.add(pHistoryList, BorderLayout.CENTER);
			pSwitch.add(pCmb, BorderLayout.NORTH);

			this.revalidate();
		} catch (IndexOutOfBoundsException e1) {
			removeComp();
			JOptionPane.showMessageDialog(null, "????????? ??????????????????.");
		}
	}

	protected void actionPerformedBtnCheck(ActionEvent e) {

		if (btnCheck.getText().equals("??????")) {
			String pass = new String(pDetail.getPfPass().getPassword());
			if (pass.equals(getPass())) {
				User user = pDetail.getUser();
				userService.insertUser(user);

				JOptionPane.showMessageDialog(null, "?????????????????????.");
				pDetail.clearTf();
				pList.loadData();
				pUserListMain.loadData();

				pDetail.getTfUserNo().setText(userService.nextUserNo() + "");
				pDetail.getTfUserNo().setEditable(false);
			} else {
				JOptionPane.showMessageDialog(null, "????????? ??????????????? ????????????.");
				pDetail.clearTf();
			}
		} else if (btnCheck.getText().equals("??????")) {
			String pass = new String(pDetail.getPfPass().getPassword());
			if (pass.equals(getPass())) {
				User user = pDetail.getUser();
				userService.updateUser(user);

				JOptionPane.showMessageDialog(null, "?????????????????????.");
				pDetail.clearTf();
				pList.loadData();
				pUserListMain.loadData();

				pDetail.getTfUserNo().setEditable(true);
//				pDetail.clearTf();

				btnCheck.setText("??????");
			} else {
				JOptionPane.showMessageDialog(null, "????????? ??????????????? ????????????.");
				pDetail.clearTf();
			}
		}
	}

	protected void actionPerformedBtnClear(ActionEvent e) {
		pDetail.clearTf();
	}

	public void removeComp() {
		if (pDetail != null) {
			pSwitch.remove(pDetail);
		}

		if (pCmb != null) {
			pSwitch.remove(pCmb);
		}

		if (pHistoryList != null) {
			pSwitch.remove(pHistoryList);
		}
	}

}
