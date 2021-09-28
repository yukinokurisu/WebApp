package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.QRReader;
import dao.Ticket;
import dao.TicketDao;

/**
 * Servlet implementation class TicketCheckServlet
 */
@WebServlet("/TicketCheckServlet")
@MultipartConfig
public class TicketCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TicketCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字コードの設定
		// フォワード処理
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/WEB-INF/jsp/ticket_check.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String identification_code;
		// リクエストパラメータの文字コードをUTF-8に設定
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("identification_code") != null) {
			// リクエストパラメータの取得
			identification_code = request.getParameter("identification_code");

			if (identification_code.equals("")) { //入力フォームに何も入力されずにボタンが押されたとき
				request.setAttribute("loginFailure", "コードを入力してください");
				doGet(request, response);
				return;
			}

		} else {//追加部分
			request.setCharacterEncoding("utf-8");
			//name属性がpictのファイルをPartオブジェクトとして取得
			Part part = request.getPart("pict");

			//ファイル名の取得
			try {
				String name = this.getFileName(part);
				part.write(getServletContext().getRealPath("/upload") + "/" + name);
				QRReader QR = new QRReader();
				identification_code = QR.Read(getServletContext().getRealPath("/upload") + "/" + name);
			} catch (Exception e) {
				request.setAttribute("fileError", "正しいファイルを選んでください");
				doGet(request, response);
				return;
			}
		}

		Ticket ticket = null;

		//shop_nameをseesionから受け取る
		HttpSession session = request.getSession();
		String shop_name = (String) session.getAttribute("shop_name");

		try {
			ticket = new TicketDao().findByIdentificationCode(identification_code, shop_name);
		} catch (SQLException e) {
			System.out.println("Dao呼び出し失敗");
			e.printStackTrace();
		}
		// ログイン処理
		// ログイン失敗時のメッセージをリクエストスコープに保存
		if (ticket == null) {
			request.setAttribute("loginFailure", "認証失敗");
			doGet(request, response);
			return;
		} else if (ticket.getPunched_at() != null) {
			request.setAttribute("loginFailure", "使用済み");
			doGet(request, response);
			return;
		} else {
			request.setAttribute("loginFailure", "認証成功");
			try {
				new TicketDao().update(ticket);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("アップデート失敗");
			}
			doGet(request, response);
			return;
		}

	}

	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}

}
