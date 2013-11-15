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
		
		Refreshable refresher = new RefreshService(fetcher);
		
		IImageLoader loader = new CachedImageLoader(images, refresher);
		
		return loader;
	}

}
