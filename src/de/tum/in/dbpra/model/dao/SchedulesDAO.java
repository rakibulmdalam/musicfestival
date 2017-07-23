package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.StageBean;

public class SchedulesDAO extends DAO {

	public ArrayList<ScheduleBean> getAllSchedules() throws SQLException, ClassNotFoundException, SearchQueryException {
		String sql = "SELECT s.id, s.time_start_playing, s.time_finish_playing, s.stage_name, p.name, b.genre, u.picture "
				+ "FROM schedule s, provider p, band b, festivaluser u " + "WHERE p.id = s.band_id "
				+ "AND b.id = p.id " + "AND p.id = u.id;";

		return getMatchingSchedules(sql, null);
	}
	
	public ArrayList<ScheduleBean> getSchedulesByBand(String band) throws ClassNotFoundException, SQLException, SearchQueryException {
		String sql = "SELECT s.id, s.time_start_playing, s.time_finish_playing, "
				+ "s.stage_name, p.name, b.genre, u.picture "
				+ "FROM schedule s, provider p, band b, festivaluser u "
				+ "WHERE p.id = s.band_id AND b.id = p.id AND p.id = u.id "
				+ "AND lower(p.name) ~ ?;";
		return getMatchingSchedules(sql, band);
	}

	public ArrayList<ScheduleBean> getSchedulesByGenre(String genre) throws ClassNotFoundException, SQLException, SearchQueryException {
		String sql = "SELECT s.id, s.time_start_playing, s.time_finish_playing, "
				+ "s.stage_name, p.name, b.genre, u.picture "
				+ "FROM schedule s, provider p, band b, festivaluser u "
				+ "WHERE p.id = s.band_id AND b.id = p.id AND p.id = u.id "
				+ "AND lower(b.genre) ~ ?;";
		return getMatchingSchedules(sql, genre);
	}

	public ArrayList<ScheduleBean> getSchedulesByStage(String stage) throws ClassNotFoundException, SQLException, SearchQueryException {
		String sql = "SELECT s.id, s.time_start_playing, s.time_finish_playing, "
				+ "s.stage_name, p.name, b.genre, u.picture "
				+ "FROM schedule s, provider p, band b, festivaluser u "
				+ "WHERE p.id = s.band_id AND b.id = p.id AND p.id = u.id "
				+ "AND lower(s.stage_name) ~ ?;";
		return getMatchingSchedules(sql, stage);
	}
	
	public void insertSchedule(int bandID, String stageName, HashMap<String, Date> dates) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO schedule VALUES (NEXTVAL('schedule_seq'), ?, ?, ?, ?, ?, ?);";
		
		Connection con = getConnection();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
		
		PreparedStatement pstmt = con.prepareStatement(query);
		
		int i = 1;
		
		for(String param : new String[] {"build_up", "start_playing", "finish_playing", "leave_stage"}) {
			pstmt.setTimestamp(i++, new Timestamp(dates.get(param).getTime()));
		}
		
		pstmt.setString(5, stageName);
		pstmt.setInt(6, bandID);
		
		pstmt.executeUpdate();
		pstmt.close();
		
		con.commit();
		con.close();
	}

	private ArrayList<ScheduleBean> getMatchingSchedules(String sqlQuery, String query)
			throws SQLException, ClassNotFoundException, SearchQueryException {
		ArrayList<ScheduleBean> schedules = new ArrayList<ScheduleBean>();

		Connection con = getConnection();
		con.setAutoCommit(false);
		
		ResultSet rs;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		if (query == null) {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
		} else {
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.setString(1, query.toLowerCase());
			rs = pstmt.executeQuery();
		}

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

			schedules.add(schedule);
		}
		
		Collections.sort(schedules);
		
		rs.close();
		if (query == null) {
			stmt.close();
		} else {
			pstmt.close();
		}
		con.close();

		if (!hasResults) {
			throw new SearchQueryException("No schedules matching that query were found");
		}
		return schedules;
	}

	public static class SearchQueryException extends Throwable {
		private static final long serialVersionUID = 1L;

		SearchQueryException(String message) {
			super(message);
		}
	}

}
