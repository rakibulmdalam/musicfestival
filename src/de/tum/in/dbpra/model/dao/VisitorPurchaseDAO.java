package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.tum.in.dbpra.model.bean.PurchaseBean;
import de.tum.in.dbpra.model.bean.VisitorBean;

public class VisitorPurchaseDAO extends DAO{
	
	public List<PurchaseBean> getVisitorPurchases(VisitorBean visitor)
			throws EmptyPurchaseException, SQLException, ClassNotFoundException {

		String query = "select p.name as product_name, p.type, p.price, pr.purchase_date, pr.quantity, sh.name as shop_name "
				+ "from purchase pr, sell s , product p, shop sh where pr.sell_id = s.id "
				+ "and s.product_id = p.id and s.shop_id = sh.id and pr.visitor_id = ? ;";
		Connection con = getConnection();

		PreparedStatement pstmt = con.prepareStatement(query);

		pstmt.setInt(1, visitor.getUserID() );

		ResultSet rs = pstmt.executeQuery();
		
		List<PurchaseBean> resultset = new ArrayList<PurchaseBean>();
		
		boolean hasResults = false;

		while (rs.next()) {
			
			hasResults = true;
			PurchaseBean purchase = new PurchaseBean();
			purchase.setProduct(rs.getString("product_name"));
			purchase.setPurchaseDate(rs.getDate("purchase_date"));
			purchase.setQuantity(rs.getInt("quantity"));
			purchase.setShop(rs.getString("shop_name"));
			purchase.setPrice(rs.getFloat("price"));
			resultset.add(purchase);
			
		} 
		
		rs.close();
		pstmt.close();
		con.close();
		
		
		if (!hasResults) {
			throw new EmptyPurchaseException("You have made no purchases!");
		}
		return resultset;
	}

	
	public static class EmptyPurchaseException extends Throwable {
		private static final long serialVersionUID = 1L;

		EmptyPurchaseException(String message) {
			super(message);
		}
	}


}
