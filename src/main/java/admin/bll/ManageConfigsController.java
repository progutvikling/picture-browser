package admin.bll;

import java.util.Map;

import common.dal.IConfigsStore;
import common.dal.StoreFactory;

import admin.gui.ManageConfigsPanel;
import admin.gui.ManageConfigsPanelHandler;

public class ManageConfigsController implements ManageConfigsPanelHandler {
	public ManageConfigsPanel view;
	public IConfigsStore store = StoreFactory.getConfigsStore();
	
	public Map<String, String> configs = store.getConfigs();
	
	public ManageConfigsController() {
		view = new ManageConfigsPanel(this);
	}

	@Override
	public boolean addConfig(String name, String value) {
		if (store.addConfig(name, value)) {
			configs.put(name, value);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteConfig(String name) {
		if (store.deleteConfig(name)) {
			configs.remove(name);
			return true;
		}
		return false;
	}

	@Override
	public String getConfig(String name) {
		return configs.get(name);
	}

	@Override
	public Map<String, String> getConfigs() {
		return configs;
	}
	
	public ManageConfigsPanel getView() {
		return view;
	}

}
