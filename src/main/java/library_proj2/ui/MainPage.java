package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import library_proj2.content.cmb.BookCmb;
import library_proj2.content.cmb.UserCmb;
import library_proj2.content.list.BookTable;
import library_proj2.content.list.RentalTable;
import library_proj2.content.list.UserTable;
import library_proj2.service.MainService;
import library_proj2.service.RentalService;

@SuppressWarnings("serial")
public class MainPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnRent;
	private JButton btnReturn;
	private UserTable pUserList;
	private BookTable pBookList;
	private RentalTable pRentalList;
	private JButton btnUserMng;
	private JButton btnBookMng;
	private MainService mainService;
	private RentalService rentalService;
	
	public MainPage() {
		mainService = new MainService();
		rentalService = new RentalService();
		
		initialize();
	}
	
	private void initialize() {
		setTitle("메인화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pBtn = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pBtn.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(pBtn, BorderLayout.NORTH);
		
		btnRent = new JButton("대출하기");
		btnRent.addActionListener(this);
		pBtn.add(btnRent);
		
		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
		pBtn.add(btnReturn);
		
		btnUserMng = new JButton("회원관리");
		btnUserMng.addActionListener(this);
		pBtn.add(btnUserMng);
		
		btnBookMng = new JButton("도서관리");
		btnBookMng.addActionListener(this);
		pBtn.add(btnBookMng);
		
		JPanel pSearch = new JPanel();
		contentPane.add(pSearch, BorderLayout.CENTER);
		pSearch.setLayout(new GridLayout(0, 2, 5, 0));
		
		JPanel pUser = new JPanel();
		pSearch.add(pUser);
		pUser.setLayout(new BorderLayout(0, 0));
		
		UserCmb pCmbUser = new UserCmb(1);
		pCmbUser.setService(mainService);
		pUser.add(pCmbUser, BorderLayout.NORTH);
		
		pUserList = pCmbUser.getpUserList();
		pUserList.setService(mainService);
		pUserList.loadData();
		pUser.add(pUserList, BorderLayout.CENTER);
		
		JPanel pBook = new JPanel();
		pSearch.add(pBook);
		pBook.setLayout(new BorderLayout(0, 0));
		
		BookCmb pCmbBook = new BookCmb(1);
		pCmbBook.setMainService(mainService);
		pCmbBook.setRentalService(rentalService);
		pBook.add(pCmbBook, BorderLayout.NORTH);
		
		pBookList = pCmbBook.getpBookList();
		pBookList.setMainService(mainService);
		pBookList.setRentalService(rentalService);
		pBookList.loadData();
		pBook.add(pBookList, BorderLayout.CENTER);

		pRentalList = pUserList.getpRentalList();
		pRentalList.setMainService(mainService);
//		pRentalList.setRentalService(rentalService);
		contentPane.add(pRentalList, BorderLayout.SOUTH);
		
		pUserList.setpBookList(pBookList);	
		pRentalList.setpBookList(pBookList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBookMng) {
			actionPerformedBtnBookMng(e);
		}
		if (e.getSource() == btnUserMng) {
			actionPerformedBtnUserMng(e);
		}
		if (e.getSource() == btnReturn) {
			actionPerformedBtnReturn(e);
		}
		if (e.getSource() == btnRent) {
			actionPerformedBtnRent(e);
		}
	}
	
	protected void actionPerformedBtnRent(ActionEvent e) {
		RentalPage frame = new RentalPage();
		frame.setVisible(true);
	}
	
	protected void actionPerformedBtnReturn(ActionEvent e) {
		ReturnPage frame = new ReturnPage();
		frame.setpBookList(pBookList);
		frame.setVisible(true);
	}
	
	protected void actionPerformedBtnUserMng(ActionEvent e) {
		UserMngPage frame = new UserMngPage();
		frame.setVisible(true);
	}
	
	protected void actionPerformedBtnBookMng(ActionEvent e) {
		/*작성필요*/
	}
}
