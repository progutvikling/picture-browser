package common.dal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * A POJO with the information we need for the 
 * images fetched from twitter an instagram.
 * @author Stian Sandve
 *
 */

public class Image {

	private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

	private String url;
	private long id;
	private String description;
	private String keyword;
	private Date createdTime;
	private int internalId;

	public Image(String url, long id, String description, String keyword, Date createdTime) {
		this.url = url;
		this.id = id;
		this.description = description;
		this.keyword = keyword;
		this.createdTime = createdTime;
	}

	public Image(String json) {
		Gson gson = new GsonBuilder()
		.setDateFormat(DATE_FORMAT).create();
		Image img = gson.fromJson(json, Image.class);
		this.url = img.getUrl();
		this.id = img.getID();
		this.description = img.getDescription();
		this.keyword = img.getKeyword();
		this.createdTime = img.getCreatedTime();
	}

	public String getUrl() {
		return this.url;
	}

	public long getID() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setInternalId(int id) {
		this.internalId = id;
	}

	public int getInternalId() {
		return this.internalId;
	}

	@Override
	public boolean equals(Object obj) {
		Image img = (Image) obj;
		if (
				img.getUrl().equals(this.url) &&
				img.getID() == this.id &&
				img.getDescription().equals(this.description) &&
				img.getCreatedTime().equals(this.createdTime)
				)
			return true;
		else
			return false;
	}

	public String toJson() {
		String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
		Gson gson = new GsonBuilder()
		.setDateFormat(DATE_FORMAT).create();
		String json = gson.toJson(this);
		return json;
	}

	public static ArrayList<Image> createImagesFromJson(String json) {
		if(json != null && !(json.equals(""))) {
			Gson gson = new GsonBuilder()
			.setDateFormat(DATE_FORMAT).create();

			Image[] img = gson.fromJson(json, Image[].class);
			return new ArrayList<Image>(Arrays.asList(img));
		} 
		else
			return null;
	}
}
