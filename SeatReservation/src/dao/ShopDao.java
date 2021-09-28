package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDao {

	public Shop findAccount(Shop shop) throws SQLException {

		// 戻り値の用意
        Shop returnAb = new Shop();

		String sql = "SELECT * FROM shops WHERE shop_name = ? AND password = ? ;";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, shop.getShop_name());
			stmt.setString(2, shop.getPassword());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
                // 見つかったアカウント情報を戻り値にセット
                returnAb.setShop_name(rs.getString("shop_name"));
                returnAb.setPassword(rs.getString("password"));

            } else {
                // アカウントがなければnullを返す
                return null;
            }
		} catch (SQLException e) {
			throw e;
		}
		return returnAb;
	}

	public List<Shop> findAll() throws SQLException {

		// 戻り値の用意
        List<Shop> shops = new ArrayList<Shop>();

		String sql = "SELECT shop_name FROM shops;";
		try (Connection conn = DbConnectionFactory.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                // 見つかったアカウント情報を戻り値にセット
                Shop shop = new Shop(rs.getString("shop_name"), "pass");
                shops.add(shop);
            }
		} catch (SQLException e) {
			throw e;
		}
		return shops;
	}


}
