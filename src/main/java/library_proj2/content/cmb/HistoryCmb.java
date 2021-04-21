package library_proj2.content.cmb;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import library_proj2.content.list.UserHistoryTable;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistoryCmb extends JPanel implements ActionListener {
	private JTextField tfNo;
	private JButton btnSearch;
	private int delimiter;
	private JLabel lblText;
	private UserHistoryTable pHistoryList;

	public JTextField getTfNo() {
		return tfNo;
	}
	public void setTfNo(JTextField tfNo) {
		this.tfNo = tfNo;
	}
	
	public HistoryCmb() {
	}
	
	public int getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(int delimiter) {
		this.delimiter = delimiter;
	}
	public HistoryCmb(int delimiter) {
		this.delimiter = delimiter;
		lblText = new JLabel();
		initialize();
	}

	private void initialize() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		if(delimiter == 4) {
			lblText.setText("회원번호");
		} else if(delimiter == 5) {
			lblText.setText("도서번호");
		}
		add(lblText);
		
		tfNo = new JTextField();
		add(tfNo);
		tfNo.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		add(btnSearch);
		
		JPanel panel = new JPanel();
		add(panel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
	}
	protected void actionPerformedBtnSearch(ActionEvent e) {
		int userNo = Integer.parseInt(tfNo.getText());
		
	}
}
