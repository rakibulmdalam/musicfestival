package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class SponsorBean extends ProviderBean {
	private Integer id;
	private Double paidMoney;
	private ArrayList<ShopBean> shops;

	public SponsorBean() {
		super();
		this.shops = new ArrayList<ShopBean>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(Double paidMoney) {
		this.paidMoney = paidMoney;
	}

	public ArrayList<ShopBean> getShops() {
		return shops;
	}

	public void addShop(ShopBean shop) {
		this.shops.add(shop);
	}
}
