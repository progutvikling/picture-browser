package test.common;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void setBlock() {
		assertTrue("should retrun true if added", store.block(getAValidImageId()));
	}

	@Test
	public void removeBlock(){
		assertTrue("Should return true if removed", store.unBlock(getAValidImageId()));
	}
	
	
	/**
	 * Helper function to get a image id
	 */
	public static Long getAValidImageId() {
		Long id = getAId();
		if (id == null) {

			Image img = new Image("http://dummy.com/img.jpg", (long)(Math.random()*10000), "Description", "keyword", new Date(0001L));
			IImageStore imageStore = StoreFactory.getImageStore();
			imageStore.insert(img);

			id = getAId();
			if (id == null) {
				throw new RuntimeException("There are no images in DB, and I can't insert one. Cannot run tests without.");
			}
		}
		return id;
	}

	private static Long getAId() {
		IImageStore imageStore = StoreFactory.getImageStore();
		List<Image> images = imageStore.getLast(1);
		if (images.size() > 0) {
			return new Long(images.get(0).getID());
		} else {
			return null;
		}
	}
	
}
