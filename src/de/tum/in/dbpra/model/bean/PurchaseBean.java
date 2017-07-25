package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class PurchaseBean {
	
	private String shop;
	private String product;
	//private VisitorBean visitor;
	private Date purchaseDate;
	private Integer quantity;
	private Float price; 
	private List<PurchaseBean> purchaseBeans ;

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	/*public VisitorBean getVisitor() {
		return visitor;
	}

	public void setVisitor(VisitorBean visitor) {
		this.visitor = visitor;
	}
*/
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public List<PurchaseBean> getPurchaseBeans() {
		return purchaseBeans;
	}

	public void setPurchaseBeans(List<PurchaseBean> purchaseBeans) {
		this.purchaseBeans = purchaseBeans;
	}
}
