package client.bll;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import common.dal.Image;
import common.utils.ConfigsParser;
import client.dal.IFetcher;

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

public class RefreshService extends Thread implements Refreshable {

	private static final int INTERVAL = 5;
	private LinkedList<RefreshListener> refreshListeners = new LinkedList<RefreshListener>();
	private IFetcher fetcher;

	public RefreshService(IFetcher fetcher) {
		this.fetcher = fetcher;
		this.start();
	}

	@Override
	public void run() {
		while(true) {	
			refresh();
			sleep();
		}
	}

	public void refresh() {
		List<Image> images = fetchImages();
		Map<String, Object> configs = fetchConfigs();

		for (RefreshListener listener : refreshListeners)
			listener.refreshPerformed(new RefreshEvent(images, configs));
	}

	private List<Image> fetchImages() {
		String json = fetcher.fetchImagesFromServer();
		return Image.createImagesFromJson(json);
	}

	private Map<String, Object> fetchConfigs() {
		String json = fetcher.fetchConfigsFromServer();
		return ConfigsParser.getMapFromJson(json);
	}

	private void sleep() {
		try {
			Thread.sleep(INTERVAL * 60 * 1000);
		} catch (InterruptedException e) {}
	}

	public void addRefreshListener(RefreshListener listener){
		refreshListeners.add(listener);
	}

	public boolean removeRefreshListener(RefreshListener listener){
		return refreshListeners.remove(listener);
	} 

}
