package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.AreaBean;
import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.NoteBean;
import de.tum.in.dbpra.model.bean.NoteNotificationBean;
import de.tum.in.dbpra.model.bean.SongBean;

public class SongDAO extends DAO {
	
	public static void main(String[] args){
		SongDAO dao = new SongDAO();
		BandBean band = new BandBean();
		SongBean song = new SongBean();
		
		band.setUserID(300);
		
		ArrayList<SongBean> songList;
		try {
			songList = dao.getAllSongsByBand(band.getUserID());
			for(SongBean singleSong: songList){
				System.out.println(singleSong.getTitle() + "," + singleSong.getId());
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		song.setBand(band);
		song.setTitle("Test Title");
		
		//SongDAO dao = new SongDAO();
		/*try {
			dao.addSong(song);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public void addSong(SongBean song) throws ClassNotFoundException, SQLException{
		
		Connection con = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO song"
				+ "(title, band_id) VALUES"
				+ "(?,?)";

		try {
			con = getConnection();
			con.setAutoCommit(false);
			
			preparedStatement = con.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, song.getTitle());
			preparedStatement.setInt(2, song.getBand().getUserID());
			
			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into DBUSER table!");
			
			con.commit();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			con.rollback();

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (con != null) {
				con.close();
			}

		}
	}
	

	public ArrayList<SongBean> getAllSongsByBand(int bandId) throws ClassNotFoundException, SQLException{
		ArrayList<SongBean> songList = new ArrayList<SongBean>();
		
		Connection con = null;
		ResultSet rs;
		PreparedStatement preparedStatement = null;
		
		try{
			con = getConnection();
			con.setAutoCommit(false);
			
			String sqlQuery = "SELECT * FROM song WHERE band_id = ?";
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, bandId);
			rs = preparedStatement.executeQuery();
			
			boolean hasResults = false;
			while (rs.next()) {
				hasResults = true;
	
				BandBean band = new BandBean();
				band.setUserID(bandId);
				
				SongBean song = new SongBean();
	
				song.setBand(band);
				song.setTitle(rs.getString("title"));
				song.setId(rs.getInt("id"));
				
				songList.add(song);
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
		return songList;
	}
	
}
