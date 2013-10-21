package dal.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Retrieve keywords from database, add keywords and delete keywords.
 * @author Knut Helland <knutoh@gmail.com>
 */
public class KeywordsStore implements IKeywordsStore {
	Connection conn;

	public KeywordsStore(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<String> getKeywords() {
		ArrayList<String> keywords = new ArrayList<String>();

		try {
			PreparedStatement statement = conn.prepareStatement("SELECT keyword FROM keywords");
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				keywords.add(r.getString("keyword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return keywords;
	}

	public boolean addKeyword(String keyword) {
		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO keywords (keyword) VALUES (?)");
			statement.setString(1, keyword);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean deleteKeyword(String keyword) {
		try {
			PreparedStatement statement = conn.prepareStatement("DELETE FROM keywords WHERE keyword = ?");
			statement.setString(1, keyword);
			return statement.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			return false;
		}
	}	
}
