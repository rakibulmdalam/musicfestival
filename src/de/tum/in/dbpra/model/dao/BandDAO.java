package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import de.tum.in.dbpra.model.bean.AreaBean;
import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.BandEmployeeInteractionBean;
import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.NoteBean;
import de.tum.in.dbpra.model.bean.NoteNotificationBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.StageBean;

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
			band.setNumberOfMembers(rs.getString("number_of_members"));
			
			bands.add(band);
		}
		
		
		rs.close();
		pstmt.close();
		con.close();
		
		return bands;
	}

}
