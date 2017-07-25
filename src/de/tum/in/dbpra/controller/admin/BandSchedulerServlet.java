package de.tum.in.dbpra.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.StageBean;
import de.tum.in.dbpra.model.bo.SearchSchedules;
import de.tum.in.dbpra.model.bo.SearchType;
import de.tum.in.dbpra.model.dao.BandDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;
import de.tum.in.dbpra.model.dao.StageDAO;

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
		SearchSchedules search;
		try {
			search = new SearchSchedules("", SearchType.ALL);
			req.setAttribute("schedules", search.getSchedules());
			req.setAttribute("dates", search.getScheduleDates());
		} catch (ClassNotFoundException | SQLException | SearchQueryException e) {
			e.printStackTrace();
			req.setAttribute("schedules", null);
			req.setAttribute("dates", null);
		}

		BandDAO bandDAO = new BandDAO();
    	StageDAO stageDAO = new StageDAO();
    	
    	SchedulesDAO schedulesDAO = new SchedulesDAO();
		
		ArrayList<BandBean> bands = null;
		ArrayList<StageBean> stages = null;
		
		try {
			stages = stageDAO.getStageList();
			bands = bandDAO.getBandList();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("bands", bands);
		req.setAttribute("stages", stages);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandScheduler.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

    	BandDAO bandDAO = new BandDAO();
    	StageDAO stageDAO = new StageDAO();
    	
    	SchedulesDAO schedulesDAO = new SchedulesDAO();
		
		ArrayList<BandBean> bands = null;
		ArrayList<StageBean> stages = null;
		
		try {
			stages = stageDAO.getStageList();
			bands = bandDAO.getBandList();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		HashMap<String, Date> dateValues = new HashMap<>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date previous = null;
		int bandId = 0;
		String stageName = null;
		
		try {
			bandId = Integer.valueOf(req.getParameter("band_id"));
			stageName = req.getParameter("stage_name");
			
			for(String param : new String[] {"build_up", "start_playing", "finish_playing", "leave_stage"}) {
				Calendar cal = Calendar.getInstance();
				String dateValue = req.getParameter("day_" + param);
				int timeValue = Integer.valueOf(req.getParameter("time_" + param));
				
				if(timeValue < 0 || timeValue > 23 * 60 + 30 || timeValue % 30 != 0) {
					throw new RuntimeException("Invalid value for time of day in field " + param);
				}
				
				cal.setTime(dateFormat.parse(dateValue));
				cal.add(Calendar.MINUTE, timeValue);
				dateValues.put(param, cal.getTime());	
				
				if(previous != null && previous.after(cal.getTime())) {
					throw new RuntimeException("Please make sure that all the dates are in ascending order.");
				}
				
				previous = cal.getTime();
			}
			
			schedulesDAO.insertSchedule(bandId, stageName, dateValues);
			
			
		} catch(Exception e) {
			req.setAttribute("prefill_dates", dateValues);
			req.setAttribute("prefill_band", bandId);
			req.setAttribute("prefill_stage", stageName);
			req.setAttribute("form_error", true);
			
			e.printStackTrace();
		}
		
		SearchSchedules search;
		try {
			search = new SearchSchedules("", SearchType.ALL);
			req.setAttribute("schedules", search.getSchedules());
			req.setAttribute("dates", search.getScheduleDates());
		} catch (ClassNotFoundException | SQLException | SearchQueryException e) {
			e.printStackTrace();
			req.setAttribute("schedules", null);
			req.setAttribute("dates", null);
		}
		
		req.setAttribute("bands", bands);
		req.setAttribute("stages", stages);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/BandScheduler.jsp");
		dispatcher.forward(req, resp);
	}

}
