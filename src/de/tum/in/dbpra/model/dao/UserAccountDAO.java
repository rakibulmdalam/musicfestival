package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.tum.in.dbpra.model.bean.Role;
import de.tum.in.dbpra.model.bean.UserAccountBean;

public class UserAccountDAO extends DAO {
	public void checkUserAccount(UserAccountBean user)
			throws UserNotFoundException, SQLException, ClassNotFoundException {

		String query = "SELECT f.* FROM festivaluser f where email = ? and password = ?;";
		
		String visitor = "SELECT * from visitor where id = ?;";
		
		String provider = "SELECT * from provider where id = ?;";
		
		String employee = "SELECT * from employee where id = ?;";

		Connection con = getConnection();

		PreparedStatement pstmt = con.prepareStatement(query);

		pstmt.setString(1, user.getEmail());
		pstmt.setString(2, user.getPassword());

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
			user.setUserID(rs.getInt("id"));
			user.setPhone(rs.getString("phone"));
			user.setPhotoUrl(rs.getString("picture"));
			if (user.getRole()== Role.VISITOR){
				
				PreparedStatement psv = con.prepareStatement(visitor);

				psv.setLong(1, user.getUserID());
					
				ResultSet rsv = psv.executeQuery();
				
				if(rsv.next()){
					
					user.setUserName(rsv.getString("first_name"));
				}
				rsv.close();
				psv.close();
			}
			else if (user.getRole()== Role.BAND || user.getRole()== Role.SPONSOR){
				
				PreparedStatement psb = con.prepareStatement(provider);

				psb.setLong(1, user.getUserID());
					
				ResultSet rsb = psb.executeQuery();
				
				if(rsb.next()){
					
					user.setUserName(rsb.getString("name"));
				}
				rsb.close();
				psb.close();
			}
			else if (user.getRole()== Role.EMPLOYEE){
				
				PreparedStatement pse = con.prepareStatement(employee);

				pse.setLong(1, user.getUserID());
					
				ResultSet rse = pse.executeQuery();
				
				if(rse.next()){
					
					user.setUserName(rse.getString("first_name"));
					user.setEmployeeRole(rse.getString("role"));
				}
				rse.close();
				pse.close();
			}
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

		UserNotFoundException(String message) {
			super(message);
		}
	}
}