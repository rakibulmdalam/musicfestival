package de.tum.in.dbpra.controller.visitor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.bean.VisitorBean;
import de.tum.in.dbpra.model.dao.VisitorDAO;

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
				//int userID =  Integer.parseInt(request.getParameter("userID"));
				//request.setParameter("userID", userID);
				VisitorDAO dao = new VisitorDAO();
				//UserAccountBean user = new UserAccountBean();
				VisitorBean visitor = new VisitorBean();
				//UserAccountBean user = (UserAccountBean) request.getAttribute("user");
				visitor.setUserID((int) request.getSession(false).getAttribute("userID"));
				try{
					dao.getVisitorAccountOverview(visitor);
					request.setAttribute("visitor", visitor);
				}
				catch (Throwable e) {
					e.printStackTrace();
					request.setAttribute("error", e.getMessage());
				}
				

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/VisitorOverview.jsp");
				dispatcher.forward(request, response);

			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//int userID =  Integer.parseInt(request.getParameter("userID"));
		//request.setParameter("userID", userID);
		VisitorDAO dao = new VisitorDAO();
		//UserAccountBean user = new UserAccountBean();
		VisitorBean visitor = new VisitorBean();
		UserAccountBean user = (UserAccountBean) request.getAttribute("user");
		visitor.setUserID(user.getUserID());
		visitor.setFirstName(user.getUserName());
//		try{
//			dao.getVisitorAccountOverview(visitor);
//			request.setAttribute("visitor", visitor);
//		}
//		catch (Throwable e) {
//			e.printStackTrace();
//			request.setAttribute("error", e.getMessage());
//		}
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/VisitorOverview.jsp");
		dispatcher.forward(request, response);

	}


}