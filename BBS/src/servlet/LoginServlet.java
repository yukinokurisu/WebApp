package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public LoginServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 文字コードの設定
    request.setCharacterEncoding("UTF-8");
    // フォワード処理
    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // リクエストパラメータの文字コードをUTF-8に設定
    request.setCharacterEncoding("UTF-8");
    // リクエストパラメータの取得
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");

    final String LOGIN_PASSWORD = "pass";

    // ログイン処理
    if( password.equals(LOGIN_PASSWORD) == false ) {
      // ログイン失敗時のメッセージをリクエストスコープに保存
      request.setAttribute("loginFailure", "ログインに失敗しました。もう一度入力してください。");
      doGet(request, response);
      return;
    }

    // ユーザ情報をセッションスコープに保存
    HttpSession session = request.getSession();
    session.setAttribute("user", new User(userName,password));
    // トップへ
    response.sendRedirect("/BBS/top");
  }
}