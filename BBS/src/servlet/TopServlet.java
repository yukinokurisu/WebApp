package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/top")
public class TopServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

    /**
      * @see HttpServlet#HttpServlet()
      */
    public TopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 文字コードの設定
      request.setCharacterEncoding("UTF-8");
      // フォワード処理
      request.getRequestDispatcher("/WEB-INF/jsp/top.jsp").forward(request, response);
  }

  /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}