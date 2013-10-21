package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import dal.admin.Image;
import dal.admin.TwitterClient;
import dal.admin.TwitterSource;


public class TwitterSourceTest {
	@Test
	public void integrationTest_GetByKeyword() {
		TwitterSource source = new TwitterSource(new TwitterClient());
		
		List<Image> images = source.getByKeyword("Utøya", 10);
		assertEquals(images.size(), 10);
		
		assertNotNull(images.get(1).getUrl());
		assertNotNull(images.get(1).getID());
		assertNotNull(images.get(1).getCreatedTime());
		assertNotNull(images.get(1).getDescription());
	}
}
