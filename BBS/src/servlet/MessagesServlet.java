package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Message;
import bean.User;
import dao.MessageDao;

/**
 * Servlet implementation class MessagesServlet
 */
@WebServlet("/messages")
public class MessagesServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
    * @see HttpServlet#HttpServlet()
    */
  public MessagesServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 文字コードの設定
    request.setCharacterEncoding("UTF-8");
    // ログイン有無のチェックのため、セッションスコープからユーザ情報を取得
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    // ログインチェック
    if (user == null) {
      // トップへ
      response.sendRedirect("/BBS/login");
      return;
    }
    // ログインしている場合

    /*
    // ひとことリストをアプリケーションスコープから取得
    ServletContext application = this.getServletContext();
    List<Message> messages = (ArrayList<Message>) application.getAttribute("messages");
    */

    List<Message> messages = null;

  //DAOを使ってSELECT文を実行
  		try {
  			//DAOはSELECTの結果をList<Message>で返す
  			messages = new MessageDao().findAll();

  			//返してくれたListをbaggages.jspに渡す
  			request.setAttribute("messages", messages);

  		} catch (SQLException e) {
  			e.printStackTrace();

			System.out.println("findAll()がnullになっています");
  		}

  	// DAOからひとことリストが取得できなかったとき
  	    if (messages == null) {
  	      // ひとことリストを新規作成
  	      messages = new ArrayList<Message>();
  	      //アプリケーションスコープに保存
  	      request.setAttribute("messages", messages);
  	    }

    // フォワード処理
  	    request.getRequestDispatcher("/WEB-INF/jsp/messages.jsp").forward(request, response);
  }

  /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 文字コードの設定
      request.setCharacterEncoding("UTF-8");

      //System.out.println(request.getParameter("buttonName"));

    if(request.getParameter("tweet")!=null) {//投稿ボタンが押されたとき

	    // ログイン有無のチェックのため、セッションスコープからユーザ情報を取得
	    HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");

	    // ログインチェック
	    if (user == null) {
	      // トップへ
	      response.sendRedirect("/BBS/login");
	      return;
	    }

	    // リクエストパラメータ「comment」から値を取得
	    String comment = request.getParameter("comment");

	    if (comment != null && comment.length() != 0) {
		     // 投稿された「ひとこと」をインスタンスとして生成
		     Message word = new Message(user.getUserName(), comment);
		     // 生成した「ひとこと」インスタンスを「ひとこと」リストの先頭に挿入
		     try {
				new MessageDao().create(word);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

	    } else {
	      // エラーメッセージをリクエストスコープに保存
	          request.setAttribute("errorMessage", "ひとこと発言してください。");
	    }
    }else if (request.getParameter("pushGood")!=null) {

    	 // リクエストパラメータ「message_id」から値を取得
    	String message_id_string = request.getParameter("message_id");
    	int message_id = Integer.valueOf(message_id_string);
    	//  DAOに値を渡してあげる
    	try {
			new MessageDao().good(message_id);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("goodに失敗");
			e.printStackTrace();
		}

    } else {
    	System.out.println("buttonNameを正しく入力してください");
    }

    doGet(request, response);



  }

}