package de.tum.in.dbpra.controller.provider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.SongBean;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.dao.BandDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;
import de.tum.in.dbpra.model.dao.SongDAO;

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
    	
    	BandDAO bandDAO = new BandDAO();
		HttpSession session = req.getSession(false);
		UserAccountBean uab = (UserAccountBean) session.getAttribute("user");
		try {
			band = bandDAO.getBandData(uab.getUserID());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<ScheduleBean> bandSchedule;
		try {
			bandSchedule = schedulesDAO.getSchedulesByBand(band.getName());
			band.setSchedules(bandSchedule);
			req.setAttribute("schedules", bandSchedule);
		} catch (ClassNotFoundException | SQLException | SearchQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		req.setAttribute("band", band);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandOverview.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		BandBean band = new BandBean();
		UserAccountBean uab = (UserAccountBean) session.getAttribute("user");
		band.setUserID(uab.getUserID());
		SongBean song = new SongBean();
		song.setTitle(req.getParameter("newSongTitle"));
		song.setBand(band);
		
		SongDAO songDAO = new SongDAO();
		
		try {
			songDAO.addSong(song);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SchedulesDAO schedulesDAO = new SchedulesDAO();
    	
    	BandDAO bandDAO = new BandDAO();
		
		try {
			band = bandDAO.getBandData(uab.getUserID());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<ScheduleBean> bandSchedule;
		try {
			bandSchedule = schedulesDAO.getSchedulesByBand(band.getName());
			band.setSchedules(bandSchedule);
			req.setAttribute("schedules", bandSchedule);
		} catch (ClassNotFoundException | SQLException | SearchQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		req.setAttribute("band", band);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandOverview.jsp");
		dispatcher.forward(req, resp);
	}

}
