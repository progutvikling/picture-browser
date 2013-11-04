package test;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import dal.admin.IImageStore;
import dal.admin.IStoreBlocking;
import dal.admin.Image;
import dal.admin.StoreFactory;

public class StoreBlockingTest {
	IStoreBlocking store = StoreFactory.getStoreBlocking();

	static Integer id = 0;

	@BeforeClass
	public static void getAValidImageId() {
		id = getAId();
		if (id == null) {

			Image img = new Image("http://dummy.com/img.jpg", 234L, "Description", new Date(0001L));
			IImageStore imageStore = StoreFactory.getImageStore();
			imageStore.insert(img);

			id = getAId();
			if (id == null) {
				throw new RuntimeException("There are no images in DB, and I can't insert one. Cannot run tests without.");
			}
		}
	}

	private static Integer getAId() {
		IImageStore imageStore = StoreFactory.getImageStore();
		List<Image> images = imageStore.getLast(1);
		if (images.size() > 0) {
			return new Integer(images.get(0).getInternalId());
		} else {
			return null;
		}
	}

	@Test
	public void setBlock() {
		assertTrue("should retrun true if added", store.setBlock(id));
	}

	@Test
	public void removeBlock(){
		assertTrue("Should return true if removed", store.removeBlock(id));
	}

}
