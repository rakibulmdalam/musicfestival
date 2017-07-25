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
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.UserAccountBean;
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
		HttpSession session = req.getSession(false);
		UserAccountBean user = (UserAccountBean) session.getAttribute("user");

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

		TreeSet<String> scheduleDays = new TreeSet<String>(
				visitor.getTimetable().stream().map(ScheduleBean::getDateWithoutTime).collect(Collectors.toSet()));
		req.setAttribute("scheduleDays", scheduleDays);
		req.setAttribute("visitor", visitor);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Timetable.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserAccountBean user = (UserAccountBean) session.getAttribute("user");
		TimetableDAO dao = new TimetableDAO();

		Integer deleteId = Integer.parseInt(req.getParameter("deleteId"));
		try {
			dao.deleteScheduleFromTimetable(deleteId, user.getUserID());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		resp.sendRedirect("/visitor/timetable?deleted=true");

	}

}
