package dal.admin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class StoreBlocking implements IStoreBlocking {
	protected Connection conn;

	public StoreBlocking(java.sql.Connection connection) {
		this.conn = (Connection) connection;
	}

	public synchronized boolean setBlock(int id) {
		try {
			PreparedStatement statement = conn
					.prepareStatement("UPDATE images SET blocked=true WHERE id="
							+ id + ";");
			return statement.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public synchronized boolean removeBlock(int id) {
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement("UPDATE images SET blocked=false"
					+ " WHERE id=" + id + ";");
			return statement.executeUpdate() == 1;

		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}

	}

}
