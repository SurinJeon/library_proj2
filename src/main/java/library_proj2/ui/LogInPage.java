package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import library_proj2.content.LogInPanel;
import library_proj2.dto.Manager;
import library_proj2.exception.InvalidCheckException;
import library_proj2.service.LogInService;

@SuppressWarnings("serial")
public class LogInPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnLogIn;
	private LogInPanel pLogIn;
	private LogInService service;

	public LogInPage() {
		service = new LogInService();
		initialize();
		
	}
	private void initialize() {
		setTitle("관리자 로그인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pLogIn = new LogInPanel();
		pLogIn.setService(service);
		contentPane.add(pLogIn, BorderLayout.CENTER);
		
		btnLogIn = new JButton("로그인");
		btnLogIn.addActionListener(this);
		contentPane.add(btnLogIn, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogIn) {
			actionPerformedBtnLogIn(e);
		}
	}
	protected void actionPerformedBtnLogIn(ActionEvent e) {
		try {
			Manager mng = pLogIn.getManager();
			Manager searchMn = service.managerLogIn(mng);

			if( mng != null) {
				if(mng.getMngAccount().equals(searchMn.getMngAccount()) && mng.getPasswd().equals(searchMn.getPasswd())) {
					service.updateDelayDate(); // 연체일 update
					
					System.out.println("id >> " + mng.getMngAccount());
					System.out.println("pass >> " + mng.getPasswd());
					
					MainPage2 frame = new MainPage2();
					frame.setId(mng.getMngAccount());
					frame.setPass(mng.getPasswd());
				
					frame.setVisible(true);
					dispose();
					
				} else {
					JOptionPane.showMessageDialog(null, "잘못된 비밀번호입니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				if(mng.getMngAccount().contains("") && mng.getPasswd().contains("")) {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "해당 아이디가 없습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				}

			}
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "로그인실패", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
