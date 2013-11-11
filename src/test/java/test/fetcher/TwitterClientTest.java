package test.fetcher;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fetcher.dal.TwitterClient;


public class TwitterClientTest {
	@Test
	public void test_search() {
		TwitterClient client = new TwitterClient();
		
		String result = client.search("Obama", 0);
		assertNotNull("Should get a json result", result);

		try {
			JsonObject responseJson = new GsonBuilder().create().fromJson(result, JsonObject.class);
			JsonArray statuses = responseJson.getAsJsonArray("statuses");
			assertNotNull("We should get a array of statuses", statuses);
		} catch (Exception e) {
			assertNotNull("We didn't get any statuses.", null);
		}
	}
}
