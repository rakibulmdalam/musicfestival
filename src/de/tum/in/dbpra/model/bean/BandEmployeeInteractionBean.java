package de.tum.in.dbpra.model.bean;

import java.util.Date;

public class BandEmployeeInteractionBean {

	private BandBean band;
	private EmployeeBean employee;
	private StageBean stage;
	private Date interactionDate;
	private String description;
	
	public BandEmployeeInteractionBean() {
		this.band = new BandBean();
		this.stage = new StageBean();
		this.employee = new EmployeeBean();
	}

	public BandBean getBand() {
		return band;
	}

	public void setBand(BandBean band) {
		this.band = band;
	}

	public EmployeeBean getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}

	public Date getInteractionDate() {
		return interactionDate;
	}

	public void setInteractionDate(Date interactionDate) {
		this.interactionDate = interactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StageBean getStage() {
		return stage;
	}

	public void setStage(StageBean stage) {
		this.stage = stage;
	}

	
}
