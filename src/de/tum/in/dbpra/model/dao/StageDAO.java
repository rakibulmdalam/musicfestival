package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import de.tum.in.dbpra.model.bean.StageBean;

public class StageDAO extends DAO {
	
	public ArrayList<StageBean> getStageList() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM stage ORDER BY name";

		return getStages(sql);
	}
	

	private ArrayList<StageBean> getStages(String sqlQuery)
			throws SQLException, ClassNotFoundException {
		ArrayList<StageBean> stages = new ArrayList<>();

		Connection con = getConnection();
		con.setAutoCommit(false);
		
		ResultSet rs;
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement(sqlQuery);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			StageBean stage = new StageBean();
			stage.setName(rs.getString("name"));
			
			stages.add(stage);
		}
		
		
		rs.close();
		pstmt.close();
		con.close();
		
		return stages;
	}

}
