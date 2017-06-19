package de.tum.in.dbpra.model.bean;

import java.util.Date;

public class Application {
	private Integer id;
	private Date time;
	private Integer approvedBy;
	
	public Application(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}
}
