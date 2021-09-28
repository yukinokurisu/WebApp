package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Ticket;
import dao.TicketDao;

/**
 * Servlet implementation class TicketsListServlet
 */
@WebServlet("/TicketsListServlet")
public class TicketsListServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TicketsListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String event_idString = request.getParameter("event_id");
		int event_id = Integer.parseInt(event_idString);

		try {
			List<Ticket> tickets = new TicketDao().findByEventID(event_id);
			request.setAttribute("tickets", tickets);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/jsp/ticket_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字コード変換
		request.setCharacterEncoding("UTF-8");

		String event_id = request.getParameter("event_id");
		request.setAttribute("event_id", event_id);

		if (request.getParameter("cancell_issue") != null) {
			// jspから受け取る
			String identification_code = request.getParameter("identification_code");
			try {//チケットを取り消すDAOを実行する

				new TicketDao().DeleteByIdentificationCode(identification_code);
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("消去SQL失敗");
			}
		}
		request.setAttribute("cancell_issue", null); //ここいるか微妙

		doGet(request, response);
	}

}
