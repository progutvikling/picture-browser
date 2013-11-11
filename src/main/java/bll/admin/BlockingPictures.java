package bll.admin;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bll.utils.ImageParser;
import dal.admin.IImageStore;
import dal.admin.Image;
import dal.admin.ImageStore;
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
	private ImageIcon imgIcon;
	private List<Image> lImages;
	private int kol = 4;

	public BlockingPictures() {
		this.setName("Block");
		ImageLoader();
		JPanel picPanel = new JPanel();
		picPanel.setLayout(new GridLayout((lImages.size() / kol) + 1, kol, 5, 5));
		label = new JLabel[lImages.size()];
		System.out.println("Antall bilder: " + lImages.size());

		int count = 0;
		if (lImages.size() != 0) {
			if (lImages.size() < 30) {
				for (int i = 0; i < lImages.size(); i++) {
					count++;
					System.out.println(count);
					buffImage = loadImage(lImages.get(i).getUrl());
					if(buffImage == null)
						continue;
					buffImage = resize(buffImage);
					if (buffImage != null) {
						imgIcon = new ImageIcon(buffImage);
						label[i] = new JLabel(imgIcon);
						label[i].addMouseListener(createMouseListener());
						picPanel.add(label[i]);
					}
				}
			}

			// Bare for å slippe å vente på loading av bilde enn så lenge if
			if (lImages.size() >= 30) {
				for (int i = 0; i < 37; i++) {
					count++;
					System.out.println("mer enn 30: " + count);
					buffImage = loadImage(lImages.get(i).getUrl());
					if(buffImage == null)
						continue;
					buffImage = resize(buffImage);
					if (buffImage != null) {
						imgIcon = new ImageIcon(buffImage);
						label[i] = new JLabel(imgIcon);
						label[i].addMouseListener(createMouseListener());
						picPanel.add(label[i]);
					}
				}
			}

		}

		JScrollPane scrollpane = new JScrollPane(picPanel);
		scrollpane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBounds(getVisibleRect());
		this.setLayout(new BorderLayout());
		this.add(scrollpane, BorderLayout.CENTER);
		setVisible(true);
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
		try {
			int w = img.getWidth();

			int h = img.getHeight();
			int newH = 130;
			int newW = 130;
			BufferedImage dimg = new BufferedImage(newH, newW, img.getType());
			Graphics2D g = dimg.createGraphics();
			System.out.println("graphics");
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
			g.dispose();
			return dimg;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public MouseAdapter createMouseListener() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel coverLabel = (JLabel) e.getSource();
				for (int i = 0; i < lImages.size(); i++) {
					if (e.getSource().equals(label[i])) {
						if (!coverLabel.isEnabled()) {
							coverLabel.setEnabled(true);
							storeblocking.unBlock(lImages.get(i).getID());
							System.out.println("unblock "
									+ lImages.get(i).getID());

						} else {
							coverLabel.setEnabled(false);
							storeblocking.block(lImages.get(i).getID());
							System.out.println("block "
									+ lImages.get(i).getID());

						}
					}

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
