package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import library_proj2.service.ReturnService;

public class MainPage2 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private MainService mainService;
	private RentalService rentalService;
	private ReturnService returnService;
	private UserCmb pUserCmb;
	private UserTable pUserList;
	private BookCmb pBookCmb;
	private BookTable pBookList;
	private RentalTable pRentalList;
	private JButton btnRental;
	private JButton btnReturn;
	private JButton btnUser;
	private JButton btnBook;

	public MainPage2() {
		mainService = new MainService();
		rentalService = new RentalService();
		returnService = new ReturnService();
		
		initialize();
		
		pUserCmb.setpUserList(pUserList);
		pBookCmb.setpBookList(pBookList);
		
		pUserList.setpBookListMain(pBookList);
		pUserList.setpRentalList(pRentalList);
		
		pRentalList.setpBookList(pBookList);
		
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
		
		pRentalList = new RentalTable(1);
		pRentalList.setMainService(mainService);
		pRentalList.setReturnService(returnService);
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
		
		pBookCmb = new BookCmb(1);
		pBookCmb.setMainService(mainService);
		pBookCmb.setRentalService(rentalService);
		pSecond.add(pBookCmb, BorderLayout.NORTH);
		
		pBookList = new BookTable(1);
		pBookList.setMainService(mainService);
		pBookList.setRentalService(rentalService);
		pBookList.loadData();
		pSecond.add(pBookList, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		FlowLayout fl_pBtn = (FlowLayout) pBtn.getLayout();
		fl_pBtn.setAlignment(FlowLayout.LEFT);
		contentPane.add(pBtn, BorderLayout.NORTH);
		
		btnRental = new JButton("대여하기");
		btnRental.addActionListener(this);
		pBtn.add(btnRental);
		
		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
		pBtn.add(btnReturn);
		
		btnUser = new JButton("회원관리");
		btnUser.addActionListener(this);
		pBtn.add(btnUser);
		
		btnBook = new JButton("도서관리");
		btnBook.addActionListener(this);
		pBtn.add(btnBook);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRental) {
			actionPerformedBtnRental(e);
		}
		if (e.getSource() == btnReturn) {
			actionPerformedBtnReturn(e);
		}
		if (e.getSource() == btnUser) {
			actionPerformedBtnUser(e);
		}
		if (e.getSource() == btnBook) {
			actionPerformedBtnBook(e);
		}
	}
	
	protected void actionPerformedBtnRental(ActionEvent e) {
		RentalPage frame = new RentalPage();
		frame.setVisible(true);
	}
	
	protected void actionPerformedBtnReturn(ActionEvent e) {
		ReturnPage frame = new ReturnPage();
		frame.setVisible(true);
	}
	
	protected void actionPerformedBtnUser(ActionEvent e) {
		/*작성필요*/
	}
	
	protected void actionPerformedBtnBook(ActionEvent e) {
		/*작성필요*/
	}

}
