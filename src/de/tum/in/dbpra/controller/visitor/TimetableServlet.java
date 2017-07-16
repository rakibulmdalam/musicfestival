package de.tum.in.dbpra.controller.visitor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.VisitorBean;
import de.tum.in.dbpra.model.dao.TimetableDAO;
import de.tum.in.dbpra.model.dao.TimetableDAO.EmptyTimetableException;

public class TimetableServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4037981938350066552L;

	public TimetableServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TimetableDAO dao = new TimetableDAO();
		VisitorBean visitor = new VisitorBean();
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			id = 1;
		}
		visitor.setUserID(id);
		try {
			dao.getTimetable(visitor);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (EmptyTimetableException e) {
			e.printStackTrace();
		}
		TreeSet<String> scheduleDays = new TreeSet<String>(visitor.getTimetable()
																  .stream()
																  .map(ScheduleBean::getDateWithoutTime)
																  .collect(Collectors.toSet()));
		req.setAttribute("scheduleDays", scheduleDays);
		req.setAttribute("visitor", visitor);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Timetable.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
