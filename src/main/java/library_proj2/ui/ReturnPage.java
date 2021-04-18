package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import library_proj2.content.cmb.UserCmb;
import library_proj2.content.detail.BookDetail;
import library_proj2.content.detail.UserDetail;
import library_proj2.content.list.BookTable;
import library_proj2.content.list.RentalTable;
import library_proj2.content.list.UserTable;
import library_proj2.dto.Book;
import library_proj2.dto.User;
import library_proj2.service.MainService;
import library_proj2.service.RentalService;
import library_proj2.service.ReturnService;

@SuppressWarnings("serial")
public class ReturnPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private UserCmb pCmbUser;
	private MainService mainService;
	private ReturnService returnService;
	private RentalService rentalService;
	private UserTable pUserList;
	private UserDetail pUserDetail;
	private BookDetail pBookDetail;
	private JButton btnReturn;
	private BookTable pBookList;
	private RentalTable pRentalList;
	private BookTable pBookListMain;
	private RentalTable pRentalListMain;
	
	public ReturnPage() {
		mainService = new MainService();
		returnService = new ReturnService();
		rentalService = new RentalService();
		initialize();
		
		
		pUserList.setpRentalList(pRentalList);
		
		pRentalList.setpBookList(pBookList);
		pRentalList.setpBookDetail(pBookDetail);
		
	}
	private void initialize() {
		setTitle("반납화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pCenter = new JPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel pSearch1 = new JPanel();
		pCenter.add(pSearch1);
		pSearch1.setLayout(new BorderLayout(0, 0));
		
		pCmbUser = new UserCmb(3);
		pCmbUser.setService(mainService);
		pSearch1.add(pCmbUser, BorderLayout.NORTH);
		
		pUserList = new UserTable(3);
		pUserList.setService(mainService);
		pUserList.loadData();
		
		pSearch1.add(pUserList, BorderLayout.CENTER);
		pUserList.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		
		pUserDetail = pUserList.getpUserDetail();
		pUserDetail.setBorder(new EmptyBorder(5, 0, 5, 0));
		pCenter.add(pUserDetail);
		
		JPanel pSearch2 = new JPanel();
		pCenter.add(pSearch2);
		pSearch2.setLayout(new BorderLayout(0, 0));
		
		JPanel pText = new JPanel();
		FlowLayout fl_pText = (FlowLayout) pText.getLayout();
		fl_pText.setAlignment(FlowLayout.LEFT);
		pSearch2.add(pText, BorderLayout.NORTH);
		
		JLabel lblText = new JLabel("대여중인도서목록");
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		pText.add(lblText);
		
		pRentalList = new RentalTable(3);
		pRentalList.setMainService(mainService);
		pRentalList.setReturnService(returnService);
		pRentalList.loadData();
		pSearch2.add(pRentalList, BorderLayout.CENTER);
		
		pBookDetail = new BookDetail();
		pCenter.add(pBookDetail);
		
		JPanel pBtn = new JPanel();
		FlowLayout fl_pBtn = (FlowLayout) pBtn.getLayout();
		fl_pBtn.setAlignment(FlowLayout.TRAILING);
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
		pBtn.add(btnReturn);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReturn) {
			actionPerformedBtnReturn(e);
		}
	}

	protected void actionPerformedBtnReturn(ActionEvent e) {
		User user = pUserDetail.getUser();
		Book book = pBookDetail.getBook();

		System.out.println(user);
		System.out.println(book);

		try {
			if (user != null && book != null) {
				returnService.transReturn(book);
			} else {
				if (user == null) {
					JOptionPane.showMessageDialog(null, "회원을 선택해주세요.");
				} else if (book != null) {
					JOptionPane.showMessageDialog(null, "도서를 선택해주세요.");
				}
			}
			JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.");
			
			pBookListMain.setMainService(mainService);
			pBookListMain.setRentalService(rentalService);
			pBookListMain.loadData();
			pRentalListMain.loadData();
			pBookList.loadData();
			pUserList.loadData();
		} catch (Exception e1) {
			e1.getStackTrace();
		} finally {
			pUserDetail.clearTf();
			pBookDetail.clearTf();

		}

	}

	public BookTable getpBookList() {
		return pBookList;
	}
	public void setpBookList(BookTable pBookList) {
		this.pBookList = pBookList;
	}
	public UserTable getpUserList() {
		return pUserList;
	}
	public void setpUserList(UserTable pUserList) {
		this.pUserList = pUserList;
	}

	public UserDetail getpUserDetail() {
		return pUserDetail;
	}
	public void setpUserDetail(UserDetail pUserDetail) {
		this.pUserDetail = pUserDetail;
	}
	
	public BookDetail getpBookDetail() {
		return pBookDetail;
	}
	public void setpBookDetail(BookDetail pBookDetail) {
		this.pBookDetail = pBookDetail;
	}
	public RentalTable getpRentalList() {
		return pRentalList;
	}
	public void setpRentalList(RentalTable pRentalList) {
		this.pRentalList = pRentalList;
	}
	public BookTable getpBookListMain() {
		return pBookListMain;
	}
	public void setpBookListMain(BookTable pBookListMain) {
		this.pBookListMain = pBookListMain;
	}
	public RentalTable getpRentalListMain() {
		return pRentalListMain;
	}
	public void setpRentalListMain(RentalTable pRentalListMain) {
		this.pRentalListMain = pRentalListMain;
	}

	
}











