package bll.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bll.utils.ImageParser;
import sun.awt.image.BufferedImageDevice;
import dal.admin.IImageStore;
import dal.admin.Image;
import dal.admin.StoreFactory;
import dal.client.Fetcher;

public class BlockingPictures extends JPanel implements MouseListener {

	public IImageStore storeblocking = StoreFactory.getImageStore();
	/**
	 * 
	 */
	private static final long serialVersionUID = 6747143681976968595L;

	private JLabel label[];
	private BufferedImage buffImage;
	private ImageIcon imgIcon = null;
	private List<Image> lImages;
	// private long[] ex_id;

	private BufferedImage resizeImage;

	public BlockingPictures() {
		this.setName("Block");
		ImageLoader();
		JPanel picPanel = new JPanel();
		picPanel.setLayout(new GridLayout(5, 5));
		label = new JLabel[lImages.size()];
		System.out.println("Antall bilder: " + lImages.size());

		int count = 0;
		if (lImages.size() != 0) {
			if (lImages.size() > 20) {

				for (int i = 0; i < 20; i++) {
					count++;
					System.out.println("mer enn 30: " + count);
					buffImage = loadImage(lImages.get(i).getUrl());
					buffImage = resize(buffImage);
					// ex_id[i] = (lImages.get(i).getID());
					imgIcon = new ImageIcon(buffImage);
					label[i] = new JLabel(imgIcon);
					label[i].addMouseListener(createMouseListener());
					picPanel.add(label[i]);
					// System.out.println("test: " + lImages.get(i).getUrl());
				}
			} else if (lImages.size() <= 20) {
				System.out.println("mindre enn 30");

				for (int i = 0; i < lImages.size(); i++) {
					buffImage = loadImage(lImages.get(i).getUrl());
					buffImage = resize(buffImage);
					// ex_id[i] = (lImages.get(i).getID());
					imgIcon = new ImageIcon(buffImage);
					label[i] = new JLabel(imgIcon);
					label[i].addMouseListener(createMouseListener());
					picPanel.add(label[i]);
					// System.out.println("test: " + lImages.get(i).getUrl());

				}
			}
			// }

			// picPanel.setLayout(new BoxLayout(picPanel, BoxLayout.Y_AXIS));

			picPanel.add(Box.createRigidArea(new Dimension(5, 5)));
			picPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

			this.setLayout(new BorderLayout());
			this.add(picPanel, BorderLayout.CENTER);
			setVisible(true);
		}
	}

	public void ImageLoader() {
		lImages = getImagesFromDB();
	}

	private ArrayList<Image> getImagesFromDB() {
		String json = Fetcher.fetchImagesFromServer();
		return ImageParser.getImageFromJson(json);
	}

	private BufferedImage loadImage(String urlString) {
		BufferedImage bImages = null;
		try {
			URL url = new URL(urlString);
			bImages = ImageIO.read(url);
		} catch (Exception e) {
			return null;
		}
		return bImages;
	}

	public static BufferedImage resize(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		int newH = 140;
		int newW = 140;
		BufferedImage dimg = new BufferedImage(newH, newW, img.getType());
		Graphics2D g = dimg.createGraphics();
		System.out.println("graphics");
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return dimg;
	}

	public MouseAdapter createMouseListener() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JLabel label = (JLabel) e.getSource();
				if (!label.isEnabled()) {
					label.setEnabled(true);
					System.out.println("unblocked");

				} else {
					// label.setText("Clicked");
					label.setEnabled(false);
					storeblocking.block(3);
					System.out.println("blocked");
				}
			}
		};
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
