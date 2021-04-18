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
import library_proj2.service.ReturnService;
import library_proj2.ui.ReturnPage;

@SuppressWarnings("serial")
public class RentalTable extends AbstractCustomTable<RentalStatus> implements MouseListener{
	
	private MainService mainService;
	private ReturnService returnService;
	private BookTable pBookList;
	private BookDetail pBookDetail;
	private int delimiter;
	
	public RentalTable() {
	}

	public RentalTable(int delimiter) {
		this.delimiter = delimiter;
		table.addMouseListener(this);
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	public void setRentalService(ReturnService returnService) {
		this.returnService = returnService;
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
			Book bookDetail = returnService.bookDetail(new Book(bookNo));
			
			ReturnPage frame = new ReturnPage();
			
			List<User> searchUser = frame.getpUserList().getList()
					.stream().filter(user -> user.getUserNo()==userNo)
					.collect(Collectors.toList());
			User user = searchUser.get(0);
			int idxRent = frame.getpUserList().getList().indexOf(user);
			frame.getpUserList().table.setRowSelectionInterval(idxRent, idxRent);
			frame.getpUserDetail().setUser(userDetail);
			
			List<RentalStatus> list = mainService.mainToReturn(null, new User(userNo));
			
			frame.getpRentalList().setList(list);
			frame.getpRentalList().setList();
			
			frame.getpRentalList().setDelimiter(3);
			frame.getpUserList().setDelimiter(3);
			RentalStatus rental = mainService.mainToReturn(null, new User(userNo)).get(0);
			List<RentalStatus> searchRentalList= frame.getpRentalList().getList();
			
			int idxRs = 0;

			for (int i = 0; i < searchRentalList.size(); i++) {
				if (searchRentalList.get(i).getBookNo().getBookNo().equals(bookNo)) {
					idxRs = i;
					System.out.println(searchRentalList.get(i).getBookNo().getBookNo());
					break;
				} else {
					continue;
				}
			}
			frame.getpBookDetail().setBook(bookDetail);
			
			frame.getpRentalList().table.setRowSelectionInterval(idxRs, idxRs);
			
			frame.setpBookListMain(pBookList);
			frame.setpRentalListMain(this);
			frame.setVisible(true);
			
			this.delimiter = 1;
		} else if(delimiter == 3 && e.getClickCount() == 1){ // Return frame에서 쓰임
			
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			String bookNo = (String)table.getValueAt(idx, 0);
			
			Book searchBook = returnService.bookDetail(new Book(bookNo));
			
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

	public ReturnService getReturnService() {
		return returnService;
	}

	public void setReturnService(ReturnService returnService) {
		this.returnService = returnService;
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
