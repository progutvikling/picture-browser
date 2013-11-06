package bll.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.awt.image.BufferedImageDevice;
import dal.admin.IImageStore;
import dal.admin.Image;
import dal.admin.StoreFactory;


public class BlockingPictures extends JPanel implements ActionListener {
	
	public IImageStore storeblocking = StoreFactory.getImageStore();
	/**
	 * 
	 */
	private static final long serialVersionUID = 6747143681976968595L;
	private JLabel label[];
	private BufferedImage buffImage;
	private ArrayList<Image> img;
	private ImageIcon imgIcon;
	private URL url;

	public BlockingPictures() {
		this.setName("Block");
		JPanel picPanel = new JPanel();
		picPanel.setLayout(new GridLayout(2, 2));
	
		//picPanel.setLayout(new BoxLayout(picPanel, BoxLayout.Y_AXIS));
	
		//Bare for å aktivere mysql script
		JPanel buttonPanel = new JPanel();
		JButton block = new JButton("Block");
		block.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	storeblocking.block(1);
				//label1.setText("I just blocked a picture");
			}
		});
		buttonPanel.add(block);
		JButton rblock = new JButton("Remove block");
		rblock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	storeblocking.unBlock(0);
				//label1.setText("I just removed the block on a picture");
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
