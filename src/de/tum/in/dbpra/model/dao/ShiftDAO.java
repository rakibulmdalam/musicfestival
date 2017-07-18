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
import de.tum.in.dbpra.model.bean.ShiftBean;
import de.tum.in.dbpra.model.bean.StageBean;

public class ShiftDAO extends DAO {
	
	public void getShiftsList(EmployeeBean employee) throws SQLException, ClassNotFoundException {
		String sql = "SELECT s.*, a.* FROM shift s " 
				+ "JOIN employee_shift es ON es.shift_id = s.id "
				+ "JOIN area a ON s.area_id = a.id "
				+ "JOIN employee e ON e.id = s.employee_id  "
				+ "WHERE e.id = ? " 
				+ "ORDER BY starting_time ASC ";

		getShifts(sql, employee);
	}
	

	private void getShifts(String sqlQuery, EmployeeBean employee)
			throws SQLException, ClassNotFoundException {
		Connection con = getConnection();
		con.setAutoCommit(false);
		
		ResultSet rs;
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement(sqlQuery);
		pstmt.setInt(1, employee.getUserID());
		rs = pstmt.executeQuery();

		
		while (rs.next()) {
			ShiftBean shift = new ShiftBean();
			AreaBean area = new AreaBean();
			
			area.setName(rs.getString("name"));
			area.setType(rs.getString("type"));
			
			shift.setArea(area);
			shift.setStartingTime(rs.getTimestamp("starting_time"));
			shift.setEndingTime(rs.getTimestamp("ending_time"));
			

			employee.addShift(shift);
		}
		
		
		
		rs.close();
		pstmt.close();
		con.close();
	}

}
