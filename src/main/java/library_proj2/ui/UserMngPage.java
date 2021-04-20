package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import library_proj2.content.UserPanel;
import javax.swing.JButton;

public class UserMngPage extends JFrame {

	private JPanel contentPane;

	public UserMngPage() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pList = new JPanel();
		tabbedPane.addTab("회원목록", null, pList, null);
		
		JPanel pInsert = new JPanel();
		tabbedPane.addTab("회원추가", null, pInsert, null);
		pInsert.setLayout(new BorderLayout(0, 0));
		
		UserPanel pDetail = new UserPanel();
		pInsert.add(pDetail, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		pInsert.add(pBtn, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("추가");
		pBtn.add(btnAdd);
		
		JButton btnClear = new JButton("삭제");
		pBtn.add(btnClear);
		
		JPanel pHistory = new JPanel();
		tabbedPane.addTab("대여내역", null, pHistory, null);
	}

}
