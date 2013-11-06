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

	public BlockingPictures() throws IOException {
		
		setName("Block pictures");
		try {
			url = new URL("http://instagram.com/p/gVoiYdnQZf/?fb_action_ids=10151684013856400&fb_action_types=og.likes&fb_source=other_multiline&action_object_map=%7B%2210151684013856400%22%3A465874080196671%7D&action_type_map=%7B%2210151684013856400%22%3A%22og.likes%22%7D&action_ref_map=%5B%5D");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		buffImage = ImageIO.read(url);

		JPanel picPanel = new JPanel();
		picPanel.setLayout(new GridLayout(2, 2));
		
		for(int i = 0; i<2; i++){
			//			label[i] = new JLabel(imgIcon);
			//label[i] = new JLabel(imgIcon);
			picPanel);
		}
		//picPanel.setLayout(new BoxLayout(picPanel, BoxLayout.Y_AXIS));
		
		/**http://stackoverflow.com/questions/14558959/adding-images-to-cells-in-a-gridlayout
		BufferedImage resizedimage = resize(image,width,height); //error! type conversion
		  */
		 
		
		//Bare for å aktivere mysql script
		JPanel buttonPanel = new JPanel();
		JButton block = new JButton("Block");
		block.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				storeblocking.block(1);
				//label1.setText("I just blocked a picture");
			}
		});
		buttonPanel.add(block);
		JButton rblock = new JButton("Remove block");
		rblock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				storeblocking.unBlock(0);
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
