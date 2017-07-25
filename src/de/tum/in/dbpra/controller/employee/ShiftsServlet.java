package de.tum.in.dbpra.controller.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.NoteNotificationBean;
import de.tum.in.dbpra.model.bean.ShiftBean;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.dao.NoteDAO;
import de.tum.in.dbpra.model.dao.ShiftDAO;

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
		ShiftDAO dao = new ShiftDAO();
		EmployeeBean employee = new EmployeeBean();
		
		int id;
		try {
			HttpSession session = req.getSession();
			id = ((UserAccountBean) session.getAttribute("user")).getUserID();
		} catch (NumberFormatException e) {
			id = 0;
		}
		

		employee.setUserID(id);
		
		try {
			dao.getShiftsList(employee);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("employee", employee);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Shifts.jsp");
		dispatcher.forward(req, resp);
	}
}
