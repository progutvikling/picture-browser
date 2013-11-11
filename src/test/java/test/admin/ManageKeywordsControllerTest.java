package test.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import admin.bll.ManageKeywordsController;
import common.dal.IImageStore;
import common.dal.IKeywordsStore;
import common.dal.Image;

public class ManageKeywordsControllerTest {
	
	private KeywordsStoreMock keywordsStore;
	private ImageStoreMock imageStore;
	private ManageKeywordsController controller;
	
	@Before
	public void setupStoreAndController() {
		keywordsStore = new KeywordsStoreMock();
		imageStore = new ImageStoreMock();
		controller = new ManageKeywordsController(keywordsStore, imageStore);
	}
	
	@After
	public void cleanupStoreAndController() {
		keywordsStore = null;
		controller = null;
	}
	
	@Test
	public void testAddKeyword() {
		assertTrue("Should return true", controller.addKeyword("Donald Duck"));
		assertFalse("Should not add twice", controller.addKeyword("Donald Duck"));
		assertTrue("And should have added to our store", keywordsStore.keywords.contains("Donald Duck"));
	}
	
	@Test
	public void testRemoveKeyword() {
		controller.addKeyword("Heisann");
		controller.addKeyword("Hoppsann");
		
		assertTrue("Should remove keyword", controller.deleteKeyword("Heisann"));
		assertFalse("But not twice", controller.deleteKeyword("Heisann"));
		assertFalse("Keyword should have been removed from our store",
				keywordsStore.keywords.contains("Heisann"));
		assertTrue("Images with removed keyword should be deleted",
				imageStore.deletedKeywords.contains("Heisann"));
	}
	
	
	@Test
	public void testGetKeywords() {
		controller.addKeyword("Heisann");
		controller.addKeyword("Hoppsann");
		
		assertEquals(controller.getKeywords(), keywordsStore.keywords);
	}
	
	
	
	
	
	
	private class KeywordsStoreMock implements IKeywordsStore {
		public ArrayList<String> keywords = new ArrayList<String>();
		
		@Override
		public ArrayList<String> getKeywords() {
			return keywords;
		}

		@Override
		public boolean addKeyword(String keyword) {
			if (!keywords.contains(keyword))
				return keywords.add(keyword);
			return false;
		}

		@Override
		public boolean deleteKeyword(String keyword) {
			return keywords.remove(keyword);
		}
	}
	
	
	
	
	private class ImageStoreMock implements IImageStore {

		public ArrayList<String> deletedKeywords = new ArrayList<String>();
		
		@Override
		public boolean insert(Image img) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public ArrayList<Image> getLast(int numberOfRows) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean block(long id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean unBlock(long id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean deleteAllWithKeyword(String keyword) {
			deletedKeywords.add(keyword);
			return true;
		}

		@Override
		public String getLastAsJson(int numberOfImagesToServe) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
