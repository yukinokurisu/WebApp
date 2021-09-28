package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Message;

public class MessageDao {

	public List<Message> findAll() throws SQLException {

		String sql = "SELECT * FROM messages ORDER BY message_id DESC";

		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}

	}

	public void create(Message message) throws SQLException {
		String sql = "INSERT INTO messages" +
				" VALUES (null, ?, ?, ?, 0);";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, message.getUserName());
			stmt.setString(2, message.getComment());
			stmt.setString(3, message.getDate());
			stmt.execute();
			return ;
		} catch (SQLException e) {
			throw e;
		}
	}

	public void good(int message_id) throws SQLException {
		String sql = "UPDATE messages SET good = good+1 WHERE message_id = "
				+ message_id;
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.execute();
			return ;
		} catch (SQLException e) {
			throw e;
		}
	}

	private List<Message> executeSelectQuery(ResultSet rs) throws SQLException {

		List<Message> messages = new ArrayList<>();
		while (rs.next()) {
			Message message =
					new Message(rs.getInt("message_id"),
							rs.getString("name"),
							rs.getString("comment"),
							rs.getString("date"),
							rs.getInt("good") );
			messages.add(message);
		}
		rs.close();
		return messages;

	}
}