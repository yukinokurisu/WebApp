package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

	public List<History> findAll() throws SQLException {

		String sql = "SELECT * FROM histories INNER JOIN videos ON histories.youtube_video_id = videos.youtube_video_id;";

		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<History> Ranking() throws SQLException {

		String sql = "SELECT  title, histories.youtube_video_id, channel_name, count(histories.youtube_video_id) AS COUNT\r\n" +
				"FROM histories\r\n" +
				"INNER JOIN videos ON histories.youtube_video_id = videos.youtube_video_id\r\n" +
				"GROUP BY histories.youtube_video_id\r\n" +
				"ORDER BY  count(histories.youtube_video_id) DESC";

		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			return executeSelectQuery2(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}


	public List<History> findByTitle(String title) throws SQLException {

		String searchWord = title;
		String sql = "SELECT * FROM histories INNER JOIN videos ON histories.youtube_video_id = videos.youtube_video_id WHERE title LIKE ?";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, "%" + searchWord + "%");
			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}

	}


	public List<History> ChannelRanking() throws SQLException {

		String sql = "SELECT  channel_name, youtube_channel_id, played_at, count(youtube_channel_id) AS COUNT\r\n" +
				"FROM histories\r\n" +
				"INNER JOIN videos ON histories.youtube_video_id = videos.youtube_video_id\r\n" +
				"GROUP BY youtube_channel_id\r\n" +
				"ORDER BY  count(youtube_channel_id) DESC";

		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			return executeSelectQuery3(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}


	private List<History> executeSelectQuery(ResultSet rs) throws SQLException {

		List<History> histories = new ArrayList<>();
		while (rs.next()) {
			History history =
					new History(rs.getInt("history_id"), rs.getString("youtube_video_id"), rs.getDate("played_at"), rs.getString("title"),rs.getString("youtube_channel_id"),rs.getString("channel_name"));

			histories.add(history);
		}
		rs.close();
		return histories;
	}

	private List<History> executeSelectQuery2(ResultSet rs) throws SQLException {

		List<History> histories = new ArrayList<>();
		while (rs.next()) {
			History history =
					new History(rs.getString("title"), rs.getString("youtube_video_id"),  rs.getString("channel_name"), rs.getInt("COUNT"));

			histories.add(history);
		}
		rs.close();
		return histories;
	}

	private List<History> executeSelectQuery3(ResultSet rs) throws SQLException {

		List<History> histories = new ArrayList<>();
		while (rs.next()) {
			History history =
					new History(rs.getString("channel_name"), rs.getString("youtube_channel_id"), rs.getInt("COUNT"));

			histories.add(history);
		}
		rs.close();
		return histories;
	}



}
