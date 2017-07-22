package de.tum.in.dbpra.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.Role;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.dao.UserAccountDAO;
import de.tum.in.dbpra.model.dao.UserAccountDAO.UserNotFoundException;

/**
 * Servlet implementation class CustomerServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserAccountDAO dao = new UserAccountDAO();
			UserAccountBean user = new UserAccountBean();
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			dao.checkUserAccount(user);
			request.setAttribute("user", user);
			int userID = user.getUserID();
			Role userRole = user.getRole();
			
			HttpSession session = request.getSession(true);
			session.setAttribute("userID", userID);
			session.setAttribute("userRole", userRole);
			session.setAttribute("user", user);
			
			Role employee = Role.EMPLOYEE;
			Role band = Role.BAND; 
			Role sponsor = Role.SPONSOR; 
			Role visitor = Role.VISITOR;
			if ( user.getRole() ==  employee ){
				
				if (user.getEmployeeRole().equals("ADMIN")){
					response.sendRedirect("./admin") ;
				}
				else {
					response.sendRedirect("./employee") ;
				}
				 
				return;}  
			else if ( user.getRole() ==  band ){ 
				response.sendRedirect("./band") ; 
				return;}  
			else if ( user.getRole() ==  sponsor ){ 
				
				response.sendRedirect("./sponsor") ; 
				return;}  
			else if ( user.getRole() ==  visitor ){ 
				
				response.sendRedirect("./visitor") ; 
				return;}  
 
		} catch (UserNotFoundException e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		if (request.getAttribute("error")!=null){
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp"); 
			dispatcher.forward(request, response);
		}
		
		

	}

}