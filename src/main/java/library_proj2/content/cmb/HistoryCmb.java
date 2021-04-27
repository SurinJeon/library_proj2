package library_proj2.content.cmb;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library_proj2.content.list.UserHistoryTable;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistoryCmb extends JPanel{
	private JTextField tfNo;
	private int delimiter;
	private JLabel lblText;

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
			lblText.setText("회원번호: ");
		} else if(delimiter == 5) {
			lblText.setText("도서번호: ");
		}
		lblText.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblText);
		
		tfNo = new JTextField();
		add(tfNo);
		tfNo.setColumns(10);
	
		JPanel panel1 = new JPanel();
		add(panel1);
		
		JPanel panel2 = new JPanel();
		add(panel2);
	}

}
