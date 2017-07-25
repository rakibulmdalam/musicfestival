package de.tum.in.dbpra.controller.provider;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.SponsorBean;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.dao.SponsorDAO;

public class SponsorOverviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3637927477606825711L;

	public SponsorOverviewServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SponsorBean sponsor = new SponsorBean();
    	
    	SponsorDAO sponsorDAO = new SponsorDAO();
		HttpSession session = req.getSession();
		UserAccountBean uab = (UserAccountBean) session.getAttribute("user");
		try {
			sponsor = sponsorDAO.getSponsorData(uab.getUserID());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("sponsor", sponsor);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/SponsorOverview.jsp");
		dispatcher.forward(req, resp);
	}

}
