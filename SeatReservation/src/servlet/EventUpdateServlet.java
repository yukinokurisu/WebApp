package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Event;
import dao.EventDao;

/**
 * Servlet implementation class EventUpdateServlet
 */
@WebServlet("/EventUpdateServlet")
public class EventUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int event_id = Integer.parseInt(request.getParameter("event_id"));
		Event event = null;

		try {
			event = new EventDao().findEventByEventId(event_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("event", event);

		request.getRequestDispatcher("WEB-INF/jsp/event_update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int event_id = Integer.parseInt(request.getParameter("event_id"));
		String title = request.getParameter("title");
		String shop_name = request.getParameter("shop_name");
		String description = request.getParameter("description");
		String start_at_string = request.getParameter("start_at");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date start_at = null;
		dateFormat.setLenient(false);

		int capacity = 0;

		try {
			capacity = Integer.parseInt(request.getParameter("capacity"));

		} catch (Exception e) {
			request.setAttribute("capacityError", "不正な数値入力。半角数字を入力してください。");

			try {
				start_at = dateFormat.parse(start_at_string);
			} catch (ParseException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			java.sql.Date start_at_sql = new java.sql.Date(start_at.getTime());
			Event event = new Event(event_id, shop_name, title, capacity, description, start_at_sql);
			request.setAttribute("event", event);

			request.getRequestDispatcher("WEB-INF/jsp/event_update.jsp").forward(request, response);

		}

		try {
			start_at = dateFormat.parse(start_at_string);

		} catch (ParseException e1) {
			e1.printStackTrace();
			request.setAttribute("dateError", "不正な日付入力。例を参照にして入力してください。");

			try {
				//日付不正入力のためサンプルの日付をとりあえず表示する
				String sampledate = "2021-01-01";
				start_at = (Date) dateFormat.parse(sampledate);
				java.sql.Date start_at_sql = new java.sql.Date(start_at.getTime());
				Event event = new Event(event_id, shop_name, title, capacity, description, start_at_sql);
				request.setAttribute("event", event);

				request.getRequestDispatcher("WEB-INF/jsp/event_update.jsp").forward(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		java.sql.Date start_at_sql = new java.sql.Date(start_at.getTime());

		if (start_at != null) {
			Event event = new Event(event_id, shop_name, title, capacity, description, start_at_sql);

			try {
				//UPDATEの処理
				new EventDao().update(event);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//イベント一覧にリダイレクト
			final String CONTEXT_PATH = request.getContextPath();
			response.sendRedirect(CONTEXT_PATH + "/EventListServlet");
		}

	}

}
