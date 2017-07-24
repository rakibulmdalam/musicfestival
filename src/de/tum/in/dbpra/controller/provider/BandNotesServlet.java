package de.tum.in.dbpra.controller.provider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.AreaBean;
import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.StageBean;
import de.tum.in.dbpra.model.dao.AreaDAO;
import de.tum.in.dbpra.model.dao.BandEmployeeInteractionDAO;
import de.tum.in.dbpra.model.dao.EmployeeDAO;
import de.tum.in.dbpra.model.dao.StageDAO;

public class BandNotesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4820847690830486065L;

	public BandNotesServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeeDAO dao = new EmployeeDAO();
		AreaDAO areaDAO = new AreaDAO();
		
		LinkedHashMap<Integer, EmployeeBean> employees = null;
		ArrayList<AreaBean> areas = null;
		
		try {
			employees = dao.getNonAdminEmployeeList();
			areas = areaDAO.getAreaList();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("employees", employees);
		req.setAttribute("areas", areas);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/AdminNotes.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BandEmployeeInteractionDAO dao = new BandEmployeeInteractionDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		StageDAO stageDAO = new StageDAO();
		
		LinkedHashMap<Integer, EmployeeBean> employees = null;
		ArrayList<StageBean> stages = null;
		
		try {
			employees = employeeDAO.getNonAdminEmployeeList();
			stages = stageDAO.getStageList();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String[] ids = req.getParameterValues("employee_ids");
		
		int stageID = 0;
		
		try {
			stageID = Integer.valueOf(req.getParameter("stage_id"));
		} catch(NumberFormatException e) {
		}
		
		String noteContent = req.getParameter("note");
				
		HashSet<Integer> insertIds = new HashSet<>();
		
		if(ids != null)
			for(String id : ids) {
				System.out.println(id);
				if(employees.containsKey(Integer.valueOf(id))) {
					insertIds.add(Integer.valueOf(id));
				}
			}
		
		req.setAttribute("prefill_employee_ids", insertIds);
		req.setAttribute("prefill_note", noteContent);
		req.setAttribute("employees", employees);
		req.setAttribute("stages", stages);
		
		if(insertIds.size() == 0 || noteContent == null || stageID < 1 || noteContent.trim().length() == 0)
			req.setAttribute("form_error", true);
		else {
			try {
				dao.insertNote(noteContent, stageID, insertIds);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/AdminNotes.jsp");
		dispatcher.forward(req, resp);
	}

}
