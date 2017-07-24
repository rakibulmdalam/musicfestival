package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.ProductBean;
import de.tum.in.dbpra.model.bean.ProviderBean;


public class ProductDAO extends DAO{
	
	public ArrayList<ProductBean> getProductsByProvider(int providerId) throws ClassNotFoundException, SQLException {
		ArrayList<ProductBean> products = new ArrayList<ProductBean>();
		Connection con = null;
		ResultSet rs;
		PreparedStatement preparedStatement = null;
		
		try{
			con = getConnection();
			con.setAutoCommit(false);
			
			String sqlQuery = "SELECT * FROM product WHERE provider_id = ?";
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, providerId);
			rs = preparedStatement.executeQuery();
			
			boolean hasResults = false;
			while (rs.next()) {
				hasResults = true;
	
				ProviderBean provider = new ProviderBean();
				provider.setUserID(providerId);
				
				ProductBean product = new ProductBean();
	
				product.setName(rs.getString("name"));
				product.setType(rs.getString("type"));
				product.setId(rs.getInt("id"));
				product.setPrice(rs.getDouble("price"));
				product.setProvider(provider);
				
				products.add(product);
			}
		
		
		} catch (SQLException e) {
	
			System.out.println(e.getMessage());
	
		} finally {
	
			if (preparedStatement != null) {
				preparedStatement.close();
			}
	
			if (con != null) {
				con.close();
			}
	
		}
		return products;
	}
}
