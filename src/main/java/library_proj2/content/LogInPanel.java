package library_proj2.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library_proj2.dto.Manager;
import library_proj2.exception.InvalidCheckException;
import library_proj2.service.LogInService;

@SuppressWarnings("serial")
public class LogInPanel extends JPanel {
	private JTextField tfId;
	private JPasswordField pfPasswd;
	private LogInService service;
	private String id;
	private String passwd;
	
	public LogInPanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pLogIn = new JPanel();
		add(pLogIn);
		pLogIn.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblId = new JLabel("ID");
		pLogIn.add(lblId);
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		
		tfId = new JTextField();
		pLogIn.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblPasswd = new JLabel("비밀번호");
		pLogIn.add(lblPasswd);
		lblPasswd.setHorizontalAlignment(SwingConstants.TRAILING);
		
		pfPasswd = new JPasswordField();
		pLogIn.add(pfPasswd);
	}
	
	public void setService(LogInService service) {
		this.service = service;
	}
	public Manager getManager() {
		
		validCheck();
		
		return new Manager(id, passwd); 
	}
	
	private void validCheck() {
		id = tfId.getText().trim();
		passwd = new String(pfPasswd.getPassword());
		
		if(tfId.getText().trim().contentEquals("") || passwd.equals("")) {
			throw new InvalidCheckException();
		}
		
	}
	
}
