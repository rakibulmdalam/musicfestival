package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import de.tum.in.dbpra.model.bean.AreaBean;

public class AreaDAO extends DAO {
	
	public ArrayList<AreaBean> getAreaList() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM area ORDER BY name";

		return getAreas(sql);
	}
	

	private ArrayList<AreaBean> getAreas(String sqlQuery)
			throws SQLException, ClassNotFoundException {
		ArrayList<AreaBean> areas = new ArrayList<>();

		Connection con = getConnection();
		con.setAutoCommit(false);
		
		ResultSet rs;
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement(sqlQuery);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			AreaBean area = new AreaBean();
			area.setId(rs.getInt("id"));
			area.setName(rs.getString("name"));
			
			areas.add(area);
		}
		
		
		rs.close();
		pstmt.close();
		con.close();
		
		return areas;
	}

}
