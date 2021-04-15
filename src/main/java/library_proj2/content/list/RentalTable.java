package library_proj2.content.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import library_proj2.content.detail.BookDetail;
import library_proj2.dto.Book;
import library_proj2.dto.RentalStatus;
import library_proj2.dto.User;
import library_proj2.service.MainService;
import library_proj2.service.RentalService;
import library_proj2.ui.ReturnPage;

@SuppressWarnings("serial")
public class RentalTable extends AbstractCustomTable<RentalStatus> implements MouseListener{
	
	private MainService mainService;
	private RentalService rentalService;
	private BookTable pBookList;
	private BookDetail pBookDetail;
	private int delimiter;
	
	public RentalTable() {
	}

	public RentalTable(int delimiter) {
		this.delimiter = delimiter;
		pBookDetail = new BookDetail();
		table.addMouseListener(this);
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	public void setRentalService(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3);
		setTableCellWidth(100, 300, 250, 250);
	}
	
	@Override
	protected void initList() {
		list = new ArrayList<RentalStatus>();
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"도서번호", "도서제목", "도서연체일", "도서대여일"};
	}
	
	@Override
	protected Object[] toArray(RentalStatus r) {
		return new Object[] {
				r.getBookNo().getBookNo(),
				r.getBookNo().getBookTitle(),
				r.getDelayDate() <= 0 ? "" : r.getDelayDate(),
				r.getRentalDate()};
	}

	public void setList(List<RentalStatus> list) {
		this.list = list;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(delimiter == 1 && e.getClickCount() == 2) {
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			
			String bookNo = (String)table.getValueAt(idx, 0);
			
			List<RentalStatus> rentalList = mainService.mainToReturn(new Book(bookNo), null);
			RentalStatus rentalStatus = rentalList.get(0); // 객체 찾음
			
			int userNo = rentalStatus.getUserNo().getUserNo(); // 그 객체의 user
			
			// 각각 Detail에 넣을 객체 찾아냄
			User userDetail = mainService.searchByUserNo(new User(userNo)).get(0); 
			Book bookDetail = rentalService.searchByBookNo(new Book(bookNo)).get(0);
			
			ReturnPage frame = new ReturnPage();
			
			List<User> searchUser = frame.getpUserList().getList()
					.stream().filter(user -> user.getUserNo()==userNo)
					.collect(Collectors.toList());
			User user = searchUser.get(0);
			int idxRent = frame.getpUserList().getList().indexOf(user);
			frame.getpUserList().table.setRowSelectionInterval(idxRent, idxRent);
			frame.getpUserDetail().setUser(userDetail);
			
			List<RentalStatus> list = mainService.mainToReturn(null, new User(userNo)); // userno로 rentalstatus검색해야됨...
			
			frame.getpRentalList().setList(list);
			frame.getpRentalList().setList();
			
			List<RentalStatus> searchRentalStatus = list.stream()
					.filter(r -> r.getBookNo().getBookNo().equals(bookNo))
					.collect(Collectors.toList());
			
			RentalStatus rental = searchRentalStatus.get(0);
			
			int idxRs = frame.getpBookList().getList().indexOf(rental);
			
			frame.getpBookList().table.setRowSelectionInterval(idxRs, idxRs);
			
			frame.getpBookDetail().setBook(bookDetail);
			
			frame.setpBookList(pBookList);
			frame.setVisible(true);
			
			//회원/도서목록 select되게 해+야함
		} else if(delimiter == 2 && e.getClickCount() == 1){ // Return frame에서 쓰임
			
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			String bookNo = (String)table.getValueAt(idx, 0);
			
			Book searchBook = rentalService.searchByBookNo(new Book(bookNo)).get(0);
			
			pBookDetail.setBook(searchBook);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	public BookTable getpBookList() {
		return pBookList;
	}

	public void setpBookList(BookTable pBookList) {
		this.pBookList = pBookList;
	}
	
	public BookDetail getpBookDetail() {
		return pBookDetail;
	}

	public void setpBookDetail(BookDetail pBookDetail) {
		this.pBookDetail = pBookDetail;
	}

	public int getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(int delimiter) {
		this.delimiter = delimiter;
	}
}
