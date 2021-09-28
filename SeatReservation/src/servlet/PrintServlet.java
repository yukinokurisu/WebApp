package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Ticket;
import dao.TicketDao;

/**
 * Servlet implementation class PrintServlet
 */
@WebServlet("/PrintServlet")
public class PrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrintServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String identification_code = request.getParameter("identification_code");

		try {
			Ticket ticket = new TicketDao().PrintFind(identification_code);
			request.setAttribute("ticket", ticket);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//		System.out.println(request.getSession().getServletContext().getRealPath(""));

		String path = this.getServletContext().getRealPath("/img/qrcode.png");

		//		System.out.println(path);

		Ticket ticket = new Ticket(null, identification_code, null);
		String imag = ticket.QRcode(identification_code, path);
		//		System.out.println(imag);
		request.setAttribute("path", imag);

		request.getRequestDispatcher("WEB-INF/jsp/print.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
