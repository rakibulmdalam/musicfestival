package de.tum.in.dbpra.model.bean;

import java.util.Date;

public class Visitor {
	private Integer id;
	private String lastName;
	private String firstName;
	private String phone;
	private String address;
	private String email;
	private Date dateOfBirth;
	private Double balance;
	private Integer ticketClassId;
	
	public Visitor(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getTicketClassId() {
		return ticketClassId;
	}
	public void setTicketClassId(Integer ticketClassId) {
		this.ticketClassId = ticketClassId;
	}
}
