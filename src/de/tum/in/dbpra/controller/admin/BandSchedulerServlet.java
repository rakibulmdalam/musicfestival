package de.tum.in.dbpra.controller.admin;

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

public class BandSchedulerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5044942860830593031L;

	public BandSchedulerServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SchedulesDAO schedulesDAO = new SchedulesDAO();
    	
    	ScheduleBean scheduleBean = new ScheduleBean();
    	
		
		ArrayList<ScheduleBean> schedules;
		try {
			schedules = schedulesDAO.getAllSchedules();
			req.setAttribute("timetable", schedules);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Timetable.jsp");
		dispatcher.forward(req, resp);
			
		} catch (ClassNotFoundException | SQLException | SearchQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandScheduler.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
