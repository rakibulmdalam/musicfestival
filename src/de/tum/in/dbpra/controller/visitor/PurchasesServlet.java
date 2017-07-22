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

public class PurchasesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8082530144272757949L;

	public PurchasesServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Purchases.jsp");
		dispatcher.forward(req, resp);
	}

}
