package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Event;
import dao.EventDao;

/**
 * Servlet implementation class EventSearchServlet
 */
@WebServlet("/EventSearchServlet")
public class EventSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");

		HttpSession session = request.getSession();
		String shop_name = (String) session.getAttribute("shop_name");
		String start_at_min = request.getParameter("start_at_min");
		String start_at_max = request.getParameter("start_at_max");
		String title = request.getParameter("title");
		Pattern pattern = Pattern.compile("^[0-9]{4}-[0-1]{1}[0-9]{1}-[0-3]{1}[0-9]{1}$");

		//イベント一覧画面を開いたときにtitleの値がnullにならないようにするためのもの。
		if (title == null) {
			title = "";
		}

		//日付がnullか空の時に最古の日付を入れる。
		if (start_at_min == null || start_at_min == "") {
			start_at_min = "(SELECT MIN(`start_at`) FROM events)";
		}
		//日付がnullか空の時に最新の日付を入れる。
		if (start_at_max == null || start_at_max == "") {
			start_at_max = "(SELECT MAX(`start_at`) FROM events)";
		}

		//正しい入力の場合にDATE(日付)の形にしてあげる。
		//正しくない場合は、「正しく入力してください」を表示させる
		if(start_at_min != "(SELECT MIN(`start_at`) FROM events)" ) {
			Matcher matcher_min = pattern.matcher(start_at_min);

			if (matcher_min.matches() == false) {
				request.setAttribute("dateSearchError", "正しく入力してください");
			}
			start_at_min = "DATE('" + start_at_min + "')";
		}

		if(start_at_max != "(SELECT MAX(`start_at`) FROM events)" ) {
			Matcher matcher_max = pattern.matcher(start_at_max);
			if (matcher_max.matches() == false) {
				request.setAttribute("dateSearchError", "正しく入力してください");
			}
			start_at_max = "DATE('" + start_at_max + "')";
		}

		try {
			List<Event> events = new EventDao().findEventByDateAndTitle(start_at_min, start_at_max, title, shop_name);

			request.setAttribute("events", events);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/jsp/event_search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);



	}

}
