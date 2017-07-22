package de.tum.in.dbpra.controller.employee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.dao.EmployeeAllDAO;

public class EmployeeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6629141420946194447L;

	
	public EmployeeServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				//int userID =  Integer.parseInt(request.getParameter("userID"));
				//request.setParameter("userID", userID);
				EmployeeAllDAO dao = new EmployeeAllDAO();
				//UserAccountBean user = new UserAccountBean();
				EmployeeBean employee = new EmployeeBean();
				UserAccountBean user = (UserAccountBean) request.getAttribute("user");
				employee.setUserID(user.getUserID());
				try{
					dao.isAdmin(employee);
					request.setAttribute("employee", employee);
					if ( employee.getEmployeeRole().toUpperCase() == "ADMIN"){
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("./admin");
						dispatcher.forward(request, response);

					}
					else {//if ( employee.getEmployeeRole().toUpperCase() != "ADMIN"){
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("./employee");
						dispatcher.forward(request, response);

					}
 
				}
				catch (Throwable e) {
					e.printStackTrace();
					request.setAttribute("error", e.getMessage());
				}
				

//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/VisitorOverview.jsp");
//				dispatcher.forward(request, response);

			}

	
}
