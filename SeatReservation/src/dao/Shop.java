package dao;

public class Shop {

	private int shop_id;
	private String shop_name;
	private String password;

	public Shop() { }

	public Shop(String shop_name, String password) {
		this.shop_name = shop_name;
		this.setPassword(password);
	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}





}
