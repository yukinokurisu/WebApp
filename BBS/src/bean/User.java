package bean;

import java.io.Serializable;

public class User implements Serializable {
  // フィールドの定義
  private static final long serialVersionUID = 1L;
  private String userName; //ユーザ名
  private String password; //パスワード

  // コンストラクタ（引数なし）
  public User(){ }
  // コンストラクタ（引数あり）
  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  //getterメソッド
  public String getUserName() {
    return this.userName;
  }
  public String getPassword() {
    return this.password;
  }
}