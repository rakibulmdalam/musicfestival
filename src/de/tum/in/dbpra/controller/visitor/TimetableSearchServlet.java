package de.tum.in.dbpra.controller.visitor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.Role;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.bean.VisitorBean;
import de.tum.in.dbpra.model.bo.SearchSchedules;
import de.tum.in.dbpra.model.bo.SearchType;
import de.tum.in.dbpra.model.dao.TimetableDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;
import de.tum.in.dbpra.model.dao.TimetableDAO.EmptyTimetableException;

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
		HttpSession session = req.getSession(false);
		UserAccountBean user;

		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect("/login");
			return;
		} else {
			user = (UserAccountBean) session.getAttribute("user");
			if (user.getRole() != Role.VISITOR) {
				session.invalidate();
				resp.sendRedirect("/login");
				return;
			}
		}
		
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
		
		VisitorBean visitor = new VisitorBean();
		visitor.setUserID(user.getUserID());

		TimetableDAO dao = new TimetableDAO();
		try {
			dao.getTimetable(visitor);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (EmptyTimetableException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("visitorScheduleIds", visitor.getTimetable().stream().map(s -> s.getId()).collect(Collectors.toList()));
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/TimetableSearch.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserAccountBean user = (UserAccountBean) session.getAttribute("user");
		TimetableDAO dao = new TimetableDAO();
		
		if (req.getParameter("addId") != null) {
			Integer addId = Integer.parseInt(req.getParameter("addId"));
			try {
				dao.addScheduleToTimetable(addId, user.getUserID());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			resp.sendRedirect("/visitor/timetable/search?added=true");
		} else {
			Integer deleteId = Integer.parseInt(req.getParameter("deleteId"));
			try {
				dao.deleteScheduleFromTimetable(deleteId, user.getUserID());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			resp.sendRedirect("/visitor/timetable/search?deleted=true");
		}
		
	}

}
