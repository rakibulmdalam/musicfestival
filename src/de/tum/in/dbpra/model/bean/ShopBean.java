package de.tum.in.dbpra.model.bean;

import java.util.HashMap;

public class ShopBean {

	private Integer id;
	private String name;
	private SponsorBean sponsor;
	private HashMap<ProductBean, Integer> productQuantities;

	public ShopBean() {
		this.productQuantities = new HashMap<ProductBean, Integer>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SponsorBean getSponsor() {
		return sponsor;
	}

	public void setSponsor(SponsorBean sponsor) {
		this.sponsor = sponsor;
	}

	public HashMap<ProductBean, Integer> getProductQuantities() {
		return productQuantities;
	}

	public Integer getProductQuantity(ProductBean product) {
		return productQuantities.get(product);
	}

	public void addProduct(ProductBean product, Integer quantity) {
		this.setProductQuantity(product, quantity);
	}

	public void setProductQuantity(ProductBean product, Integer quantity) {
		this.productQuantities.put(product, quantity);
	}

}
