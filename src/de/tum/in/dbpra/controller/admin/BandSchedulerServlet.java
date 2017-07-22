package de.tum.in.dbpra.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bo.SearchSchedules;
import de.tum.in.dbpra.model.bo.SearchType;
import de.tum.in.dbpra.model.dao.SchedulesDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;

public class BandSchedulerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5044942860830593031L;

	public BandSchedulerServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SearchSchedules search;
		try {
			search = new SearchSchedules("", SearchType.ALL);
			req.setAttribute("schedules", search.getSchedules());
			req.setAttribute("dates", search.getScheduleDates());
		} catch (ClassNotFoundException | SQLException | SearchQueryException e) {
			e.printStackTrace();
			req.setAttribute("schedules", null);
			req.setAttribute("dates", null);
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandScheduler.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
