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
		Refreshable refresher;
		if(json.equals("")) {
			 refresher = new RefreshService(fetcher, 0.1);
		}else{
			refresher = new RefreshService(fetcher, 5);
		}
		IImageLoader loader = new CachedImageLoader(images, refresher);
		
		return loader;
	}

}
