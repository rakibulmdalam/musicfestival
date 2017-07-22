package de.tum.in.dbpra.controller.provider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.dao.SchedulesDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;

public class BandOverviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7370860435322910251L;

	public BandOverviewServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SchedulesDAO schedulesDAO = new SchedulesDAO();
    	
    	BandBean band = new BandBean();
    	
    	/*String bandName;
		try {
			bandName= request.getParameter("name");
		} catch (NumberFormatException e) {
			bandName = "1";
		}
		band.setUserName(bandName);
		*/
		ArrayList<ScheduleBean> bandSchedule;
		try {
			bandSchedule = schedulesDAO.getSchedulesByBand("bandName");
			req.setAttribute("schedules", bandSchedule);
		
			
		} catch (ClassNotFoundException | SQLException | SearchQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}   
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandOverview.jsp");
		
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
