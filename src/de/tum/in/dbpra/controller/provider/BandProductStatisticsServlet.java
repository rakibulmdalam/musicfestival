package de.tum.in.dbpra.controller.provider;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BandProductStatisticsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -734752193143192719L;

	public BandProductStatisticsServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandProductStatistics.jsp");
		dispatcher.forward(req, resp);
	}

}
