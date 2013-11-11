package test.common;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import common.dal.IImageStore;
import common.dal.Image;
import common.dal.StoreFactory;

/**
 * 
 * An integration test for our database operations
 * @author Stian Sandve
 *
 */

public class ImageStoreTest {
	
	IImageStore store = StoreFactory.getImageStore();
	
	@Test
	public void insertTest() {
		java.util.Date currentTime = new java.util.Date();
		Image img = new Image("insertUrl", (long)(Math.random()*100000), "insertTest", "insertTest", new Date(currentTime.getTime()));
		assertTrue("A valid insert should return true", store.insert(img));
	}
	
	@Test
	public void getLastHundredImagesTest() {
		ArrayList<Image> images = store.getLast(10);
		assertTrue("Should return maximum 10 images", images.size() <= 10);
	}
}
