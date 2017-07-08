package de.tum.in.dbpra.controller.employee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiftsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3133775626354469167L;

	public ShiftsServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Shifts.jsp");
		dispatcher.forward(req, resp);
	}
}
