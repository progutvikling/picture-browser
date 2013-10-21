package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.UUID;
import org.junit.Test;
import dal.admin.IConfigsStore;
import dal.admin.StoreFactory;

public class ConfigsStoreTest {

	IConfigsStore store = StoreFactory.getConfigsStore();

	public String generateRandomString() {
		return String.valueOf(UUID.randomUUID());
	}

	@Test
	public void addConfigTest() {
		String randomConfigName = generateRandomString();
		String randomConfigValue = generateRandomString();

		assertTrue("Should return true if added", store.addConfig(randomConfigName, randomConfigValue));

		String secondRandomConfigValue = generateRandomString();
		assertTrue("Should return true even if we add a config multiple times with the same name." + 
				" We update the existing config value and keep the same name", 
				store.addConfig(randomConfigName, secondRandomConfigValue));
		assertTrue(store.deleteConfig(randomConfigName));
	}

	@Test
	public void deleteConfigTest() {
		String randomConfigName = generateRandomString();
		String randomConfigValue = generateRandomString();

		assertFalse("Should return false if the config doesn't exist.",
				store.deleteConfig(randomConfigName));

		store.addConfig(randomConfigName, randomConfigValue);
		assertTrue("Should successfully delete the existing config. (this depends on addConfig working well)",
				store.deleteConfig(randomConfigName));
	}

	@Test
	public void getConfig() {
		String randomConfigName = generateRandomString();
		String randomConfigValue = generateRandomString();

		store.addConfig(randomConfigName, randomConfigValue);

		assertEquals("Should be able to retrieve the value of a previously" +
				" inserted configuration (depends on addConfig)"
				, randomConfigValue, store.getConfig(randomConfigName));
		store.deleteConfig(randomConfigName);
	}
}