package de.tum.in.dbpra.controller.provider;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.dao.SchedulesDAO;



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
			try {
			
        	SchedulesDAO schedulesDAO = new SchedulesDAO();
        	//String band = UserAccountBean.class.getName();
        	String band = "hi";
        	List listOk = schedulesDAO.getSchedulesByBand(band);       	
        	request.setAttribute("orderOk", listOk);
        	
	
        	
    	} catch (Throwable e) {
    		e.printStackTrace();
    		request.setAttribute("error", e.toString() + e.getMessage());
    	}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BandScheduler.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
