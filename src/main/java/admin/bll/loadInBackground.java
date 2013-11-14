package admin.bll;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import common.dal.IImageStore;
import common.dal.Image;

public class loadInBackground extends SwingWorker<JLabel, JLabel> {
	private JLabel label;
	private BufferedImage buffImage;
	private ImageIcon imgIcon;
	private List<Image> lImages;
	private IImageStore imagestore;
	int count = 0;

	private BlockingPictures blockPic;

	public loadInBackground(IImageStore imagestore, BlockingPictures bp) {
		this.imagestore = imagestore;
		blockPic = bp;
	}

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
		blockPic.setLabel(label);
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

	public static BufferedImage resize(BufferedImage img) {
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
		lImages = imagestore.getLast(100);

	}
	//Denne må vente litt før den bli kallet, det burde funke.
	public int getImageListSize(){
		return lImages.size();
	}

}
