package dao;

import java.sql.Date;

public class History {

	private int history_id;
	private String youtube_video_id;
	private Date played_at;
	private String title;
	private String youtube_channel_id;
	private String channel_name;
	private int count;

	public History() {

	}

	public History(int history_id, String youtube_video_id, Date played_at, String title, String youtube_channel_id,
			String channel_name) {
		this.history_id = history_id;
		this.youtube_video_id = youtube_video_id;
		this.played_at = played_at;
		this.title = title;
		this.youtube_channel_id = youtube_channel_id;
		this.channel_name = channel_name;
		this.count=1;
	}

	public History(String title, String youtube_video_id, String channel_name, int count) {
		this.youtube_video_id = youtube_video_id;
		this.title = title;
		this.count=count;
		this.history_id = 0;
		this.played_at = null;
		this.youtube_channel_id = null;
		this.channel_name = channel_name;
	}

	public History(String channel_name,  String youtube_channel_id, int count) {
		this.youtube_video_id = null;
		this.title = null;
		this.count=count;
		this.history_id = 0;
		this.played_at = null;
		this.youtube_channel_id = youtube_channel_id;
		this.channel_name = channel_name;
	}

	public int getHistory_id() {
		return history_id;
	}

	public void setHistory_id(int history_id) {
		this.history_id = history_id;
	}

	public String getYoutube_video_id() {
		return youtube_video_id;
	}

	public void setYoutube_video_id(String youtube_video_id) {
		this.youtube_video_id = youtube_video_id;
	}

	public Date getPlayed_at() {
		return played_at;
	}

	public void setPlayed_at(Date played_at) {
		this.played_at = played_at;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYoutube_channel_id() {
		return youtube_channel_id;
	}

	public void setYoutube_channel_id(String youtube_channel_id) {
		this.youtube_channel_id = youtube_channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}



}
