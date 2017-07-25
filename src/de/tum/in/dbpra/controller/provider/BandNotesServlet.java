package de.tum.in.dbpra.controller.provider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.dao.BandEmployeeInteractionDAO;
import de.tum.in.dbpra.model.dao.EmployeeDAO;

public class BandNotesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4820847690830486065L;

	public BandNotesServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		LinkedHashMap<Integer, EmployeeBean> employees = null;
		try {
			employees = employeeDAO.getEmployeesWithStages();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("employees", employees);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandNotes.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BandEmployeeInteractionDAO dao = new BandEmployeeInteractionDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		LinkedHashMap<Integer, EmployeeBean> employees = null;
		int bandID = 0;
		
		try {
			employees = employeeDAO.getEmployeesWithStages();
			HttpSession session = req.getSession();
			bandID = ((UserAccountBean) session.getAttribute("user")).getUserID();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String[] ids = req.getParameterValues("employee_ids");
		
		
		String noteContent = req.getParameter("note");
				
		HashSet<String> insertIds = new HashSet<>();
		
		try {

			if(ids != null)
				for(String id : ids) {
					String[] split = id.split("-");
					if(employees.containsKey(Integer.valueOf(split[0]))) {
						insertIds.add(id);
					}
				}
		} catch(NullPointerException | NumberFormatException e) {
			req.setAttribute("form_error", true);
		}
		
		req.setAttribute("prefill_employee_ids", insertIds);
		req.setAttribute("prefill_note", noteContent);
		req.setAttribute("employees", employees);
		
		if(insertIds.size() == 0 || noteContent == null || noteContent.trim().length() == 0)
			req.setAttribute("form_error", true);
		else {
			try {
				dao.insertNote(noteContent, bandID, insertIds);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandNotes.jsp");
		dispatcher.forward(req, resp);
	}

}
