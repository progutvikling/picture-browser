package test;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import dal.admin.IImageStore;
import dal.admin.Image;
import dal.admin.StoreFactory;

public class StoreBlockingTest {
	//IStoreBlocking store = StoreFactory.getStoreBlocking();
	IImageStore store = StoreFactory.getImageStore();
	static Long id = 0L;

	@BeforeClass
	public static void getAValidImageId() {
		id = getAId();
		if (id == null) {

			Image img = new Image("http://dummy.com/img.jpg", (long)(Math.random()*10000), "keyword", "Description", new Date(0001L));
			IImageStore imageStore = StoreFactory.getImageStore();
			imageStore.insert(img);

			id = getAId();
			if (id == null) {
				throw new RuntimeException("There are no images in DB, and I can't insert one. Cannot run tests without.");
			}
		}
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

	@Test
	public void setBlock() {
		assertTrue("should retrun true if added", store.block(id));
	}

	@Test
	public void removeBlock(){
		assertTrue("Should return true if removed", store.unBlock(id));
	}

}
