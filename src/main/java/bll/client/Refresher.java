package bll.client;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import bll.utils.ConfigsParser;
import bll.utils.ImageParser;
import dal.admin.Image;
import dal.client.Fetcher;

public class Refresher extends Thread {

	private static final int INTERVAL = 5;
	private LinkedList<RefreshListener> refreshListeners = new LinkedList<RefreshListener>();

	@Override
	public void run() {
		while(true) {
			
			List<Image> images = fetchImages();
			Map<String, Object> configs = fetchConfigs();
			
			for (RefreshListener listener : refreshListeners)
				listener.refreshPerformed(new RefreshEvent(images, configs));
			
			try {
				Thread.sleep(INTERVAL * 60 * 1000);
			} catch (InterruptedException e) {}
		}
	}
	
	public List<Image> fetchImages() {
		String json = Fetcher.fetchImagesFromServer();
		return ImageParser.getImageFromJson(json);
	}
	
	public Map<String, Object> fetchConfigs() {
		String json = Fetcher.fetchConfigsFromServer();
		return ConfigsParser.getMapFromJson(json);
	}
    
    public void addRefreshListener(RefreshListener listener){
        refreshListeners.add(listener);
    }
    
    public boolean removeRefreshListener(RefreshListener listener){
        return refreshListeners.remove(listener);
    } 
	
}
