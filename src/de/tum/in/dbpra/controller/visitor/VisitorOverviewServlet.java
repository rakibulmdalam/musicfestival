package de.tum.in.dbpra.controller.visitor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.Role;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		UserAccountBean user;

		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect("/login");
			return;
		} else {
			user = (UserAccountBean) session.getAttribute("user");
			if (user.getRole() != Role.VISITOR) {
				session.invalidate();
				resp.sendRedirect("/login");
				return;
			}
		}

		VisitorDAO dao = new VisitorDAO();
		VisitorBean visitor = new VisitorBean();
		visitor.setUserID(user.getUserID());
		try {
			dao.getVisitorAccountOverview(visitor);
			req.setAttribute("visitor", visitor);
		} catch (Throwable e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/VisitorOverview.jsp");
		dispatcher.forward(req, resp);

	}

}