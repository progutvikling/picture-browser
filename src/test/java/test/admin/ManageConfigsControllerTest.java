package test.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import admin.bll.ManageConfigsController;
import common.dal.IConfigsStore;

public class ManageConfigsControllerTest {

	ConfigsStoreMock store;
	ManageConfigsController controller;
	
	@Before
	public void setup() {
		store = new ConfigsStoreMock();
		controller = new ManageConfigsController(store);
	}
	
	@After
	public void teardown() {
		store = null;
		controller = null;
	}
	
	
	@Test
	public void testAddConfig() {
		assertTrue("Should return true", controller.addConfig("myKey", "myValue"));
		assertTrue("Should have added it to the store",
				store.configs.containsKey("myKey"));
	}
	
	@Test
	public void testDeleteConfig() {
		controller.addConfig("Hello", "hey");
		assertTrue(controller.deleteConfig("Hello"));
		assertFalse(store.configs.containsKey("Hello"));
	}
	
	@Test
	public void testGetConfig() {
		controller.addConfig("Hello", "my value");
		assertEquals(controller.getConfig("Hello"), "my value");
	}
	
	@Test
	public void testGetConfigs() {
		controller.addConfig("Hello", "my value");
		assertEquals(controller.getConfigs(), store.configs);
	}
	
	
	
	
	private class ConfigsStoreMock implements IConfigsStore {

		public HashMap<String, String> configs = new HashMap<String, String>();
		
		@Override
		public String getConfig(String name) {
			return configs.get(name);
		}

		@Override
		public boolean addConfig(String name, String value) {
			configs.put(name, value);
			return true;
		}

		@Override
		public boolean deleteConfig(String name) {
			return configs.remove(name) != null;
		}

		@Override
		public Map<String, String> getConfigs() {
			return configs;
		}
	}
}
