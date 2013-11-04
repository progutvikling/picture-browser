package bll.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dal.admin.IKeywordsStore;
import dal.admin.IStoreBlocking;
import dal.admin.StoreBlocking;
import dal.admin.StoreFactory;


public class BlockingPictures extends JPanel implements ActionListener {
	
	public IStoreBlocking storeblocking = StoreFactory.getStoreBlocking();
	/**
	 * 
	 */
	private static final long serialVersionUID = 6747143681976968595L;
	private JLabel label1 = new JLabel();

	public BlockingPictures() {
		
		setName("Block pictures");
		
		
		
		//Simple view of images
		JPanel picPanel = new JPanel();
		picPanel.setLayout(new BoxLayout(picPanel, BoxLayout.Y_AXIS));
		
		//http://stackoverflow.com/questions/14558959/adding-images-to-cells-in-a-gridlayout
		label1 = new JLabel("test1");
	
		picPanel.add(label1);
		
		//Bare for å aktivere mysql script
		JPanel buttonPanel = new JPanel();
		JButton block = new JButton("Block");
		block.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				storeblocking.setBlock(204);
				label1.setText("I just blocked a picture");
			}
		});
		buttonPanel.add(block);
		JButton rblock = new JButton("Remove block");
		rblock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				storeblocking.removeBlock(203);
				label1.setText("I just removed the block on a picture");
			}
		});
		buttonPanel.add(rblock);

		
	
		picPanel.add(Box.createRigidArea(new Dimension(0,5)));
		picPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		this.setLayout(new BorderLayout());
		this.add( picPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {


	}
}
