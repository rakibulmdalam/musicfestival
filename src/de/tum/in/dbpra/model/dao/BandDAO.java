package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.SongBean;

public class BandDAO extends DAO {
	
	public BandBean getBandData(int bandId) throws ClassNotFoundException, SQLException{
		BandBean band = new BandBean();
		
		Connection con = null;
		ResultSet rs;
		PreparedStatement preparedStatement = null;
		
		try{
			con = getConnection();
			con.setAutoCommit(false);
			
			String sqlQuery = "SELECT b.*, p.*, f.* FROM band b, provider p, festivaluser f WHERE b.id = ? AND b.id = p.id AND p.id = f.id";
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, bandId);
			rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {

				band.setName(rs.getString("name"));
				band.setEmail(rs.getString("email"));
				band.setNumberOfMembers(rs.getInt("number_of_members"));
				band.setPhotoUrl(rs.getString("picture"));
				band.setUserID(rs.getInt("id"));
				band.setGenre(rs.getString("genre"));
				band.setPhone(rs.getString("phone"));
				
				SongDAO dao = new SongDAO();
				ArrayList<SongBean> songs = dao.getAllSongsByBand(bandId);
				band.setSongs(songs);
				//SchedulesDAO scheduleDAO = new SchedulesDAO();
				//ArrayList<ScheduleBean> schedules = scheduleDAO.getSchedulesByBand(bandId);
				//band.setSchedules(schedules);
			}
		
		
		} catch (SQLException e) {
	
			System.out.println(e.getMessage());
	
		} finally {
	
			if (preparedStatement != null) {
				preparedStatement.close();
			}
	
			if (con != null) {
				con.close();
			}
	
		}
		return band;
	}  
}
