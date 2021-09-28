package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

	public Ticket findByIdentificationCode (String identification_code, String shop_name) throws SQLException {

		String sql = "SELECT tickets.event_id, identification_code, sold_at, punched_at FROM tickets "
				+ "INNER JOIN events ON tickets.event_id = events.event_id "
				+ "WHERE identification_code = \""+ identification_code	+ "\" "
				+ "AND events.shop_name = \""
				+ shop_name
				+ "\" AND events.start_at = CURDATE();";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}

	}

	public void DeleteByIdentificationCode (String identification_code) throws SQLException {
		//IdentificationCodeを受け取って一致したチケットを消す
		//System.out.println(identification_code + ":DAO");
		String sql = "DELETE FROM tickets WHERE identification_code = \""
				+ identification_code
				+ "\";";
		//System.out.println(sql);
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.execute();
			return ;
		} catch (SQLException e) {
			throw e;
		}

	}

	public Ticket PrintFind (String identification_code) throws SQLException {

		String sql = "SELECT identification_code, title, start_at FROM events INNER JOIN tickets ON events.event_id = tickets.event_id WHERE identification_code = \""
				+ identification_code
				+ "\";";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			return executeSelectQuery4(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}

	}

	public void create(Ticket ticket) throws SQLException {

		String sql = "INSERT INTO tickets" +
				" VALUES (?, ?, ?, ?);";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, ticket.getEvent_id());
			stmt.setString(2, ticket.getIdentification_code());
			stmt.setDate(3, ticket.getSold_at());
			stmt.setDate(4, ticket.getPunched_at());
			stmt.execute();

			return ;
		} catch (SQLException e) {
			throw e;
		}

	}

	public void update(Ticket ticket) throws SQLException {


		String sql = "UPDATE tickets SET punched_at = Now() "
		+ " WHERE identification_code = \""
		+ ticket.getIdentification_code()
		+"\";";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.execute();
			return ;
		} catch (SQLException e) {
			throw e;
		}
	}

	public Event ticketIssue(int event_id) throws SQLException {

		String sql = "SELECT title, start_at FROM events WHERE events.event_id = \"" + event_id + "\";";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			return executeSelectQuery2(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}

	public void ticketCode(Ticket ticket2) throws SQLException {

		String sql = "INSERT INTO tickets(event_id, identification_code, sold_at)" +
				" VALUES (?, ?, Now()) ;";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, ticket2.getEvent_id());
			stmt.setString(2, ticket2.getIdentification_code());
			stmt.execute();
			return ;
		} catch (SQLException e) {
			throw e;
		}

	}
	public List<Ticket>  findByEventID(int event_id) throws SQLException {
		String sql = "SELECT * FROM tickets WHERE event_id = "
				+ event_id
				+ ";";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			return executeSelectQuery3(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}

	public int ticketCount(int event_id) throws SQLException {

		String sql = "SELECT COUNT(*) FROM tickets WHERE event_id = \"" + event_id + "\";";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			return executeSelectQuery5(stmt.executeQuery());

		} catch (SQLException e) {
			throw e;
		}
	}


	private Ticket executeSelectQuery(ResultSet rs) throws SQLException {

		Ticket ticket = null;
		while (rs.next()) {
			ticket =
					new Ticket( rs.getInt("event_id"),	rs.getString("identification_code"),
							rs.getDate("sold_at"), rs.getDate("punched_at"));
		}
		rs.close();
		return ticket;
	}

	private List<Ticket> executeSelectQuery3(ResultSet rs) throws SQLException {

		 List<Ticket> tickets = new ArrayList<Ticket>();
		while (rs.next()) {
			Ticket ticket =
					new Ticket( rs.getInt("event_id"),	rs.getString("identification_code"),
							rs.getDate("sold_at"), rs.getDate("punched_at"));
			tickets.add(ticket);
		}
		rs.close();
		return tickets;
	}

	private Event executeSelectQuery2(ResultSet rs) throws SQLException {

		Event event = null;
		while (rs.next()) {
			event =
					new Event( rs.getString("title"),	rs.getDate("start_at"));
		}
		rs.close();
		return event;
	}


	private int executeSelectQuery5(ResultSet rs) throws SQLException {

		int tickets = 0;
		while (rs.next()) {
			tickets = rs.getInt("COUNT(*)");
		}
		rs.close();
		return tickets;
	}

	private Ticket executeSelectQuery4(ResultSet rs) throws SQLException {

		Ticket ticket = null;
		while (rs.next()) {
			ticket =
					new Ticket( rs.getString("identification_code"),
							rs.getString("title"), rs.getDate("start_at"));
		}
		rs.close();
		return ticket;

	}

}
