package de.tum.in.dbpra.controller.provider;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SponsorProductsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3548548572339513246L;

	public SponsorProductsServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/SponsorProducts.jsp");
		dispatcher.forward(req, resp);
	}

}
