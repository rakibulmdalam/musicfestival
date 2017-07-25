package de.tum.in.dbpra.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.dao.SchedulesDAO;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;

public class SearchSchedules {
	private HashMap<String, HashMap<String, List<ScheduleBean>>> scheduleResults;
	private Set<String> scheduleDates;
	
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
		scheduleDates = new TreeSet<String>(schedules.stream().map(ScheduleBean::getDateWithoutTime).collect(Collectors.toSet()));
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

	public Set<String> getScheduleDates() {
		return scheduleDates;
	}
}

