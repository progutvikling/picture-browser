
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

import dal.admin.IKeywordsStore;
import dal.admin.StoreFactory;


public class KeywordsStoreTest {
	IKeywordsStore store = StoreFactory.getKeywordsStore();

	public String generateRandomKeyword() {
		return String.valueOf(UUID.randomUUID());
	}
		
	@Test
	public void addKeywordTest() {
		String randomKeyword = generateRandomKeyword();

		assertTrue("Should return true if added", store.addKeyword(randomKeyword));
		assertFalse("Database should have UNIQUE index on keywords, so we cannot add twice..." +
					" (run sql-scripts/4-keywords-should-be-unique if this fails)",
					store.addKeyword(randomKeyword));
		store.deleteKeyword(randomKeyword);
	}
	
	@Test
	public void deleteKeywordTest() {
	    String randomKeyword = generateRandomKeyword();

		assertFalse("Should return false if the keyword doesn't exist.",
					store.deleteKeyword(randomKeyword));
		
		store.addKeyword(randomKeyword);
		assertTrue("Should successfully delete the existing keyword. (this depends on addKeyword working well)",
				   store.deleteKeyword(randomKeyword));
	}

	@Test
	public void getKeywords() {
		ArrayList<String> randomKeywords = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			String random = generateRandomKeyword();
			randomKeywords.add(random);
			store.addKeyword(random);
		}

		ArrayList<String> keywords = store.getKeywords();
		for (String random : randomKeywords) {
			assertTrue("Should retrieve the keywords added (depends on addKeyword).",
					   keywords.contains(random));
			store.deleteKeyword(random);
		}
	}
}
