package library_proj2.content;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import library_proj2.dto.User;
import library_proj2.exception.InvalidCheckException;
import javax.swing.DefaultComboBoxModel;

public class UserPanel extends JPanel {
	private JTextField tfUserNo;
	private JTextField tfUserName;
	private JTextField tfAccount;
	private JTextField tfTel;
	private JTextField tfPhone;
	private JTextField tfAddress;
	private JPasswordField pfPass;
	private JDateChooser dateChooser;
	private JComboBox cmbBlackList;

	public JPasswordField getPfPass() {
		return pfPass;
	}
	
	public JTextField getTfUserNo() {
		return tfUserNo;
	}

	public void setTfUserNo(JTextField tfUserNo) {
		this.tfUserNo = tfUserNo;
	}

	public UserPanel() {
		initialize();
		cmbBlackList.setSelectedIndex(-1);
	}


	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblUserNo = new JLabel("회원번호: ");
		lblUserNo.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblUserNo);
		
		tfUserNo = new JTextField();
		add(tfUserNo);
		tfUserNo.setColumns(10);
		
		JLabel lblUserName = new JLabel("회원이름: ");
		lblUserName.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblUserName);
		
		tfUserName = new JTextField();
		tfUserName.setColumns(10);
		add(tfUserName);
		
		JLabel lblBirth = new JLabel("회원생년월일: ");
		lblBirth.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblBirth);
		
		dateChooser = new JDateChooser();
		add(dateChooser);
		
		JLabel lblAccount = new JLabel("계정: ");
		lblAccount.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblAccount);
		
		tfAccount = new JTextField();
		tfAccount.setColumns(10);
		add(tfAccount);
		
		JLabel lblTel = new JLabel("전화번호: ");
		lblTel.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblTel);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		add(tfTel);
		
		JLabel lblPhone = new JLabel("휴대전화: ");
		lblPhone.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		add(tfPhone);
		
		JLabel lblAddress = new JLabel("주소: ");
		lblAddress.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		add(tfAddress);
		
		JLabel lblBlackList = new JLabel("블랙리스트 여부: ");
		lblBlackList.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblBlackList);
		
		cmbBlackList = new JComboBox();
		cmbBlackList.setModel(new DefaultComboBoxModel(new String[] {"예", "아니오"}));
		add(cmbBlackList);
		
		JLabel lblPass = new JLabel("관리자 비밀번호: ");
		lblPass.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblPass);
		
		pfPass = new JPasswordField();
		add(pfPass);
	}
	
	public void setUser(User user) {

		int idx = 0;
		
		if(user.getIsBlackList() == 0) {
			idx = 1;
		} 
		
		tfUserName.setText(user.getUserName());
		tfUserNo.setText(user.getUserNo() + "");
		tfTel.setText(user.getTel());
		tfPhone.setText(user.getPhone());
		dateChooser.setDate(user.getUserBirth());
		tfAddress.setText(user.getAddress());
		tfAccount.setText(user.getAccount());
		cmbBlackList.setSelectedIndex(idx);
	}
	
	public User getUser() {
		
		int userNo = 0;
		String userName = "";
		Date userBirth = null;
		String account = "";
		String tel = "";
		String phone = "";
		String address = "";
		int isBlackList = 0;
		try {
			validCheck();
			userNo = Integer.parseInt(tfUserNo.getText().trim());
			userName = tfUserName.getText().trim();
			userBirth = dateChooser.getDate();
			account = tfAccount.getText().trim();
			tel = tfTel.getText().trim();
			phone = tfPhone.getText().trim();
			address = tfAddress.getText().trim();
			isBlackList = cmbBlackList.getSelectedIndex() == 0 ? 1 : 0; 
		} catch (InvalidCheckException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return new User(userNo, userName, userBirth, account, tel, phone, address, isBlackList);
	}
	
	private void validCheck() {
		if(tfUserNo.getText().trim().equals("") 
				|| tfUserName.getText().trim().equals("")
				|| dateChooser.getDate() == null
				|| tfAccount.getText().trim().equals("")
				|| tfTel.getText().trim().equals("")
				|| tfPhone.getText().trim().equals("")
				|| tfAddress.getText().trim().equals("")) {
//			throw new InvalidCheckException();
		}
	}
	
	public void clearTf() {
		tfUserNo.setText("");
		tfUserName.setText("");
		dateChooser.setDate(null);
		tfAccount.setText("");
		tfTel.setText("");
		tfPhone.setText("");
		tfAddress.setText("");
		pfPass.setText("");
	}

}
