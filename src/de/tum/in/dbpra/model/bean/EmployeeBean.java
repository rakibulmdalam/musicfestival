package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;
import java.util.Date;

public class EmployeeBean extends UserAccountBean {

	private String lastName;
	private String firstName;
	private Date dateOfBirth;
	private String employeeRole; // TODO: use role enum
	private ArrayList<ShiftBean> shifts;
	private ArrayList<StageBean> stages;

	public EmployeeBean() {
		super();
		this.shifts = new ArrayList<ShiftBean>();
		this.stages = new ArrayList<StageBean>();
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	public ArrayList<ShiftBean> getShifts() {
		return shifts;
	}

	public void addShift(ShiftBean shift) {
		this.shifts.add(shift);
	}
	
	public void addStage(StageBean stage) {
		this.stages.add(stage);
	}

	public ArrayList<StageBean> getStages() {
		return stages;
	}

	public void setStages(ArrayList<StageBean> stages) {
		this.stages = stages;
	}
}
