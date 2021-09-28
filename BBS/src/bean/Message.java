package bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {
  // フィールドの定義
  private static final long serialVersionUID = 1L;
  private int messageid;
  private String userName; //ユーザ名
  private String comment; //ひとこと
  private String date; //発言日時
  private int good;

  // コンストラクタ（引数なし）
  public Message() { }
  // コンストラクタ（引数あり）
  public Message(String userName, String comment){
    this.userName = userName;
    this.comment = comment;
    this.date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    this.good = 0;
    this.messageid =0;
  }

  public Message(int messageId, String userName, String comment,String date, int good){
	    this.userName = userName;
	    this.comment = comment;
	    this.date = date;
	    this.good = good;
	    this.messageid = messageId;
  }

  //getterメソッド
  public int getMessageID() {
	    return messageid;
	  }
  public String getUserName() {
    return userName;
  }
  public String getComment() {
    return comment;
  }
  public String getDate() {
    return date;
  }
  public int getGood() {
	    return good;
  }
  public void setGood() {
		this.good++;
	}
}