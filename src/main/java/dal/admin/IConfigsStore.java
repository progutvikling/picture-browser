package dal.admin;

import java.util.Map;

public interface IConfigsStore {

	/**
	 * Returns a value associated with a given name
	 * @param name
	 * @return
	 */
	public String getConfig(String name);

	/**
	 * Adds a new configuration if it does not already exist.
	 * If it exists, its corresponding value will be updated.
	 * Returns true if insertion succeeds and false if it fails
	 * @param name	name of the configuration
	 * @param value	value associated with the configuration name
	 * @return	true if the operation succeeded
	 */
	public boolean addConfig(String name, String value);

	/**
	 * Deletes a configuration associated with a given name
	 * @param name The name of the configuration
	 * @return	true if the operation succeeded
	 */
	public boolean deleteConfig(String name);

	public Map<String, String> getConfigs();
}