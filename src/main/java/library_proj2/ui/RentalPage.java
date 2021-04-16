package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import library_proj2.content.cmb.BookCmb;
import library_proj2.content.cmb.UserCmb;
import library_proj2.content.detail.BookDetail;
import library_proj2.content.detail.UserDetail;
import library_proj2.content.list.BookTable;
import library_proj2.content.list.UserTable;
import library_proj2.dto.Book;
import library_proj2.dto.User;
import library_proj2.exception.RentalAndReturnException;
import library_proj2.service.MainService;
import library_proj2.service.RentalService;

@SuppressWarnings("serial")
public class RentalPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private RentalService rentalService;
	private MainService mainService;
	private UserTable pUserList;
	private UserDetail pUserDetail;
	private BookDetail pBookDetail;
	private JButton btnRent;
	private UserCmb pUserCmb;
	private BookCmb pBookCmb;
	private BookTable pBookList;
	private BookTable pBookListMain;
	
	public RentalPage() {
		rentalService = new RentalService();
		mainService = new MainService();
		initialize();
		pUserCmb.setpUserList(pUserList);
		pBookCmb.setpBookList(pBookList);
		
		pUserList.setpUserDetail(pUserDetail);
		pBookList.setpBookDetail(pBookDetail);
	}
	private void initialize() {
		setTitle("대출화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pCenter = new JPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel pSearch1 = new JPanel();
		pCenter.add(pSearch1);
		pSearch1.setLayout(new BorderLayout(0, 0));
		
		pUserCmb = new UserCmb(2);
		pUserCmb.setService(mainService);
		pSearch1.add(pUserCmb, BorderLayout.NORTH);
		
		pUserList = new UserTable(2);
		pUserList.setService(mainService);
		pUserList.loadData();
		pSearch1.add(pUserList, BorderLayout.CENTER);
		pUserList.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		
		pUserDetail = new UserDetail();
		pUserDetail.setBorder(new EmptyBorder(5, 0, 5, 0));
		pCenter.add(pUserDetail);
		
		JPanel pSearch2 = new JPanel();
		pCenter.add(pSearch2);
		pSearch2.setLayout(new BorderLayout(0, 0));
		
		pBookCmb = new BookCmb(2);
		pBookCmb.setMainService(mainService);
		pBookCmb.setRentalService(rentalService);
		pSearch2.add(pBookCmb, BorderLayout.NORTH);
		
		pBookList = new BookTable(2);
		pBookList.setMainService(mainService);
		pBookList.setRentalService(rentalService);
		pBookList.loadData();
		pSearch2.add(pBookList, BorderLayout.CENTER);
		
		pBookDetail = new BookDetail();
		pBookDetail.setBorder(new EmptyBorder(5, 0, 5, 0));
		pCenter.add(pBookDetail);
		
		JPanel pBtn = new JPanel();
		FlowLayout fl_pBtn = (FlowLayout) pBtn.getLayout();
		fl_pBtn.setAlignment(FlowLayout.TRAILING);
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		btnRent = new JButton("대여하기");
		btnRent.addActionListener(this);
		showBtnRent();
		pBtn.add(btnRent);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
		
		
	}

	public void showBtnRent() {
		try {
			User user = pUserDetail.getUser();
			Book book = pBookDetail.getBook();
			if(user != null && book != null) {
				btnRent.setEnabled(true);
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRent) {
			btnRent.setEnabled(true);
			actionPerformedBtnRent(e);
		}
	}
	
	protected void actionPerformedBtnRent(ActionEvent e){
		User user = pUserDetail.getUser();
		Book book = pBookDetail.getBook();

		try {
			if (user != null && book != null) {
				System.out.println(book.getIsRented());
				if (book.getIsRented() == 0) {
					throw new RentalAndReturnException("이미 대출된 도서입니다.");
				}
				rentalService.transRental(user, book);
			} else {
				if (user == null) {
					JOptionPane.showMessageDialog(null, "회원을 선택해주세요.");
				} else if (book == null) {
					JOptionPane.showMessageDialog(null, "도서를 선택해주세요.");
				}
			}

			JOptionPane.showMessageDialog(null, "대여가 완료되었습니다.");
			pBookListMain.setMainService(mainService);
			pBookListMain.setRentalService(rentalService);
			pBookListMain.loadData();
			pBookList.loadData();
		} catch (RentalAndReturnException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
		} finally {
			pUserDetail.clearTf();
			pBookDetail.clearTf();
		}
	}

	public BookDetail getpBookDetail() {
		return pBookDetail;
	}
	public void setpBookDetail(BookDetail pBookDetail) {
		this.pBookDetail = pBookDetail;
	}
	public UserTable getpUserList() {
		return pUserList;
	}
	public void setpUserList(UserTable pUserList) {
		this.pUserList = pUserList;
	}
	public BookTable getpBookList() {
		return pBookList;
	}
	public void setpBookList(BookTable pBookList) {
		this.pBookList = pBookList;
	}
	public UserDetail getpUserDetail() {
		return pUserDetail;
	}
	public void setpUserDetail(UserDetail pUserDetail) {
		this.pUserDetail = pUserDetail;
	}
	public BookTable getpBookListMain() {
		return pBookListMain;
	}
	public void setpBookListMain(BookTable pBookListMain) {
		this.pBookListMain = pBookListMain;
	}
	
}
