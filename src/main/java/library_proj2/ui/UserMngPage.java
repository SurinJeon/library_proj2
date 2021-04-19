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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMngPage frame = new UserMngPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserMngPage() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pList = new JPanel();
		tabbedPane.addTab("New tab", null, pList, null);
		
		JPanel pInsert = new JPanel();
		tabbedPane.addTab("New tab", null, pInsert, null);
		pInsert.setLayout(new BorderLayout(0, 0));
		
		UserPanel pDetail = new UserPanel();
		pInsert.add(pDetail, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		pInsert.add(pBtn, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		pBtn.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		pBtn.add(btnNewButton_1);
		
		JPanel pHistory = new JPanel();
		tabbedPane.addTab("New tab", null, pHistory, null);
	}

}
