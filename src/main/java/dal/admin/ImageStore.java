package dal.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * This class is responsible for inserting and retrieving
 * images from our database
 * @author Stian Sandve
 *
 */

public class ImageStore implements IImageStore {
	protected Connection conn;

	public ImageStore(Connection conn) {
		this.conn = conn;
	}

	public synchronized boolean insert(Image img) {
		try {
			PreparedStatement statement = conn.prepareStatement(
				"INSERT INTO images " +
				" (url, external_id, description, created_time)" +
				" VALUES (?, ?, ?, ?);");

			statement.setString(1, img.getUrl());
			statement.setInt(2, img.getID());
			statement.setString(3, img.getDescription());
			statement.setDate(4, img.getCreatedTime());
			return statement.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public synchronized ArrayList<Image> getLast(int numberOfRows) {
		if(numberOfRows < 0) {
			throw new IllegalArgumentException();
		}

		ArrayList<Image> images = new ArrayList<Image>();
		
		try {
			PreparedStatement statement = conn.prepareStatement(
				"SELECT url, external_id, description, created_time "+
				" FROM images ORDER BY id DESC LIMIT ?;");
			statement.setInt(1, numberOfRows);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				images.add(new Image(
					result.getString("url"),
					result.getInt("external_id"),
					result.getString("description"),
					result.getDate("created_time")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return images;
	}
}
