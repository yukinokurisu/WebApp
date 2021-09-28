package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Shop;
import dao.ShopDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shop_name = request.getParameter("shop_name");
		String password = request.getParameter("password");

		try {
			Shop shop = new Shop();
			shop.setShop_name(shop_name);
			shop.setPassword(password);

			ShopDao shopDao = new ShopDao();
			Shop returnshop = shopDao.findAccount(shop);

			if (returnshop != null) {

				HttpSession session = request.getSession();
				//session.setAttribute("shop", new Shop(shop_id, shop_name));
				session.setAttribute("shop_name", returnshop.getShop_name());

				response.sendRedirect("/SeatReservationSystem/TopServlet");

			} else if (shop.equals(returnshop) == false) {
				request.setAttribute("loginFailure", "ログインに失敗しました。再度、店舗名とパスワードを入力してください。");
				doGet(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
