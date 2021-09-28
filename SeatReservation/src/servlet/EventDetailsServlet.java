package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Event;
import dao.EventDao;
import dao.TicketDao;

/**
 * Servlet implementation class EventDetailServlet
 */
@WebServlet("/EventDetailsServlet")
public class EventDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventDetailsServlet() {
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

		// ボタンの非表示条件のため
		try {
			int tickets = new TicketDao().ticketCount(event_id);
			request.setAttribute("tickets", tickets);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("DAO失敗");
		}

		try {
			event = new EventDao().findEventByEventId(event_id);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("イベント詳細サーブレット : イベント読み込み失敗");
		}
		request.setAttribute("event", event);
		request.getRequestDispatcher("WEB-INF/jsp/event_details.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//イベント削除
		String deleteEvent = request.getParameter("deleteEvent");
		String event_id = request.getParameter("event_id");
		String start_at = request.getParameter("start_at");
		int start_at_int = Integer.parseInt(start_at.replace("-", ""));

		int event_id_int = Integer.parseInt(event_id);
		int tickets = 0;

		try {
			tickets = new TicketDao().ticketCount(event_id_int);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		//イベント削除:整理券の有無の条件をいれる
		//[deleteEvent != null]は削除ボタンが押されたことを示す。
		if (deleteEvent != null) {

			LocalDateTime nowDateTime_date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String nowDateTime_str = formatter.format(nowDateTime_date);
			int nowDateTime_int = Integer.parseInt(nowDateTime_str.replace("-", ""));

			if (nowDateTime_int < start_at_int) {
				//未開催のイベント
				if (tickets > 0) {
					//整理券ありの場合
					request.setAttribute("deleteEventError", "整理券をすべて削除してからイベントを削除してください");
					doGet(request, response);
				} else {
					//整理券無しの場合
					try {
						new EventDao().deleteEventAndTickets(event_id);
						request.getRequestDispatcher("/EventListServlet").forward(request, response);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} else {
				//開催済みイベント
				try {
					new EventDao().deleteEventAndTickets(event_id);
					request.getRequestDispatcher("/EventListServlet").forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}

		doGet(request, response);
	}

}
