package de.tum.in.dbpra.model.bean;

import java.util.Date;

public class NoteNotificationBean {

	private Integer id;
	private String content;
	private Date creationTime;
	private AreaBean area;
	private Date seenTime;
	private NoteBean note;
	private EmployeeBean employee;

	public NoteNotificationBean() {
	}

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

	public Date getSeenTime() {
		return seenTime;
	}

	public void setSeenTime(Date seenTime) {
		this.seenTime = seenTime;
	}

	public EmployeeBean getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}

	public NoteBean getNote() {
		return note;
	}

	public void setNote(NoteBean note) {
		this.note = note;
	}

}
