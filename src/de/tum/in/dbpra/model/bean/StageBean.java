package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class StageBean {

	private String name;
	private AreaBean area;
	private ArrayList<EmployeeBean> technicians;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AreaBean getArea() {
		return area;
	}

	public void setArea(AreaBean area) {
		this.area = area;
	}

	public ArrayList<EmployeeBean> getTechnicians() {
		return technicians;
	}

	public void addTechnician(EmployeeBean technician) {
		this.technicians.add(technician);
	}

}
