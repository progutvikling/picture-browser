package bll.admin;

import java.util.Map;
import dal.admin.IConfigsStore;
import dal.admin.StoreFactory;
import gui.admin.ManageConfigsPanel;
import gui.admin.ManageConfigsPanelHandler;

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
