package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import de.tum.in.dbpra.model.bean.BandBean;
import de.tum.in.dbpra.model.bean.BandEmployeeInteractionBean;
import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.ScheduleBean;
import de.tum.in.dbpra.model.bean.StageBean;

public class BandEmployeeInteractionDAO extends DAO {
	
	public ArrayList<BandEmployeeInteractionBean> getInteractionsList(int employeeID) throws SQLException, ClassNotFoundException {
		String sql = "SELECT i.*, p.name as band_name, b.genre FROM band_employee_interaction i " 
				+ "JOIN band b ON b.id = i.band_id "
				+ "JOIN provider p ON p.id = i.band_id "
				+ "WHERE i.employee_id = ? " 
				+ "ORDER BY i.interaction_date ASC";

		return getInteractions(sql, employeeID);
	}
	

	private ArrayList<BandEmployeeInteractionBean> getInteractions(String sqlQuery, int employeeID)
			throws SQLException, ClassNotFoundException {
		ArrayList<BandEmployeeInteractionBean> interactions = new ArrayList<>();

		Connection con = getConnection();
		con.setAutoCommit(false);
		
		ResultSet rs;
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement(sqlQuery);
		pstmt.setInt(1, employeeID);
		rs = pstmt.executeQuery();

		boolean hasResults = false;
		while (rs.next()) {
			hasResults = true;

			EmployeeBean employee = new EmployeeBean();
			employee.setUserID(employeeID);

			BandBean band = new BandBean();
			band.setUserID(rs.getInt("band_id"));
			band.setName(rs.getString("band_name"));
			band.setGenre(rs.getString("genre"));
			
			StageBean stage = new StageBean();
			stage.setName(rs.getString("stage_name"));

			BandEmployeeInteractionBean interaction = new BandEmployeeInteractionBean();
			interaction.setBand(band);
			interaction.setEmployee(employee);
			interaction.setStage(stage);
			interaction.setInteractionDate(rs.getTimestamp("interaction_date"));
			interaction.setDescription(rs.getString("description"));
			interactions.add(interaction);
		}
		
		
		rs.close();
		pstmt.close();
		con.close();
		
		return interactions;
	}

}
