package library_proj2.content;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library_proj2.service.MainService;

public class SummaryPanel extends JPanel {
	private MainService service;
	private JTextField tfDate;
	private JTextField tfUser;
	private JTextField tfBook;
	private JTextField tfDelay;

	
	public void setService(MainService service) {
		this.service = service;
	}
	public SummaryPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDate = new JLabel("오늘 날짜: ");
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblDate);
		
		tfDate = new JTextField();
		add(tfDate);
		tfDate.setColumns(10);
		
		JLabel lblUser = new JLabel("전체 회원 수: ");
		lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblUser);
		
		tfUser = new JTextField();
		tfUser.setColumns(10);
		add(tfUser);
		
		JLabel lblBook = new JLabel("대출 가능 도서 수: ");
		lblBook.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblBook);
		
		tfBook = new JTextField();
		tfBook.setColumns(10);
		add(tfBook);
		
		JLabel lblDelay = new JLabel("연체 도서 수: ");
		lblDelay.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblDelay);
		
		tfDelay = new JTextField();
		tfDelay.setColumns(10);
		add(tfDelay);
	}
	
	public void setSummary() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		tfDate.setText(format.format(date));
		tfUser.setText(service.userCount() + "명");
		tfBook.setText(service.canRentCount() + "권");
		tfDelay.setText(service.isDelayCount() + "권");
		
		tfDate.setEditable(false);
		tfUser.setEditable(false);
		tfBook.setEditable(false);
		tfDelay.setEditable(false);
	}

}
