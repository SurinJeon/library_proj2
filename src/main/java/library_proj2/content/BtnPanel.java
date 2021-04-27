package library_proj2.content;

import javax.swing.JPanel;
import javax.swing.JButton;

public class BtnPanel extends JPanel {

	public BtnPanel() {

		initialize();
	}
	private void initialize() {
		
		JButton btnCheck = new JButton("추가");
		add(btnCheck);
		
		JButton btnClear = new JButton("취소");
		add(btnClear);
	}

}
