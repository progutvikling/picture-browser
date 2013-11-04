package dal.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InstagramSource implements IImageSource {
	protected IInstagramClient client;

	public InstagramSource(IInstagramClient client) {
		this.client = client;
	}

	public List<Image> getByKeyword(String keyword, int count) {
		List<Image> images = new ArrayList<Image>();

		// Fetch images until we get 100 or there is no more...
		long maxIdFetched = 0;
		while (images.size() < count) {
			maxIdFetched = getSomeImages(images, keyword, maxIdFetched);
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

	private long getSomeImages(List<Image> images, String keyword, long maxId) {
		long maxIdFetched = -1;

		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(client.search(keyword, 0)).getAsJsonObject();
		JsonArray jsonPictures = obj.get("data").getAsJsonArray();
                
		for(JsonElement i : jsonPictures) {
			if (i.isJsonObject()) {
				JsonObject picture = i.getAsJsonObject();

				long id = 0L;
				try { 
					id = picture.get("caption").getAsJsonObject().get("id").getAsLong();
					if (maxIdFetched < id) {
						maxIdFetched = id;
					}
				} catch (IllegalStateException e) {
					continue;
				}

				JsonElement imgs = picture.get("images");
				JsonElement image = imgs.getAsJsonObject().get("standard_resolution");
				int createdTime = picture.get("created_time").getAsInt();
				String url = image.getAsJsonObject().get("url").getAsString();
				String description = picture.get("caption").getAsJsonObject().get("text").getAsString();

				Image img = new Image(url, id, description, new Date(createdTime*1000));
				images.add(img);
			}
		}

		return maxIdFetched;
	}

}
