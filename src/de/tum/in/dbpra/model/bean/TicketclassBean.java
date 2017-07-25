package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class TicketclassBean {

	private Integer id;
	private Double price;
	private String description;
	private ArrayList<AreaBean> allowedAreas;

	public TicketclassBean() {
		this.allowedAreas = new ArrayList<AreaBean>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<AreaBean> getAllowedAreas() {
		return allowedAreas;
	}

	public void addAllowedArea(AreaBean allowedArea) {
		this.allowedAreas.add(allowedArea);
	}
}
