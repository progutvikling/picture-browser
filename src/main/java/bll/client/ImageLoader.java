package bll.client;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import dal.admin.Image;

public class ImageLoader implements RefreshListener {

	private List<Image> images;
	private int pos = 0;
	private BufferedImage nextImage = null;
	private Refresher refresher;

	public ImageLoader() {
		refresher = new Refresher();
		refresher.addRefreshListener(this);
		refresher.start();
		
		/**
		 * Manually fetch the images once to ensure that
		 * users can call getNext() immediately after construction
		 */
		images = refresher.fetchImages();
	}

	public BufferedImage getNext() {
		if(images == null)
			return null;
		
		BufferedImage currentImage;
		
		if(pos == 0)
			currentImage = loadImage(images.get(pos).getUrl());
		else if(nextImage == null) {
			incrementPos();
			currentImage = loadImage(images.get(pos).getUrl());
		}
		else
			currentImage = nextImage;
		
		incrementPos();
		loadNextImage();
		
		return currentImage;
	}
	
	private void loadNextImage() {
		new Thread(new Runnable() {
		    @Override public void run() {
		    	nextImage = loadImage(images.get(pos).getUrl());
		    }
		}).start();
	}

	private void incrementPos() {
		if(pos < images.size()-1)
			pos++;
		else
			pos = 0;
	}

	private BufferedImage loadImage(String urlString) {
		BufferedImage bi = null;
		try {
			URL url = new URL(urlString);
			bi = ImageIO.read(url);
		} catch (Exception e) {
			return null;
		}
		return bi;
	}

	@Override
	public void refreshPerformed(RefreshEvent e) {
		images = e.getImages();
	}
}
