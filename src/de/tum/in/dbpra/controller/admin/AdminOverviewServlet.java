package de.tum.in.dbpra.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminOverviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711044000287770980L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/admin/notes");
	}

}
