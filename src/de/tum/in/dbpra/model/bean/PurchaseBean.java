package de.tum.in.dbpra.model.bean;

import java.util.Date;

public class PurchaseBean {
	
	private ShopBean shop;
	private ProductBean product;
	private VisitorBean visitor;
	private Date purchaseDate;
	private Integer quantity;

	public ShopBean getShop() {
		return shop;
	}

	public void setShop(ShopBean shop) {
		this.shop = shop;
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public VisitorBean getVisitor() {
		return visitor;
	}

	public void setVisitor(VisitorBean visitor) {
		this.visitor = visitor;
	}

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
}
