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

import de.tum.in.dbpra.model.bean.BandEmployeeInteractionBean;
import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.dao.BandEmployeeInteractionDAO;

public class NonAdminBandNotesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -164241206191678240L;

	public NonAdminBandNotesServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BandEmployeeInteractionDAO dao = new BandEmployeeInteractionDAO();
		EmployeeBean employee = new EmployeeBean();
		
		int id;
		try {
			HttpSession session = req.getSession();
			id = ((UserAccountBean) session.getAttribute("user")).getUserID();
		} catch (NumberFormatException e) {
			id = 0;
		}

		employee.setUserID(id);
		
		ArrayList<BandEmployeeInteractionBean> interactions = null;
		
		try {
			interactions = dao.getInteractionsList(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("interactions", interactions);
		req.setAttribute("employee", employee);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/NonAdminBandNotes.jsp");
		dispatcher.forward(req, resp);
	}

}
