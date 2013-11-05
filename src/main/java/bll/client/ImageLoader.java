package bll.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
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

	public ImageLoader() {
		images = fetchImages();
	}

	public BufferedImage getNext() {
		BufferedImage nextImage = loadImage(images.get(pos).getUrl());
		incrementPos();
		return nextImage;
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
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return bi;
	}
}
