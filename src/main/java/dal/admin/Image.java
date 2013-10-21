package dal.admin;

import java.sql.Date;

/**
 * 
 * A POJO with the information we need for the 
 * images fetched from twitter an instagram.
 * @author Stian Sandve
 *
 */

public class Image {

	private String url;
	private int id;
	private String description;
	private Date createdTime;

	public Image(String url, int id, String description, Date createdTime) {
		this.url = url;
		this.id = id;
		this.description = description;
		this.createdTime = createdTime;
	}

	public String getUrl() {
		return this.url;
	}

	public int getID() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public Date getCreatedTime() {
		return this.createdTime;
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
}
