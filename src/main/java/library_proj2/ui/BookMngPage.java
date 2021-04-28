package library_proj2.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library_proj2.content.BookPanel;
import library_proj2.content.cmb.HistoryCmb;
import library_proj2.content.list.BookHistoryTable;
import library_proj2.content.list.BookMngTable;
import library_proj2.content.list.BookTable;
import library_proj2.dto.Book;
import library_proj2.dto.History;
import library_proj2.service.BookService;
import library_proj2.service.HistoryService;

public class BookMngPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pContain;
	private JButton btnCheck;
	private JButton btnClear;
	private BookMngTable pList;
	private BookService bookService;
	private HistoryService hisService;
	private BookTable pBookListMain;
	private String id;
	private String pass;
	private BookPanel pDetail;
	private JPanel pSwitch;
	private JPanel pBtn;
	private HistoryCmb pCmb;
	private BookHistoryTable pHistoryList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	public void setpBookListMain(BookTable pBookListMain) {
		this.pBookListMain = pBookListMain;
	}
	public BookMngPage() {
		bookService = new BookService();
		hisService = new HistoryService();
		initialize();
	}
	private void initialize() {
		setTitle("도서관리화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pContain = new JPanel();
		contentPane.add(pContain, BorderLayout.CENTER);
		pContain.setLayout(new GridLayout(2, 1, 0, 0));
		
		pList = new BookMngTable();
		pList.setService(bookService);
		pList.loadData();
		pList.setPopupMenu(createPopMenu());
		pContain.add(pList);
		
		pSwitch = new JPanel();
		pContain.add(pSwitch);
		pSwitch.setLayout(new BorderLayout(0, 0));
		
		pBtn = new JPanel();
		pSwitch.add(pBtn, BorderLayout.SOUTH);
		
		btnCheck = new JButton("추가");
		btnCheck.addActionListener(this);
		pBtn.add(btnCheck);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
		pBtn.setVisible(false);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		if (e.getSource() == btnCheck) {
			actionPerformedBtnCheck(e);
		} else {
			String cmd = e.getActionCommand();

			switch (cmd) {
			case "추가":
				actionPerformedInsertMenu(e);
				break;
			case "수정":
				btnCheck.setText("수정");
				actionPerformedUpdateMenu(e);
				break;
			case "삭제":
				actionPerformedDeleteMenu(e);
				break;
			case "대여내역":
				actionPerformedHistoryMenu(e);
				break;
			}
			
		}
	}
	private void actionPerformedInsertMenu(ActionEvent e) {
		removeComp();
		if(btnCheck.getText().equals("수정")) {
			btnCheck.setText("추가");
		}
		
		pDetail = new BookPanel();
		pDetail.setService(bookService);
		pSwitch.add(pDetail, BorderLayout.CENTER);
		pBtn.setVisible(true);
		this.revalidate();
	}
	

	private void actionPerformedUpdateMenu(ActionEvent e) {
		removeComp();
		if (btnCheck.getText().equals("추가")) {
			btnCheck.setText("수정");
		}
		
		try {
			pDetail = new BookPanel();
			pDetail.setService(bookService);
			
			Book book = pList.getItem();
			
			pDetail.setBook(book);
			
			pSwitch.add(pDetail, BorderLayout.CENTER);
			pBtn.setVisible(true);
			this.revalidate();
		} catch (IndexOutOfBoundsException e1) {
			removeComp();
			JOptionPane.showMessageDialog(null, "도서를 선택하십시오.");
		}
		
	}
	
	private void actionPerformedDeleteMenu(ActionEvent e) {
		try {
			Book book = pList.getItem();
			
			int reply = JOptionPane.showConfirmDialog(null,
					book.getBookTitle() + "(" + book.getBookNo() + ")" + "를 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
			
			if (reply == JOptionPane.YES_OPTION) {
				bookService.deleteBook(book);
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
				pBookListMain.loadData();
				pList.loadData();
			} else {
				JOptionPane.showMessageDialog(null, "삭제 실패");
				pBookListMain.loadData();
				pList.loadData();
			}
		} catch (IndexOutOfBoundsException e1) {
			removeComp();
			JOptionPane.showMessageDialog(null, "도서를 선택하십시오.");
		}
		
	}
	
	private void actionPerformedHistoryMenu(ActionEvent e) {
		try {
			removeComp();
			pBtn.setVisible(false);
			pCmb = new HistoryCmb(5);
			
			Book book = pList.getItem();
			pCmb.getTfNo().setText(book.getBookNo());
			
			pHistoryList = new BookHistoryTable();
			
			try {
				pHistoryList.setService(hisService);
				if(hisService.bookHistory(book.getBookNo()) != null) {
					pHistoryList.setList(book.getBookNo());
				} else {
					pHistoryList.initList();
				}
				pHistoryList.setList();
			} catch (NullPointerException e1) {
				pHistoryList.loadData();
			}
			pSwitch.add(pHistoryList, BorderLayout.CENTER);
			pSwitch.add(pCmb, BorderLayout.NORTH);

			this.revalidate();
		} catch (IndexOutOfBoundsException e1) {
			e1.printStackTrace();
			removeComp();
			JOptionPane.showMessageDialog(null, "도서를 선택하십시오.");
		}
		
	}
	
	protected void actionPerformedBtnCheck(ActionEvent e) {
		String pass = new String(pDetail.getPfPass().getPassword());
		
		if(btnCheck.getText().equals("추가")) {
			if(pass.equals(pass)) {
				Book book = pDetail.getBook();
				bookService.insertBook(book);
				
				JOptionPane.showMessageDialog(null, "추가되었습니다.");
				pDetail.clearTf();
				pList.loadData();
				pBookListMain.loadData();
				
			} else {
				JOptionPane.showMessageDialog(null, "관리자 비밀번호가 아닙니다.");
				pDetail.clearTf();
			}
		} else if (btnCheck.getText().equals("수정")) {
			if (pass.equals(pass)) {
				Book book = pDetail.getBook();
				bookService.updateBook(book);
				
				JOptionPane.showMessageDialog(null, "수정되었습니다.");
				pDetail.clearTf();
				pList.loadData();
				pBookListMain.loadData();
				btnCheck.setText("추가");
			} else {
				JOptionPane.showMessageDialog(null, "관리자 비밀번호가 아닙니다.");
				pDetail.clearTf();
			}
		}
	}
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pDetail.clearTf();
	}
	
	public JPopupMenu createPopMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem insert = new JMenuItem("추가");
		insert.setHorizontalAlignment(SwingConstants.CENTER);
		insert.addActionListener(this);
		popMenu.add(insert);
		
		JSeparator separator1 = new JSeparator();
		popMenu.add(separator1);
		
		JMenuItem update = new JMenuItem("수정");
		update.setHorizontalAlignment(SwingConstants.CENTER);
		update.addActionListener(this);
		popMenu.add(update);
		
		JSeparator separator2 = new JSeparator();
		popMenu.add(separator2);
		
		JMenuItem delete = new JMenuItem("삭제");
		delete.setHorizontalAlignment(SwingConstants.CENTER);
		delete.addActionListener(this);
		popMenu.add(delete);
		
		JSeparator separator3 = new JSeparator();
		popMenu.add(separator3);
		
		JMenuItem history = new JMenuItem("대여내역");
		history.setHorizontalAlignment(SwingConstants.CENTER);
		history.addActionListener(this);
		popMenu.add(history);
		
		return popMenu;
	}
	
	private void removeComp() {
		if (pDetail != null) {
			pSwitch.remove(pDetail);
		}
		
		if (pCmb != null) {
			pSwitch.remove(pCmb);
		}
		
		if(pHistoryList != null) {
			pSwitch.remove(pHistoryList);
		}
		
	}
	
}
