package bll.client;

import java.util.List;
import java.util.Map;

import dal.admin.Image;

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
