package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.StageBean;
import de.tum.in.dbpra.model.bean.VisitorBean;

public class TimetableDAO extends DAO {

	public void getTimetable(VisitorBean visitor) throws ClassNotFoundException, SQLException, EmptyTimetableException {

		String query = "SELECT s.id, s.time_start_playing, s.time_finish_playing, s.stage_name, p.name, b.genre, u.picture "
				+ "FROM visitor_schedule vs, schedule s, provider p, band b, festivaluser u "
				+ "WHERE vs.visitor_id = ? "
				+ "AND s.id = vs.schedule_id " 
				+ "AND p.id = s.band_id " 
				+ "AND b.id = p.id "
				+ "AND b.id = u.id;";
		
		Connection con = getConnection();
		con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, visitor.getUserID());
		ResultSet rs = pstmt.executeQuery();

		boolean hasResults = false;
		while (rs.next()) {
			hasResults = true;
			
			StageBean stage = new StageBean();
			stage.setName(rs.getString("stage_name"));
			
			BandBean band = new BandBean();
			band.setName(rs.getString("name"));
			band.setGenre(rs.getString("genre"));
			band.setPhotoUrl(rs.getString("picture"));
			
			ScheduleBean schedule = new ScheduleBean();
			schedule.setBand(band);
			schedule.setStage(stage);
			schedule.setId(rs.getInt("id"));
			schedule.setTimeStartPlaying(rs.getTimestamp("time_start_playing"));
			schedule.setTimeFinishPlaying(rs.getTimestamp("time_finish_playing"));
			
			visitor.addSchedule(schedule);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		if (!hasResults) {
			throw new EmptyTimetableException("Visitor has an empty timetable");
		}
	}

	public static class EmptyTimetableException extends Throwable {
		private static final long serialVersionUID = 1L;

		EmptyTimetableException(String message) {
			super(message);
		}
	}

}
