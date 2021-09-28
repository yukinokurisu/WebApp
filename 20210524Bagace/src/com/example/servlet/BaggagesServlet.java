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
 * Servlet implementation class BaggagesServlet
 */
@WebServlet("/BaggagesServlet")
public class BaggagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaggagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//DAOを使ってSELECT文を実行
		try {
			//DAOはSELECTの結果をList<Baggage>で返す
			List<Baggage> baggages = new BaggageDao().findAll();

			//返してくれたListをbaggages.jspに渡す
			request.setAttribute("baggages", baggages);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/jsp/baggages.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getParameterで
		//baggage_status_idは何番にしたい？
		//baggage_idは何番？
		String statusString = request.getParameter("baggage_status_id");
		String idString = request.getParameter("baggage_id");

		//ブラウザで送られてきた情報をもとにUPDATE文を実行
		//DAOにUPDATEするメソッドを書く
		//呼び出し
		try {
			new BaggageDao().update(idString, statusString);
		} catch (SQLException e) {
			e.printStackTrace();
		}


		//BaggageServletにリダイレクト
		final String CONTEXT_PATH = request.getContextPath();
		response.sendRedirect(CONTEXT_PATH + "/BaggagesServlet");

	}

}
