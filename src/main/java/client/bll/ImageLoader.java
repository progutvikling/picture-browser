package client.bll;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import common.dal.Image;

/**
 * This class is responsible for providing the
 * slide show with images. It asynchronous loads the 
 * next image into memory while the current images is shown.
 * 
 * It also listens for changes from the refresher to keep
 * images and configs up to date.
 * 
 * @author Stian Sandve <stian@sandve.org>
 *
 */

public class ImageLoader implements RefreshListener, IImageLoader {

	private static final String SLIDESHOW_DELAY_KEY = "slideshow_delay";

	private List<Image> images;
	private int pos = 0;
	private BufferedImage nextImage = null;
	private Refreshable refresher;
	private int slideshowDelay = 3;

	public ImageLoader(List<Image> images, Refreshable refresher) {
		this.images = images;
		this.refresher = refresher;
		this.refresher.addRefreshListener(this);
	}

	/* (non-Javadoc)
	 * @see client.bll.IImageLoader#getNext()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see client.bll.IImageLoader#getSlideshowDelay()
	 */
	@Override
	public int getSlideshowDelay() {
		return slideshowDelay;
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
		String delay = (String) e.getConfigs().get(SLIDESHOW_DELAY_KEY);
		slideshowDelay = Integer.parseInt(delay);
	}
}