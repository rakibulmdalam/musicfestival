package de.tum.in.dbpra.model.bean;

public class Ticketclass {
	private Integer id;
	private Double price;
	private String description;
	
	public Ticketclass(){}
	
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
}
