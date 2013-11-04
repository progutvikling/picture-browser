package bll.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import bll.utils.ImageParser;
import dal.admin.Image;
import dal.client.Fetcher;

public class ImageLoader {

	private Map<Long, BufferedImage> cache;
	private List<Image> images;
	private int pos = 0;

	public ImageLoader() {
		cache = new HashMap<Long, BufferedImage>();
		images = fetchImages();
		cacheImages();
	}

	public BufferedImage getNext() {
		BufferedImage nextImage = null;
		if(!cache.isEmpty() && cache.containsKey(images.get(pos).getID())) {
			nextImage = cache.get(images.get(pos).getID());
		}
		else {
			BufferedImage newImg = loadImage(images.get(pos).getUrl());
			cache.put(images.get(pos).getID(), newImg);
			nextImage = newImg;
		}
		if(pos < images.size()-1)
			pos++;
		else
			pos = 0;
		return nextImage;
	}

	private ArrayList<Image> fetchImages() {
		String json = Fetcher.fetchImagesFromServer();
		return ImageParser.getImageFromJson(json);
	}

	private void cacheImages() {
		for(Image img : images) {
			cache.put(img.getID(), loadImage(img.getUrl()));
		}
	}

	private BufferedImage loadImage(String urlString) {
		BufferedImage bi = null;
		try {
			URL url = new URL(urlString);
			bi = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return bi;
	}

}
