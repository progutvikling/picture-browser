package bll.client;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import bll.utils.ImageParser;
import dal.admin.Image;
import dal.client.Fetcher;

public class ImageLoader {

	private List<Image> images;
	private int pos = 0;
	private BufferedImage nextImage = null;

	public ImageLoader() {
		images = fetchImages();
	}

	public BufferedImage getNext() {
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

	private ArrayList<Image> fetchImages() {
		String json = Fetcher.fetchImagesFromServer();
		return ImageParser.getImageFromJson(json);
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
}
