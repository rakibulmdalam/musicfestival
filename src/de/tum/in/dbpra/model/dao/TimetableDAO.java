package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.SongBean;
import de.tum.in.dbpra.model.bean.StageBean;
import de.tum.in.dbpra.model.bean.VisitorBean;

public class TimetableDAO extends DAO {

	public void getTimetable(VisitorBean visitor) throws ClassNotFoundException, SQLException, EmptyTimetableException {

		String query = "SELECT s.id, s.time_start_playing, s.time_finish_playing, s.stage_name, p.name, b.id as bandId, b.genre, u.picture "
				+ "FROM visitor_schedule vs, schedule s, provider p, band b, festivaluser u "
				+ "WHERE vs.visitor_id = ? "
				+ "AND s.id = vs.schedule_id " 
				+ "AND p.id = s.band_id " 
				+ "AND b.id = p.id "
				+ "AND b.id = u.id;";
		
		Connection con = getConnection();
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
			
			SongDAO dao = new SongDAO();
			ArrayList<SongBean> songs = dao.getAllSongsByBand(rs.getInt("bandId"));
			band.setSongs(songs);
			
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
	
	public void addScheduleToTimetable(Integer scheduleId, Integer visitorId) throws ClassNotFoundException, SQLException {
		String query = "INSERT INTO visitor_schedule VALUES (?, ?);";
		
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, visitorId);
		pstmt.setInt(2, scheduleId);
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	
	public void deleteScheduleFromTimetable(Integer scheduleId, Integer visitorId) throws ClassNotFoundException, SQLException {
		String query = "DELETE FROM visitor_schedule WHERE visitor_id = ? AND schedule_id = ?;";
		
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, visitorId);
		pstmt.setInt(2, scheduleId);
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}

	public static class EmptyTimetableException extends Throwable {
		private static final long serialVersionUID = 1L;

		EmptyTimetableException(String message) {
			super(message);
		}
	}

}
