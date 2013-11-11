package client.bll;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import common.dal.Image;
import common.utils.ConfigsParser;
import client.dal.Fetcher;

/**
 * This class runs forever in the background and fetches
 * the latest data from the server every X minutes,
 * where X is the INTERVAL constant.
 * 
 * You can implement a refresh listener and register your 
 * class to get notified when new data is fetched.
 * 
 * @author Stian Sandve <stian@sandve.org>
 *
 */

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
		return Image.createImagesFromJson(json);
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
