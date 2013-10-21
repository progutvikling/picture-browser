package dal.admin;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class TwitterSource implements IImageSource {
	ITwitterClient twitter;

	private static final SimpleDateFormat twitterDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);

	public TwitterSource(ITwitterClient twitter) {
		this.twitter = twitter;
	}

	@Override
	public List<Image> getByKeyword(String keyword, int count) {
		List<Image> images = new ArrayList<Image>();

		// Fetch images until we get 100 or there is no more...
		long maxIdFetched = 0;
		while (images.size() < count) {
			maxIdFetched = fetchSomeImages(images, keyword, maxIdFetched);
			if (maxIdFetched == -1) {
				break;
			}
		}

		if (images.size() > count) {
			return images.subList(0, count);
		} else {
			return images;
		}
	}


	private long fetchSomeImages(List<Image> images, String keyword, long maxId) {
		long maxIdFetched = -1;

		String response = twitter.search(keyword, maxId);
		JsonObject responseJson = new GsonBuilder().create().fromJson(response, JsonObject.class);
		JsonArray statuses = responseJson.getAsJsonArray("statuses");

		for (JsonElement i : statuses) {
			if (i.isJsonObject()) {
				JsonObject status = (JsonObject)i;
				JsonElement entities;
				JsonElement media;
				JsonObject mediaObject;

				long id = status.get("id").getAsLong();
				if (maxIdFetched < id) {
					maxIdFetched = id;
				}

				if ((entities = status.get("entities")) != null) {
					if ((media = entities.getAsJsonObject().get("media")) != null) {
						JsonArray mediaArray = media.getAsJsonArray();

						for (JsonElement mediaElement : mediaArray) {
							mediaObject = mediaElement.getAsJsonObject();
							
							if (mediaObject.get("type").getAsString().equals("photo")) {
								Image img = new Image(
									mediaObject.get("media_url").getAsString(),
									mediaObject.get("id").getAsInt(),
									status.get("text").getAsString(),
									parseDate(status.get("created_at").getAsString()));
								
								images.add(img);
							}								
						}
					}
				}
			}
		}

		return maxIdFetched;
	}

	/**
	 * Helper function to parse date
	 */
	public static Date parseDate(String input) {
		try {
			return new Date(twitterDateFormat.parse(input).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
