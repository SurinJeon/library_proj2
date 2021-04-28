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
		setTitle("회원관리화면");
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

		btnCheck = new JButton("추가");
		btnCheck.addActionListener(this);
		pBtn.add(btnCheck);

		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
		pBtn.setVisible(false);

	}

	public JPopupMenu createPopMenu() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		} else if (e.getSource() == btnCheck) {
			actionPerformedBtnCheck(e);
		} else {

			String cmd = e.getActionCommand();

			switch (cmd) {
			case "추가":
				actionPerformedInsertMenu(e);
				break;
			case "수정":
				btnCheck.setText("수정");
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
	}

	private void actionPerformedInsertMenu(ActionEvent e) {
		removeComp();

		if (btnCheck.getText().equals("수정")) {
			btnCheck.setText("추가");
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

		if (btnCheck.getText().equals("추가")) {
			btnCheck.setText("수정");
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
			JOptionPane.showMessageDialog(null, "회원을 선택하십시오.");
		}
	}

	private void actionPerformedDeleteMenu(ActionEvent e) {
		try {
			User user = pList.getItem();

			int reply = JOptionPane.showConfirmDialog(null,
					user.getUserName() + "(" + user.getUserNo() + ")" + "를 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				userService.deleteUser(user);
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
				pUserListMain.loadData();
				pList.loadData();
			} else {
				JOptionPane.showMessageDialog(null, "삭제 실패");
				pUserListMain.loadData();
				pList.loadData();
			}
		} catch (IndexOutOfBoundsException e1) {
			removeComp();
			JOptionPane.showMessageDialog(null, "회원을 선택하십시오.");
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
			JOptionPane.showMessageDialog(null, "회원을 선택하십시오.");
		}
	}

	protected void actionPerformedBtnCheck(ActionEvent e) {

		if (btnCheck.getText().equals("추가")) {
			String pass = new String(pDetail.getPfPass().getPassword());
			if (pass.equals(getPass())) {
				User user = pDetail.getUser();
				userService.insertUser(user);

				JOptionPane.showMessageDialog(null, "추가되었습니다.");
				pDetail.clearTf();
				pList.loadData();
				pUserListMain.loadData();

				pDetail.getTfUserNo().setText(userService.nextUserNo() + "");
				pDetail.getTfUserNo().setEditable(false);
			} else {
				JOptionPane.showMessageDialog(null, "관리자 비밀번호가 아닙니다.");
				pDetail.clearTf();
			}
		} else if (btnCheck.getText().equals("수정")) {
			String pass = new String(pDetail.getPfPass().getPassword());
			if (pass.equals(getPass())) {
				User user = pDetail.getUser();
				userService.updateUser(user);

				JOptionPane.showMessageDialog(null, "수정되었습니다.");
				pDetail.clearTf();
				pList.loadData();
				pUserListMain.loadData();

				pDetail.getTfUserNo().setEditable(true);
//				pDetail.clearTf();

				btnCheck.setText("추가");
			} else {
				JOptionPane.showMessageDialog(null, "관리자 비밀번호가 아닙니다.");
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
