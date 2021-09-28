package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Event;
import dao.EventDao;
import dao.Shop;
import dao.ShopDao;

/**
 * Servlet implementation class NewEventServlt
 */
@WebServlet("/NewEventServlet")
public class NewEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewEventServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Shop> shops = null;
		try {
			shops = new ShopDao().findAll();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			//System.out.println("ShopDAO呼び出し失敗");
		}
		request.setAttribute("shops", shops);
		request.getRequestDispatcher("/WEB-INF/jsp/new_event.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Eventsテーブルにデータを1行追加

		//文字コード変換
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		int capacity = 0;

		try {
			capacity = Integer.parseInt(request.getParameter("capacity"));
		} catch (Exception e) {
			request.setAttribute("capacityError", "不正な数値入力");
//			request.setAttribute("capacity", "0");
			doGet(request, response);
			return;
		}

		String shop_name = request.getParameter("shop_name");
		String description = request.getParameter("description");
		String start_at_string = request.getParameter("start_at");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date start_at = null;
		dateFormat.setLenient(false);

		try {
			start_at = dateFormat.parse(start_at_string);
		} catch (ParseException e1) {
			e1.printStackTrace();
			request.setAttribute("dateError", "不正な日付入力");
			doGet(request, response);
			return;
		}

		java.sql.Date start_at_sql = new java.sql.Date(start_at.getTime());

		if (start_at != null) {
			Event event = new Event(shop_name, title, capacity, description, start_at_sql);
			try {
				//イベントの追加
				new EventDao().create(event);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//イベント一覧にリダイレクト
		final String CONTEXT_PATH = request.getContextPath();
		response.sendRedirect(CONTEXT_PATH + "/EventListServlet");
	}

}
