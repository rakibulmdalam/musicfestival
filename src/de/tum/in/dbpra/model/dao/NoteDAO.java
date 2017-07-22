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

public class NoteDAO extends DAO {
	
	public ArrayList<NoteNotificationBean> getNotesList(int employeeID) throws SQLException, ClassNotFoundException {
		String sql = "SELECT n.*, ntfy.seen, a.name as area_name FROM note n " 
				+ "JOIN note_notification ntfy ON n.id = ntfy.note_id "
				+ "JOIN employee e ON e.id = ntfy.employee_id "
				+ "JOIN area a ON a.id = n.area_id "
				+ "WHERE ntfy.employee_id = ? " 
				+ "ORDER BY n.creation_time DESC";

		return getNotes(sql, employeeID);
	}
	

	private ArrayList<NoteNotificationBean> getNotes(String sqlQuery, int employeeID)
			throws SQLException, ClassNotFoundException {
		ArrayList<NoteNotificationBean> notes = new ArrayList<>();

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

			
			AreaBean area = new AreaBean();
			area.setId(rs.getInt("area_id"));
			area.setName(rs.getString("area_name"));
			
			
			NoteBean note = new NoteBean();
			NoteNotificationBean ntf = new NoteNotificationBean();

			note.setArea(area);
			ntf.setEmployee(employee);
			note.setCreationTime(rs.getTimestamp("creation_time"));
			note.setContent(rs.getString("content"));
			ntf.setNote(note);
			notes.add(ntf);
		}
		
		
		rs.close();
		pstmt.close();
		con.close();
		
		return notes;
	}

}
