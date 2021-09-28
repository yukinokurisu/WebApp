package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.Baggage;
import com.example.dao.BaggageDao;

/**
 * Servlet implementation class FindBaggagesServlet
 */
@WebServlet("/FindBaggagesServlet")
public class FindBaggagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindBaggagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// request.getParameterで入力された伝票番号の文字列を受け取る
		String trackingNumber = request.getParameter("tracking_number");

		//部分一致で検索するSQL
		//findByTrackingNumberを呼ぶ
		try {
			//DAOはSELECTの結果をList<Baggage>で返す
			List<Baggage> findBaggages = new BaggageDao().findByTrackingNumber(trackingNumber);
			//返してくれたListをbaggages.jspに渡す
			request.setAttribute("findBaggages", findBaggages);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/jsp/find_baggages.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
