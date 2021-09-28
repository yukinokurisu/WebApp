package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Event;
import dao.Ticket;
import dao.TicketDao;

/**
 * Servlet implementation class TicketIssueServlet
 */
@WebServlet("/TicketIssueServlet")
public class TicketIssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TicketIssueServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/ticket_issue.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String event_idString = request.getParameter("event_id");
		int event_id = Integer.parseInt(event_idString);
		String capacityString = request.getParameter("capacity");
		int capacity = Integer.parseInt(capacityString);
		request.setAttribute("capacity", capacity);
		Date sold_at = null;

		// (発行)発行整理券枚数 < capacity
		// 発行枚数をえるDaoメソッドを実行
		int tickets = 0;
		try {
			tickets = new TicketDao().ticketCount(event_id);
			request.setAttribute("tickets", tickets);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (tickets < capacity) {

			try {
				Event event = new TicketDao().ticketIssue(event_id);
				request.setAttribute("event", event);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//整理券コードを生成するメソッドを実行
			Ticket ticket = new Ticket(null, null, null);
			String identification_code = ticket.createCode();

			//その整理券コードをDaoメソッドでDB登録
			Ticket ticket2 = new Ticket(event_id, identification_code, sold_at);
			try {
				new TicketDao().ticketCode(ticket2);
				request.setAttribute("ticket2", ticket2);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			// (発行しない) 発行整理券枚数 > capacity
		} else {
			request.setAttribute("IssueFailure", "定員に達しましたので発行できません。大変申し訳ございません。");
			doGet(request, response);
			return;
		}

		request.getRequestDispatcher("/WEB-INF/jsp/ticket_issue.jsp").forward(request, response);
	}

}
