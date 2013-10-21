package gui.admin;

import java.util.Map;

public interface ManageConfigsPanelHandler {
	
	/**
	 * Should return true if the config is accepted and should be
	 * added to the list.
	 */
	public boolean addConfig(String name, String value);

	/**
	 * Should return true if the config is successfully deleted
	 */
	public boolean deleteConfig(String name);
	
	/**
	 * Should return the value associated with the configuration
	 * with the specified name
	 */
	public String getConfig(String name);

	/**
	 * Should return a map of all configs.
	 */
	public Map<String, String> getConfigs();

}
