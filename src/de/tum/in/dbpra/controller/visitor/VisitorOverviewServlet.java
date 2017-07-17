package de.tum.in.dbpra.controller.visitor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.bean.VisitorBean;
import de.tum.in.dbpra.model.dao.VisitorOverviewDAO;

public class VisitorOverviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1997539060524686869L;

	public VisitorOverviewServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String userID = request.getParameter("userID");
		//request.setAttribute("userID", userID);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/VisitorOverview.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			VisitorOverviewDAO dao = new VisitorOverviewDAO();
			//UserAccountBean user = new UserAccountBean();
			VisitorBean visitor = new VisitorBean();
			//int id = Integer.valueOf(request.getAttribute("userID"));
			visitor.setUserID((int) request.getAttribute("userID"));
			dao.getVisitorAccountOverview(visitor);
			request.setAttribute("visitor", visitor);


		} catch (Throwable e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/VisitorOverview.jsp"); 
		dispatcher.forward(request, response);
		

	}


}