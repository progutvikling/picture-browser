package client.bll;

import client.dal.Fetcher;
import client.dal.IFetcher;

import java.util.List;
import common.dal.Image;

public class ImageLoaderFactory {
	
	public static IImageLoader build() {
		IFetcher fetcher = new Fetcher();
		String json = fetcher.fetchImagesFromServer();
		List<Image> images = Image.createImagesFromJson(json);
		
		Refresher refresher = new RefreshService(fetcher);
		
		IImageLoader loader = new ImageLoader(images, refresher);
		
		return loader;
	}

}
