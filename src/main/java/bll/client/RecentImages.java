package bll.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.imageio.ImageIO;
import bll.utils.ImageParser;
import dal.admin.Image;
import dal.client.Fetcher;

public class RecentImages {

	private Map<Integer, BufferedImage> cache;
	private List<Image> images;
	private ListIterator<Image> iter;

	public RecentImages() {
		cache = new HashMap<Integer, BufferedImage>();
		images = fetchImages();
		iter = images.listIterator();
		cacheImages();
	}

	public BufferedImage getNext() {
		if(iter.hasNext()) {
			Image img = iter.next();
			if(cache.containsKey(img.getID()))
				return cache.get(img.getID());
			else {
				BufferedImage newImg = loadImage(img.getUrl());
				cache.put(img.getID(), newImg);
				return newImg;
			}
		}
		else
			return null;
	}

	private LinkedList<Image> fetchImages() {
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
