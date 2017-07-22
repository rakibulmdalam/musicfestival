package de.tum.in.dbpra.controller.provider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.ProviderBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.bean.VisitorBean;
import de.tum.in.dbpra.model.dao.SchedulesDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;
import de.tum.in.dbpra.model.dao.TimetableDAO.EmptyTimetableException;



/**
 * Servlet implementation class BandSchedulerServlet
 */
@WebServlet(name = "BandScheduleServlet", urlPatterns = { "/BandScheduleServlet" })
public class BandSchedulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BandSchedulerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
        	SchedulesDAO schedulesDAO = new SchedulesDAO();
        	
        	BandBean band = new BandBean();
        	
        	String bandName;
    		try {
    			bandName= request.getParameter("name");
    		} catch (NumberFormatException e) {
    			bandName = "1";
    		}
    		band.setUserName(bandName);
    		
    		ArrayList<ScheduleBean> bandSchedule;
			try {
				bandSchedule = schedulesDAO.getSchedulesByBand(bandName);
				request.setAttribute("schedules", bandSchedule);
    		request.setAttribute("band", band);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Timetable.jsp");
    		dispatcher.forward(request, response);
				
			} catch (ClassNotFoundException | SQLException | SearchQueryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
    		
    		
    		
    	
	
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
