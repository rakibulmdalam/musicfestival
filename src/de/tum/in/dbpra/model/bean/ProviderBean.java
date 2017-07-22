package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class ProviderBean extends UserAccountBean {
	
	private String name;
	private ArrayList<ProductBean> products;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<ProductBean> products) {
		this.products = products;
	}
}
