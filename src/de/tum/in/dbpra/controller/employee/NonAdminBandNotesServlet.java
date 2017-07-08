package de.tum.in.dbpra.controller.employee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NonAdminBandNotesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -164241206191678240L;

	public NonAdminBandNotesServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/NonAdminBandNotes.jsp");
		dispatcher.forward(req, resp);
	}

}
