package client.bll;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

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

public class CachedImageLoader implements RefreshListener, IImageLoader {

	private static final String SLIDESHOW_DELAY_KEY = "slideshow_delay";

	private List<Image> images;
	private int pos = 0;
	private Refreshable refresher;
	private int slideshowDelay = 3;
	private Map<Long, BufferedImage> cache;

	public CachedImageLoader(List<Image> images, Refreshable refresher) {
		this.images = images;
		this.refresher = refresher;
		this.refresher.addRefreshListener(this);
		this.cache = new Hashtable<Long, BufferedImage>();
		preloadImages();
	}

	@Override
	public BufferedImage getNext() {
		if(images == null){
			return null;
		}

		BufferedImage image;
		Image img = images.get(pos);

		if (cache.containsKey(img.getID())) {
			image = cache.get(img.getID());
		} else {
			image = loadImage(img.getUrl());
		}

		incrementPos();

		return image;
	}

	@Override
	public int getSlideshowDelay() {
		return slideshowDelay;
	}

	private void preloadImages() {
		DownloadImagesTask downloader = new DownloadImagesTask(images);
		downloader.execute();
	}

	private void incrementPos() {
		if(pos < images.size()-1)
			pos++;
		else
			pos = 0;
	}

	private synchronized BufferedImage loadImage(String urlString) {
		BufferedImage bi = null;
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e1) {}
		while (bi == null) {
			try {
				bi = ImageIO.read(url);
			} catch (Exception e) {
				incrementPos();
				try {
					url = new URL(images.get(pos).getUrl());
				} catch (MalformedURLException e1) {}
			}
		}
		return bi;
	}

	private void cache(long id, BufferedImage img) {
		cache.put(id, img);
	}

	@Override
	public void refreshPerformed(RefreshEvent e) {
		images = e.getImages();
		try{
		String delay = (String) e.getConfigs().get(SLIDESHOW_DELAY_KEY);
		slideshowDelay = Integer.parseInt(delay);
		preloadImages();
		refresher.setdelay(5);
		}catch(NullPointerException n){
		}
	}

	private class DownloadImagesTask extends SwingWorker<BufferedImage, Object> {

		private List<Image> images;

		public DownloadImagesTask(List<Image> images) {
			this.images = images;
		}

		@Override
		protected BufferedImage doInBackground() throws Exception {
			for (Image img : images) {
				BufferedImage bi = download(img.getUrl());
				publish(img.getID(), bi);
			}
			return null;
		}

		@Override
		protected void process(List<Object> img) {
			if((BufferedImage) img.get(1) != null)
				cache((long) img.get(0), (BufferedImage) img.get(1));
		}
		
		private BufferedImage download(String url) {
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(new URL(url));
			} catch (Exception e) {
				return null;
			}
			return bi;
		}
	}
}