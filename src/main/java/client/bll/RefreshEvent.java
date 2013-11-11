package client.bll;

import java.util.List;
import java.util.Map;

import common.dal.Image;

/**
 * A refresh event consists of the latest images
 * and configurations from the database
 * 
 * @author Stian Sandve <stian@sandve.org>
 *
 */

public class RefreshEvent {
	
	List<Image> images;
	Map<String, Object> configs;
	
	public RefreshEvent(List<Image> images, Map<String, Object> configs) {
		this.images = images;
		this.configs = configs;
	}

	public List<Image> getImages() {
		return images;
	}

	public Map<String, Object> getConfigs() {
		return configs;
	}

}
