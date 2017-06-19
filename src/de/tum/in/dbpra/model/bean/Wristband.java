package de.tum.in.dbpra.model.bean;

import java.util.Date;

public class Wristband {
	private String rfid;
	private Date activation;
	private Date deactivation;
	private Integer visitorId;
	
	public Wristband(){}
	
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public Date getActivation() {
		return activation;
	}
	public void setActivation(Date activation) {
		this.activation = activation;
	}
	public Date getDeactivation() {
		return deactivation;
	}
	public void setDeactivation(Date deactivation) {
		this.deactivation = deactivation;
	}
	public Integer getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}
}
