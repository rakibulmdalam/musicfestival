package de.tum.in.dbpra.controller.visitor;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.PurchaseBean;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.bean.VisitorBean;
import de.tum.in.dbpra.model.dao.VisitorPurchaseDAO;
import de.tum.in.dbpra.model.dao.VisitorPurchaseDAO.EmptyPurchaseException;
import de.tum.in.dbpra.model.dao.TimetableDAO.EmptyTimetableException;

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
		UserAccountBean user = (UserAccountBean) session.getAttribute("user");

		VisitorPurchaseDAO dao = new VisitorPurchaseDAO();
		VisitorBean visitor = new VisitorBean();
		visitor.setUserID(user.getUserID());
		PurchaseBean purchase = new PurchaseBean();

		try {
			purchase.setPurchaseBeans(dao.getVisitorPurchases(visitor));
			req.setAttribute("purchase", purchase);
 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyPurchaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Purchases.jsp");
		dispatcher.forward(req, resp);
	}

}
