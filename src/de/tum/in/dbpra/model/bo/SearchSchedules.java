package de.tum.in.dbpra.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.dao.SchedulesDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;

public class SearchSchedules {
	private HashMap<String, HashMap<String, List<ScheduleBean>>> scheduleResults;
	private List<String> scheduleDates;
	
	public SearchSchedules(String query, SearchType type) throws ClassNotFoundException, SQLException, SearchQueryException {
		SchedulesDAO dao = new SchedulesDAO();
		ArrayList<ScheduleBean> schedules = new ArrayList<ScheduleBean>();
		scheduleResults = new HashMap<String, HashMap<String, List<ScheduleBean>>>();
		
		switch (type) {
		case BAND:
			schedules = dao.getSchedulesByBand(query);
			break;
		case GENRE:
			schedules = dao.getSchedulesByGenre(query);
			break;
		case STAGE:
			schedules = dao.getSchedulesByStage(query);
			break;
		case ALL:
		default:
			schedules = dao.getAllSchedules();
			break;
		}
		
		// Group schedules into dates and stage
		scheduleDates = schedules.stream().map(ScheduleBean::getDateWithoutTime).distinct().collect(Collectors.toList());
		List<String> scheduleStages = schedules.stream().map(s -> s.getStage().getName()).collect(Collectors.toList());
		
		for (String date : scheduleDates) {
			HashMap<String, List<ScheduleBean>> stageScheduleMap = new HashMap<String, List<ScheduleBean>>();
			for (String stage : scheduleStages) {
				List<ScheduleBean> filteredSchedules = schedules.stream()
						.filter(s -> s.getDateWithoutTime().equals(date) && s.getStage().getName().equals(stage))
						.collect(Collectors.toList());
				stageScheduleMap.put(stage, filteredSchedules);
			}
			scheduleResults.put(date, stageScheduleMap);
		}
	 
	}
	
	public HashMap<String, HashMap<String, List<ScheduleBean>>> getSchedules() {
		return scheduleResults;
	}

	public List<String> getScheduleDates() {
		return scheduleDates;
	}
}

