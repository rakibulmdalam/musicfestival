package de.tum.in.dbpra.model.bean;

public class Sponsor {
	private Integer id;
	private String name;
	private String phone;
	private Double paidMoney;
	private String address;
	private Boolean isSelling;
	private Boolean isAdvertisiing;
	
	public Sponsor(){}
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(Double paidMoney) {
		this.paidMoney = paidMoney;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getIsSelling() {
		return isSelling;
	}
	public void setIsSelling(Boolean isSelling) {
		this.isSelling = isSelling;
	}
	public Boolean getIsAdvertisiing() {
		return isAdvertisiing;
	}
	public void setIsAdvertisiing(Boolean isAdvertisiing) {
		this.isAdvertisiing = isAdvertisiing;
	}
}
