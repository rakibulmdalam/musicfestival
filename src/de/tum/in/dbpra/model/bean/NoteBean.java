package de.tum.in.dbpra.model.bean;

import java.util.Date;
import java.util.HashMap;

public class NoteBean {
	
	private Integer id;
	private String content;
	private Date creationTime;
	private AreaBean area;
	private HashMap<EmployeeBean, Date> recipientsTimeSeen;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public AreaBean getArea() {
		return area;
	}

	public void setArea(AreaBean area) {
		this.area = area;
	}

	public HashMap<EmployeeBean, Date> getRecipientsTimeSeen() {
		return this.recipientsTimeSeen;
	}
	
	public Date getRecipientTimeSeen(EmployeeBean employee) {
		return this.recipientsTimeSeen.get(employee);
	}
	
	public void addRecipient(EmployeeBean employee) {
		this.recipientsTimeSeen.put(employee, null);
	}
	
	public void setRecipientTimeSeen(EmployeeBean employee, Date timeSeen) {
		this.recipientsTimeSeen.put(employee, timeSeen);
	}
	
	
}
