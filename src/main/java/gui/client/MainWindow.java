package gui.client;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = -4902720969305740099L;

	public MainWindow(JPanel panel) {
		this.setTitle("Picture Browser");
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
	}
	
}
