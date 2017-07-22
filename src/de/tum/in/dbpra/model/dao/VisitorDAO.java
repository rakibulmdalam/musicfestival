package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.tum.in.dbpra.model.bean.VisitorBean;
import de.tum.in.dbpra.model.dao.UserAccountDAO.UserNotFoundException;

public class VisitorDAO extends DAO {

	public void getVisitorAccountOverview(VisitorBean visitor)
			throws UserNotFoundException, SQLException, ClassNotFoundException {

		String query = "select v.id,v.last_name,v.first_name,v.date_of_birth,v.balance,f.picture, "
				+ "	t.description  from visitor v, festivaluser f, ticketclass t "
				+ "where v.ticketclass_id = t.id and v.id = f.id and v.id = ?;";

		Connection con = getConnection();

		PreparedStatement pstmt = con.prepareStatement(query);

		pstmt.setInt(1, visitor.getUserID() );

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			visitor.setFirstName(rs.getString("first_name"));
			visitor.setLastName(rs.getString("last_name"));
			visitor.setBalance(rs.getDouble("balance"));
			visitor.setDateOfBirth(rs.getDate("date_of_birth"));
			visitor.setPhotoUrl(rs.getString("picture"));
			visitor.setUserID(rs.getInt("id"));
			
		} else {
			throw new UserNotFoundException("Visitor Not found");
		}

		rs.close();
		pstmt.close();
		con.close();

	}

}
