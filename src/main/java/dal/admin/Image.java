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
}
