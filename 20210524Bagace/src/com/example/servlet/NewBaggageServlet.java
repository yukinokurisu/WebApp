package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.Baggage;
import com.example.dao.BaggageDao;

/**
 * Servlet implementation class NewBaggageServlet
 */
@WebServlet("/NewBaggageServlet")
public class NewBaggageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewBaggageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/new_baggage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//baggagesテーブルにデータを1行追加
		String trackingNumber = request.getParameter("tracking_number");
		Baggage baggage = new Baggage(trackingNumber, 1);
		try {
			//INSERTの処理
			new BaggageDao().create(baggage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//荷物一覧にリダイレクト
		final String CONTEXT_PATH = request.getContextPath();
		response.sendRedirect(CONTEXT_PATH + "/BaggagesServlet");

	}

}
