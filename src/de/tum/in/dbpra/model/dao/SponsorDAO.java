package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.SponsorBean;

public class SponsorDAO extends DAO {
	
	public ArrayList<SponsorBean> getSponsorList() throws SQLException, ClassNotFoundException {
		String sql = "SELECT s.*, p.name FROM sponsor b"
				+ " JOIN provider p ON p.id = s.id ORDER BY p.name";

		return getSponsors(sql);
	}
	

	private ArrayList<SponsorBean> getSponsors(String sqlQuery)
			throws SQLException, ClassNotFoundException {
		ArrayList<SponsorBean> sponsors = new ArrayList<SponsorBean>();

		Connection con = getConnection();
		con.setAutoCommit(false);
		
		ResultSet rs;
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement(sqlQuery);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			SponsorBean sponsor = new SponsorBean();
			sponsor.setUserID(rs.getInt("id"));
			sponsor.setName(rs.getString("name"));
			sponsor.setPaidMoney(rs.getDouble("paid_money"));
			
			sponsors.add(sponsor);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return sponsors;
	}

	
	public SponsorBean getSponsorData(int sponsorId) throws ClassNotFoundException, SQLException{
		SponsorBean sponsor = new SponsorBean();
		
		Connection con = null;
		ResultSet rs;
		PreparedStatement preparedStatement = null;
		
		try{
			con = getConnection();
			con.setAutoCommit(false);
			
			String sqlQuery = "SELECT s.*, p.*, f.* FROM sponsor s, provider p, festivaluser f WHERE s.id = ? AND s.id = p.id AND p.id = f.id";
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, sponsorId);
			rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {

				sponsor.setName(rs.getString("name"));
				sponsor.setEmail(rs.getString("email"));
				sponsor.setPhotoUrl(rs.getString("picture"));
				sponsor.setUserID(rs.getInt("id"));
				sponsor.setPaidMoney(rs.getDouble("paid_money"));
				sponsor.setPhone(rs.getString("phone"));
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
		return sponsor;
	}  

}
