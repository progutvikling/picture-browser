package dal.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class StoreFactory {
	/* settings */
	public static final String DB_URL = "jdbc:mysql://localhost/PictureBrowser";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "";
	/* end settings */


	/** Holds a singleton database connection */
	private static Connection conn;


	/** Returns the connection if it is alive, or creates a new one. */
	public static Connection getConnection() {
		try {
			if (StoreFactory.conn != null && StoreFactory.conn.isValid(1)) {
				return conn;
			}
			return StoreFactory.conn = DriverManager.getConnection(StoreFactory.DB_URL, StoreFactory.DB_USERNAME, StoreFactory.DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Could not create database connection!");
			e.printStackTrace();
			return null;
		}
	}


	public static IImageStore getImageStore() {
		return new ImageStore(StoreFactory.getConnection());
	}


	public static IKeywordsStore getKeywordsStore() {
		return new KeywordsStore(StoreFactory.getConnection());
	}
	
	public static IConfigsStore getConfigsStore() {
		return new ConfigsStore(StoreFactory.getConnection());
	}
}
