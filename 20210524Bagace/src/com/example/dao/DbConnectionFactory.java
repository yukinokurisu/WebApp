package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionFactory {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 自動import
	// Ctrl + Shift + O
	// command + shift + O
	public static Connection connect() throws SQLException {
		Connection conn = null;

		String user = "root";
		String schemaName = "20210524_training"; // ここにご自身のデータベースの名前を入力してください

		// Windows版XAMPP初期設定
		String url = "jdbc:mysql://localhost:3306/" + schemaName + "?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Tokyo";
		String password = "";

		// Mac版MAMP初期設定
		// String url = "jdbc:mysql://localhost:8889/" + schemaName + "?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Tokyo";
		// String password = "root";

		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}