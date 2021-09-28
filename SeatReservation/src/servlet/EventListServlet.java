package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Event;
import dao.EventDao;
import dao.TicketDao;

/**
 * Servlet implementation class EventList
 */
@WebServlet("/EventListServlet")
public class EventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//文字化け防止
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chaeset=UTF-8");

		HttpSession session = request.getSession();
		String shop_name = (String) session.getAttribute("shop_name");


		//開催未開催切り替え機能：  end,endNotを変数に、getParameterを引数に。tryの中で条件分岐。
		//イベントの開催か否か判定
		if (request.getParameter("heldNot") != null) {
			//未開催を表示
			try {
				List<Event>  events = new EventDao().findUpcomingEvent(shop_name);
				request.setAttribute("events", events);
				request.setAttribute("heldNot", "heldNot");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if ( request.getParameter("held") != null) {
			//開催済を表示
			try {
				List<Event>  events = new EventDao().findEndedEvent(shop_name);
				request.setAttribute("events", events);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			//開催したか否か問わず表示
			try {
				List<Event>  events = new EventDao().findAll(shop_name);
				request.setAttribute("events", events);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("WEB-INF/jsp/event_list.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");


		String deleteEvent = request.getParameter("deleteEvent");
		String event_id = request.getParameter("event_id");
		String start_at = request.getParameter("start_at");
		int start_at_int = Integer.parseInt(start_at.replace("-", ""));


		int event_id_int = Integer.parseInt(event_id);
		int tickets = 0;


		//整理券の数を入れる変数tikets
		try {
			tickets = new TicketDao().ticketCount(event_id_int);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}


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
				}else {
					//整理券無しの場合
					try {
						new EventDao().deleteEventAndTickets(event_id);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}else {
				//開催済みイベント
				try {
					new EventDao().deleteEventAndTickets(event_id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		doGet(request, response);
	}

}
