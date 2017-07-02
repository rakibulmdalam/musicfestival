package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.tum.in.dbpra.model.bean.Role;
import de.tum.in.dbpra.model.bean.UserAccountBean;


public class UserAccountDAO extends DAO{
	public void checkUserAccount(UserAccountBean user) throws UserNotFoundException, SQLException, ClassNotFoundException {
		
		String query = "SELECT * FROM useraccount WHERE username = ? and password = ?;";
		
		Connection con = getConnection();
		
		
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			user.setRole(Role.valueOf(rs.getString("role")));
		} else {
			throw new UserNotFoundException("Username or password given is wrong!");
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

		UserNotFoundException(String message){
			super(message);
		}
	}
}
