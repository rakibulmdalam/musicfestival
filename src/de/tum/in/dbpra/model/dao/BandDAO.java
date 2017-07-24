package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.SongBean;
import de.tum.in.dbpra.model.dao.SchedulesDAO.SearchQueryException;


public class BandDAO extends DAO {
	
	public ArrayList<BandBean> getBandList() throws SQLException, ClassNotFoundException {
		String sql = "SELECT b.*, p.name FROM band b"
				+ " JOIN provider p ON p.id = b.id ORDER BY p.name";

		return getBands(sql);
	}
	

	private ArrayList<BandBean> getBands(String sqlQuery)
			throws SQLException, ClassNotFoundException {
		ArrayList<BandBean> bands = new ArrayList<>();

		Connection con = getConnection();
		con.setAutoCommit(false);
		
		ResultSet rs;
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement(sqlQuery);
		rs = pstmt.executeQuery();

		boolean hasResults = false;
		
		while (rs.next()) {
			BandBean band = new BandBean();
			band.setUserID(rs.getInt("id"));
			band.setName(rs.getString("name"));
			band.setGenre(rs.getString("genre"));
			band.setNumberOfMembers(rs.getInt("number_of_members"));
			
			bands.add(band);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return bands;
	}

	
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
