package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.bean.Role;

public class EmployeeDAO extends DAO {
	
	public LinkedHashMap<Integer, EmployeeBean> getNonAdminEmployeeList()
			throws SQLException, ClassNotFoundException {
		LinkedHashMap<Integer, EmployeeBean> result = new LinkedHashMap<>();
		
		Connection con = getConnection();
		con.setAutoCommit(false);
		
		ResultSet rs;
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement("SELECT * FROM employee WHERE role != 'admin' ORDER BY last_name, first_name");
		rs = pstmt.executeQuery();

		
		while (rs.next()) {
			EmployeeBean employee = new EmployeeBean();
			employee.setUserID(rs.getInt("id"));
			employee.setFirstName(rs.getString("first_name"));
			employee.setLastName(rs.getString("last_name"));
			employee.setDateOfBirth(rs.getDate("date_of_birth"));
			employee.setEmployeeRole(rs.getString("role"));
			employee.setRole(Role.EMPLOYEE);
			
			
			result.put(rs.getInt("id"), employee);
		}		
		
		
		rs.close();
		pstmt.close();
		con.close();
		
		return result;
	}

}
