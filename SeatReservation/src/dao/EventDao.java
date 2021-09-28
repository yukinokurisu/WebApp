package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventDao {


	//EventSearchで使う(使わなくなった)
	//タイトル名でイベント名を検索
	public List<Event> findEventByTitle(String title,String shop_name) throws SQLException {

		String sql = "SELECT * FROM events WHERE title LIKE \"%"+ title +"%\" AND shop_name LIKE \"" + shop_name + "\"";
		//AND shop_nameがセッションスコープからもらったshop_nameと同じものを
		//System.out.println(sql);
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}

	//イベント詳細で使う
	//イベントidでイベント名を検索
	public  Event findEventByEventId(int event_id) throws SQLException {

		String sql = "SELECT * FROM events WHERE event_id = "+ event_id +";";
		//AND shop_nameがセッションスコープからもらったshop_nameと同じものを
		//System.out.println(sql);
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			return executeSelectQuery2(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}

	//EventSearchで使う
	//入力された値をSQLに入る形にただす。
//	public void correctFormOfSQL(String start_at_min,String start_at_max) {
//
//	}


	//EventSearchで使う
	//タイトルと日付から検索
	public List<Event> findEventByDateAndTitle(String start_at_min,String start_at_max, String title,String shop_name) throws SQLException {

		//String sql = "SELECT * FROM events WHERE title LIKE \"%"+ title +"%\" AND shop_name LIKE \"" + shop_name + "\"";
		//AND shop_nameがセッションスコープからもらったshop_nameと同じものを
		//SELECT * FROM `events` WHERE `start_at` BETWEEN (SELECT MIN(`start_at`) FROM events) AND (SELECT MAX(`start_at`) FROM events)
		//本番用SQL↓
		String sql  = "SELECT * FROM events WHERE title LIKE \"%"+ title +"%\" "
				+ "AND shop_name LIKE \"" + shop_name + "\""
				+ " AND start_at BETWEEN " + start_at_min + " AND " + start_at_max;
		//本番用SQL↑

		//実行確認用：ショップIDなしのSQL
//		String sql = "SELECT * FROM events WHERE title LIKE \"%"+ title +"%\" "
//				+ " AND start_at BETWEEN " + start_at_min + " AND " + start_at_max;


		//System.out.println(sql);
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}


	//EventListで使うひな形 voidがだめならListに戻す
	//イベントを削除する
	public void deleteEventAndTickets(String event_id) throws SQLException {

		//↓2文まとめて実行したかったけどできなかった。
//		String sql = "DELETE FROM tickets WHERE event_id = " + event_id +
//					"; DELETE FROM events WHERE event_id = " + event_id + ";";

		String sql_tickets = "DELETE FROM tickets WHERE event_id = " + event_id + ";";
		String sql_events = "DELETE FROM events WHERE event_id = " + event_id + ";";

		//System.out.println(sql_tickets + sql_events);

		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql_tickets);){

			stmt.execute();
			//return;
		} catch (SQLException e) {
			//System.out.println("throwしてますtickets");
			throw e;
		}

		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql_events);){

			stmt.execute();
			//stmt.executeUpdate();
			return;

		} catch (SQLException e) {
			//System.out.println("throwしてますevents");
			throw e;
		}


	}

	//EventListで使うひな形
	//EndedEventListで使うひな形
	//開催済みイベントの取得
	public List<Event> findEndedEvent(String shop_name) throws SQLException {

		//現在時刻取得
		LocalDateTime nowDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


		//現在時刻以前のイベントを取得
		String sql = "SELECT * FROM events WHERE start_at < DATE('" + formatter.format(nowDateTime) + "') AND shop_name LIKE \"" + shop_name + "\"";

		//エラーチェック用↓
		//String sql = "SELECT * FROM events WHERE start_at < DATE('" + formatter.format(nowDateTime) + "')";
		//System.out.println(sql);
		//エラーチェック用↑

		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}


	//EventListで使うひな形
	//未開催のイベントの取得
	public List<Event> findUpcomingEvent(String shop_name) throws SQLException {

		//現在時刻取得
		LocalDateTime nowDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		//現在時刻以降のイベントを取得
		String sql = "SELECT * FROM events WHERE start_at >= DATE('" + formatter.format(nowDateTime) + "') AND shop_name LIKE \"" + shop_name + "\"";
		//↓エラーチェック用↓
		//String sql = "SELECT * FROM events WHERE shop_name LIKE \"" + shop_name + "\"";
		//String sql = "SELECT * FROM events WHERE start_at >= DATE('" + formatter.format(nowDateTime) + "')";
		//System.out.println(sql);
		//エラーチェック用↑
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}

	//EventListで使うひな形
	public List<Event> findAll(String shop_name) throws SQLException {
		//現在時刻以降のイベントを取得
		String sql = "SELECT * FROM events WHERE shop_name LIKE \"" + shop_name + "\"";
		//↓エラーチェック用↓
		//String sql = "SELECT * FROM events";
		//String sql = "SELECT * FROM events WHERE start_at >= DATE('" + formatter.format(nowDateTime) + "')";
		//System.out.println(sql);

		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}


	//NewEventで使う
	public void create(Event event) throws SQLException {
		String sql = "INSERT INTO events" +
				" VALUES (null, ?, ?, ?, ?, ?);";
		System.out.println(event.getShop_name());
		System.out.println(event.getTitle() + " :タイトル3");
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, event.getShop_name());
			stmt.setString(2, event.getTitle());
			stmt.setInt(3, event.getCapacity());
			stmt.setString(4, event.getDescription());
			stmt.setDate(5, event.getStart_at());
			stmt.execute();

			return ;
		} catch (SQLException e) {
			throw e;
		}
	}

	// EventUpdate
	public void update(Event event) throws SQLException {
		String sql = "UPDATE events SET title=?, capacity=?, description=?, start_at=? WHERE event_id=?;";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, event.getTitle());
			stmt.setInt(2, event.getCapacity());
			stmt.setString(3, event.getDescription());
			stmt.setDate(4, event.getStart_at());
			stmt.setInt(5, event.getEvent_id());
			stmt.execute();

			return ;
		} catch (SQLException e) {
			throw e;
		}
	}


	private List<Event> executeSelectQuery(ResultSet rs) throws SQLException {

		List<Event> events = new ArrayList<Event>();
		while (rs.next()) {
			Event event =
					new Event( rs.getInt("event_id"), rs.getString("shop_name"), rs.getString("title"),
							rs.getInt("capacity"), rs.getString("description"),rs.getDate("start_at"));

			events.add(event);
		}
		rs.close();
		return events;
	}

	private Event executeSelectQuery2(ResultSet rs) throws SQLException {

		Event event=null;
		while (rs.next()) {
			event =
					new Event( rs.getInt("event_id"), rs.getString("shop_name"), rs.getString("title"),
							rs.getInt("capacity"), rs.getString("description"),rs.getDate("start_at"));
		}
		rs.close();
		return event;
	}


}