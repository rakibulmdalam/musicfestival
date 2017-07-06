package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;
import java.util.Date;

public class VisitorBean extends UserAccountBean {

	private String lastName;
	private String firstName;
	private Date dateOfBirth;
	private Double balance;
	private TicketclassBean ticketClass;
	private ArrayList<AreaBean> specialAreaPermissions;
	private ArrayList<ScheduleBean> timetable;
	private ArrayList<PurchaseBean> purchases;

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

	public TicketclassBean getTicketClass() {
		return ticketClass;
	}

	public void setTicketClass(TicketclassBean ticketClass) {
		this.ticketClass = ticketClass;
	}

	public ArrayList<AreaBean> getSpecialAreaPermissions() {
		return specialAreaPermissions;
	}

	public void addSpecialAreaPermission(AreaBean area) {
		this.specialAreaPermissions.add(area);
	}

	public ArrayList<ScheduleBean> getTimetable() {
		return timetable;
	}

	public void addSchedule(ScheduleBean schedule) {
		this.timetable.add(schedule);
	}

	public ArrayList<PurchaseBean> getPurchases() {
		return purchases;
	}

	public void addPurchase(PurchaseBean purchase) {
		this.purchases.add(purchase);
	}

}
