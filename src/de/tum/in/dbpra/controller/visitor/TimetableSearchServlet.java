package de.tum.in.dbpra.controller.visitor;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bo.SearchSchedules;
import de.tum.in.dbpra.model.bo.SearchType;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;

public class TimetableSearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4505465161708807960L;

	public TimetableSearchServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SearchSchedules search;
		SearchType type;
		String reqSearchType = req.getParameter("type");
		String reqQuery = req.getParameter("query");
		try {
			if (reqQuery == null || reqQuery.trim().isEmpty()) {
				type = SearchType.ALL;
			} else {
				type = SearchType.valueOf(reqSearchType.toUpperCase());
			}
			search = new SearchSchedules(reqQuery, type);
			req.setAttribute("schedules", search.getSchedules());
			req.setAttribute("dates", search.getScheduleDates());
		} catch (ClassNotFoundException | SQLException | SearchQueryException e) {
			e.printStackTrace();
			req.setAttribute("schedules", null);
			req.setAttribute("dates", null);
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/TimetableSearch.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
