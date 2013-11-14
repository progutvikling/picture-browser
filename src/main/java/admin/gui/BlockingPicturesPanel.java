package admin.gui;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import common.dal.IImageStore;
import common.dal.Image;

public class BlockingPicturesPanel extends JPanel {
	private static final long serialVersionUID = -1949002606878636732L;

	private IBlockingPicturesPanelHandler handler = null;	
	
	private int kol = 4;
	private JPanel picPanel;
	private LoadInBackground loadImage;
	private int imageLength;
	private int storedLabel = 0;
	private JLabel temp[];
	private List<Image> lImagesFromLoad;
	
	
	public BlockingPicturesPanel(IBlockingPicturesPanelHandler handler) {
		this.handler = handler;
		
		this.setName("Block");
		lImagesFromLoad = handler.getImages();
		picPanel = new JPanel();
		loadImage = new LoadInBackground();
		loadImage.execute();

		/**
		 * Burde ikkje gjør dette sidde eg benytter getLast() også i
		 * loadInBavkgroun klassen.Dobbelt med bilder. Fant ingen god måte å få
		 * antall bilder, alltid hentet før klassen var ferdig med å laste
		 * bilder...
		 * 
		 */
		imageLength = lImagesFromLoad.size();
		temp = new JLabel[imageLength];
		
		
		JScrollPane scrollpane = new JScrollPane(picPanel);
		scrollpane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.getVerticalScrollBar().setUnitIncrement(16);
		scrollpane.setBounds(getVisibleRect());
		this.setLayout(new BorderLayout());
		this.add(scrollpane, BorderLayout.CENTER);
		setVisible(true);
		
	}
	
	public void setLabel(List<JLabel> l) {
		setPanel();
		JLabel imageIconLabel = new JLabel();
		imageIconLabel = l.get(0);
		temp[storedLabel] = imageIconLabel;
		temp[storedLabel].addMouseListener(new MouseClickListener());
		picPanel.add(temp[storedLabel]);
		storedLabel++;
		System.out.println("storedlable: " + storedLabel);
	}

	public void setPanel() {
		lImagesFromLoad = loadImage.getImage();
		// imageSize = lImagesFromLoad.size();
		// System.out.println(imageSize);
		picPanel.setLayout(new GridLayout((imageLength / kol) + 1, kol, 5, 5));
	}

	
	
	private class MouseClickListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel coverLabel = (JLabel) e.getSource();
			for (int i = 0; i < storedLabel; i++) {
				if (e.getSource().equals(temp[i])) {
					if (!coverLabel.isEnabled()) {
						coverLabel.setEnabled(true);
						handler.unblockImage(lImagesFromLoad.get(i));
						System.out.println("unblock "
								+ lImagesFromLoad.get(i).getID());

					} else {
						coverLabel.setEnabled(false);
						// System.out.println("----------------------image id: "+lImagesFromLoad.get(i).getID());
						handler.blockImage(lImagesFromLoad.get(i));
						System.out.println("block "
								+ lImagesFromLoad.get(i).getID());

					}
				}

			}
		}
	}
	
	
	/**
	 * Swing worker that downloads the images and puts them into the panel.
	 */
	private class LoadInBackground extends SwingWorker<JLabel, JLabel> {
		private JLabel label;
		private BufferedImage buffImage;
		private ImageIcon imgIcon;
		private List<Image> lImages;
		int count = 0;
	
		protected JLabel doInBackground() throws Exception {
			ImageLoader();
			System.out.println("Antall bilder: " + lImages.size());
			label = new JLabel();
	
			if (lImages.size() != 0) {
				for (int i = 0; i < lImages.size(); i++) {
					buffImage = loadImage(lImages.get(i).getUrl());
					buffImage = resize(buffImage);
					if (buffImage == null) {
						count++;
						System.out.println("<--Could not resize picture : "
								+ count + "-->");
						continue;
					}
					if (buffImage != null) {
						imgIcon = new ImageIcon(buffImage);
						label = new JLabel(imgIcon);
						publish(label);
	
					}
	
				}
			}
			return label;
		}
	
		@Override
		protected void process(List<JLabel> label) {
			setLabel(label);
		}
	
		public List<Image> getImage() {
			return lImages;
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
	
		public BufferedImage resize(BufferedImage img) {
			try {
				int w = img.getWidth();
				int h = img.getHeight();
				int newH = 130;
				int newW = 130;
				BufferedImage dimg = new BufferedImage(newH, newW, img.getType());
				Graphics2D g = dimg.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
				g.dispose();
				return dimg;
			} catch (NullPointerException e) {
				return null;
			}
		}
	
		public void ImageLoader() {
			lImages = handler.getImages();
	
		}
	
	}
}
