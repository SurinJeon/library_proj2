package library_proj2.content.detail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import library_proj2.dto.User;

@SuppressWarnings("serial")
public class UserDetail extends JPanel {
	private JTextField tfUserName;
	private JTextField tfUserNo;
	private JTextField tfTel;
	private JTextField tfPhone;
	private JTextField tfYear;
	private JTextField tfMonth;
	private JTextField tfDate;
	private JTextField tfAddress;
	private JTextField tfAccount;

	public UserDetail() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("이름:");
		lblUserName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserName.setBounds(12, 36, 57, 15);
		panel.add(lblUserName);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(81, 33, 116, 21);
		panel.add(tfUserName);
		tfUserName.setColumns(10);
		
		JLabel lblUserNo = new JLabel("회원번호:");
		lblUserNo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserNo.setBounds(209, 36, 57, 15);
		panel.add(lblUserNo);
		
		tfUserNo = new JTextField();
		tfUserNo.setColumns(10);
		tfUserNo.setBounds(278, 33, 116, 21);
		panel.add(tfUserNo);
		
		JLabel lblTel = new JLabel("전화번호:");
		lblTel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTel.setBounds(12, 66, 57, 15);
		panel.add(lblTel);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		tfTel.setBounds(81, 63, 116, 21);
		panel.add(tfTel);
		
		JLabel lblPhone = new JLabel("휴대전화:");
		lblPhone.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPhone.setBounds(209, 66, 57, 15);
		panel.add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(278, 63, 116, 21);
		panel.add(tfPhone);
		
		JLabel lblBirth = new JLabel("생년월일:");
		lblBirth.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBirth.setBounds(12, 96, 57, 15);
		panel.add(lblBirth);
		
		tfYear = new JTextField();
		tfYear.setColumns(10);
		tfYear.setBounds(81, 93, 67, 21);
		panel.add(tfYear);
		
		JLabel lblYear = new JLabel("년");
		lblYear.setBounds(150, 96, 34, 15);
		panel.add(lblYear);
		
		tfMonth = new JTextField();
		tfMonth.setColumns(10);
		tfMonth.setBounds(171, 94, 34, 21);
		panel.add(tfMonth);
		
		JLabel lblMonth = new JLabel("월");
		lblMonth.setBounds(208, 96, 34, 15);
		panel.add(lblMonth);
		
		tfDate = new JTextField();
		tfDate.setColumns(10);
		tfDate.setBounds(228, 94, 34, 21);
		panel.add(tfDate);
		
		JLabel lblDate = new JLabel("일");
		lblDate.setBounds(265, 96, 34, 15);
		panel.add(lblDate);
		
		JLabel lblAddress = new JLabel("주소:");
		lblAddress.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAddress.setBounds(12, 130, 57, 15);
		panel.add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(81, 127, 116, 21);
		panel.add(tfAddress);
		
		JLabel lblAccount = new JLabel("계정:");
		lblAccount.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAccount.setBounds(209, 130, 57, 15);
		panel.add(lblAccount);
		
		tfAccount = new JTextField();
		tfAccount.setColumns(10);
		tfAccount.setBounds(278, 127, 143, 21);
		panel.add(tfAccount);
		
		JLabel lblText = new JLabel("대여회원상세정보");
		lblText.setBounds(12, 10, 185, 15);
		panel.add(lblText);
	}
	
	public void setUser(User user) {
		tfUserName.setText(user.getUserName());
		tfUserNo.setText(user.getUserNo() + "");
		tfTel.setText(user.getTel());
		tfPhone.setText(user.getPhone());
		tfYear.setText((user.getUserBirth().getYear() + 1900) + "");
		tfMonth.setText((user.getUserBirth().getMonth() + 1) + "");
		tfDate.setText(user.getUserBirth().getDate() + "");
		tfAddress.setText(user.getAddress());
		tfAccount.setText(user.getAccount());
	}
	
	public User getUser() {
		validCheck();
		int year = Integer.parseInt(tfYear.getText()) - 1900;
		int month = Integer.parseInt(tfMonth.getText()) - 1;
		int birthDate = Integer.parseInt(tfDate.getText());
		Date birth = new Date(year, month, birthDate);
		return new User(Integer.parseInt(tfUserNo.getText()), tfUserName.getText(), birth, tfAccount.getText(), tfTel.getText(), tfPhone.getText(), tfAddress.getText());
	}
	private void validCheck() {
		// validCheck 체크하기
		
	}
	
	public void clearTf() {
		tfUserName.setText("");
		tfUserNo.setText("");
		tfTel.setText("");
		tfPhone.setText("");
		tfYear.setText("");
		tfMonth.setText("");
		tfDate.setText("");
		tfAddress.setText("");
		tfAccount.setText("");
	}
}
