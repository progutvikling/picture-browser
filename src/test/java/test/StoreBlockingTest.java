package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dal.admin.IStoreBlocking;
import dal.admin.StoreFactory;

public class StoreBlockingTest {
	IStoreBlocking store = StoreFactory.getStoreBlocking();
	@Test
	public void setBlock() {
		assertTrue("should retrun true if added", store.setBlock(205));
	}

	@Test
	public void removeBlock(){
		assertTrue("Should return true if removed", store.removeBlock(205));
	}

}
