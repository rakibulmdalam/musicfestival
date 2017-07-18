package de.tum.in.dbpra.controller.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.NoteBean;
import de.tum.in.dbpra.model.bean.NoteNotificationBean;
import de.tum.in.dbpra.model.dao.NoteDAO;

public class NonAdminNotesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2847226788261998720L;

	public NonAdminNotesServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoteDAO dao = new NoteDAO();
		EmployeeBean employee = new EmployeeBean();
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			id = 1;
		}
		

		employee.setUserID(id);
		
		ArrayList<NoteNotificationBean> notes = null;
		
		try {
			notes = dao.getNotesList(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("notes", notes);
		req.setAttribute("employee", employee);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/NonAdminNotes.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
