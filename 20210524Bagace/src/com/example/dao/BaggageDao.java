package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaggageDao {

	public List<Baggage> findAll() throws SQLException {

		String sql = "SELECT * FROM baggages INNER JOIN baggage_statuses ON baggage_statuses.baggage_status_id = baggages.baggage_status_id;";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}
	}

	// このDAOに新しくメソッドを作る
	// 伝票番号を部分一致で検索する
	public List<Baggage> findByTrackingNumber(String trackingNumber) throws SQLException {

		String sql = "SELECT * FROM baggages INNER JOIN baggage_statuses ON baggage_statuses.baggage_status_id = baggages.baggage_status_id WHERE tracking_number like \"%"
				+ trackingNumber
				+ "%\";";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			return executeSelectQuery(stmt.executeQuery());
		} catch (SQLException e) {
			throw e;
		}

	}

	public void create(Baggage baggage) throws SQLException {
		String sql = "INSERT INTO baggages" +
				" VALUES (null, ?, ?);";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, baggage.getTrackingNumber());
			stmt.setInt(2, baggage.getBaggageStatusId());
			stmt.execute();

			return ;
		} catch (SQLException e) {
			throw e;
		}
	}

	public void update(String idString, String statusString) throws SQLException {

		int id = Integer.parseInt(idString);
		int status = Integer.parseInt(statusString);

		String sql = "UPDATE baggages SET baggage_status_id = "
		+ status
		+ " WHERE baggage_id = "
		+ id
		+";";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.execute();
			return ;
		} catch (SQLException e) {
			throw e;
		}
	}

	private List<Baggage> executeSelectQuery(ResultSet rs) throws SQLException {

		List<Baggage> baggages = new ArrayList<>();
		while (rs.next()) {
			Baggage baggage =
					new Baggage(rs.getInt("baggage_id"), rs.getString("tracking_number"), rs.getInt("baggage_status_id"), rs.getString("status"));

			baggages.add(baggage);
		}
		rs.close();
		return baggages;
	}


}