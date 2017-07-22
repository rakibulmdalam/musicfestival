package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.tum.in.dbpra.model.bean.EmployeeBean;
import de.tum.in.dbpra.model.dao.UserAccountDAO.UserNotFoundException;

public class EmployeeAllDAO extends DAO{
	
	public void isAdmin(EmployeeBean employee)
			throws UserNotFoundException, SQLException, ClassNotFoundException {

		String query = "SELECT * FROM employee f where id = ?;";
		
		Connection con = getConnection();

		PreparedStatement pstmt = con.prepareStatement(query);

		pstmt.setLong(1, employee.getUserID());

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			employee.setEmployeeRole(rs.getString("role"));
			employee.setFirstName(rs.getString("first_name"));
			employee.setLastName(rs.getString("last_name"));
			employee.setDateOfBirth(rs.getDate("date_of_birth"));
			
		} else {
			throw new UserNotFoundException("Employee Not found!");
		}

		rs.close();
		pstmt.close();
		con.close();

	}

	public static class UserNotFoundException extends Throwable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		UserNotFoundException(String message) {
			super(message);
		}
	}


}
