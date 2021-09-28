package dao;

import java.sql.Date;

public class Event {
	private int event_id;
	private String shop_name;
	private String title;
	private int capacity;
	private String description;
	private Date start_at;


	//全部のコンストラクタ
	public Event(int event_id, String shop_name, String title, int capacity, String description, Date start_at) {
		this.event_id = event_id;
		this.shop_name = shop_name;
		this.title = title;
		this.capacity = capacity;
		this.description = description;
		this.start_at = start_at;
	}

	//NewEventで使う
	public Event(String shop_name, String title, int capacity , String description, Date start_at) {
		this.event_id = 0; //0のイベントは存在しないことにしたい
		this.title = title;
		this.shop_name = shop_name;
		this.description = description;
		this.start_at = start_at;
		this.capacity = capacity;
	}

	//TicketIssue
	public Event(String title,Date start_at) {
		this.title = title;
		this.start_at = start_at;
	}



	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public java.sql.Date getStart_at() {
		return start_at;
	}
	public void setStart_at(Date start_at) {
		this.start_at = start_at;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}



}
