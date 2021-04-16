package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library_proj2.content.cmb.BookCmb;
import library_proj2.content.cmb.UserCmb;
import library_proj2.content.list.BookTable;
import library_proj2.content.list.RentalTable;
import library_proj2.content.list.UserTable;
import library_proj2.service.MainService;
import library_proj2.service.RentalService;

public class MainPage2 extends JFrame {

	private JPanel contentPane;
	private MainService mainService;
	private RentalService rentalService;
	private UserCmb pUserCmb;
	private UserTable pUserList;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage2 frame = new MainPage2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainPage2() {
		mainService = new MainService();
		rentalService = new RentalService();
		initialize();
		pUserCmb.setpUserList(pUserList);
	}
	
	private void initialize() {
		setTitle("메인화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pFirst = new JPanel();
		tabbedPane.addTab("회원으로 검색", null, pFirst, null);
		pFirst.setLayout(new BoxLayout(pFirst, BoxLayout.Y_AXIS));
		
		JPanel pUser = new JPanel();
		pFirst.add(pUser);
		pUser.setLayout(new BorderLayout(0, 0));
		
		pUserCmb = new UserCmb(1);
		pUserCmb.setService(mainService);
		pUser.add(pUserCmb, BorderLayout.NORTH);
		
		pUserList = new UserTable(1);
		pUserList.setService(mainService);
		pUserList.loadData();
		pUser.add(pUserList, BorderLayout.CENTER);
		
		JPanel pRental = new JPanel();
		pFirst.add(pRental);
		pRental.setLayout(new BorderLayout(0, 0));
		
		RentalTable pRentalList = new RentalTable();
		pRental.add(pRentalList, BorderLayout.CENTER);
		
		JPanel pText = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pText.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pRental.add(pText, BorderLayout.NORTH);
		
		JLabel label = new JLabel("대여 도서 목록");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		pText.add(label);
		
		JPanel pSecond = new JPanel();
		tabbedPane.addTab("도서로 검색", null, pSecond, null);
		pSecond.setLayout(new BorderLayout(0, 0));
		
		BookCmb pBookCmb = new BookCmb(1);
		pSecond.add(pBookCmb, BorderLayout.NORTH);
		
		BookTable pBookList = new BookTable();
		pSecond.add(pBookList, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_10.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_10, BorderLayout.NORTH);
		
		JButton btnRental = new JButton("대여하기");
		panel_10.add(btnRental);
		
		JButton btnReturn = new JButton("반납하기");
		panel_10.add(btnReturn);
		
		JButton btnUser = new JButton("회원관리");
		panel_10.add(btnUser);
		
		JButton btnBook = new JButton("도서관리");
		panel_10.add(btnBook);
	}

}
