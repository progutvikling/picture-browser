package test.fetcher;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fetcher.dal.InstagramClient;


public class InstagramClientTest {
	@Test
	public void test_search() {
		InstagramClient client = new InstagramClient();
		
		String result = client.search("Obama", 0);
		assertNotNull("Should get a json result", result);

		try {
			JsonObject responseJson = new GsonBuilder().create().fromJson(result, JsonObject.class);
			JsonArray statuses = responseJson.getAsJsonArray("data");
			assertNotNull("We should get a array of data objects", statuses);
		} catch (Exception e) {
			assertNotNull("We didn't get any data objects.", null);
		}
	}
}
