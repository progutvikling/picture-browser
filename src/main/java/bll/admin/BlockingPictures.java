package bll.admin;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BlockingPictures extends JPanel implements ActionListener {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6747143681976968595L;

	public BlockingPictures() {
		setName("Block pictures");
		
		
		//Simple view of images
		JPanel picPanel = new JPanel();
		picPanel.setLayout(new BoxLayout(picPanel, BoxLayout.Y_AXIS));
		
		//http://stackoverflow.com/questions/14558959/adding-images-to-cells-in-a-gridlayout
		JLabel label1 = new JLabel("test1");
	
		picPanel.add(label1);
	
		picPanel.add(Box.createRigidArea(new Dimension(0,5)));
		picPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		this.setLayout(new BorderLayout());
		this.add( picPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
