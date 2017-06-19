package de.tum.in.dbpra.model.bean;

import java.util.Date;

public class Schedule {
	private Integer id;
	private Date timeBuildUp;
	private Date timeStartPlaying;
	private Date timeFinishPlaying;
	private Date timeLeaveStage;
	private String stageName;
	private Integer bandId;
	
	public Schedule(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getTimeBuildUp() {
		return timeBuildUp;
	}
	public void setTimeBuildUp(Date timeBuildUp) {
		this.timeBuildUp = timeBuildUp;
	}
	public Date getTimeStartPlaying() {
		return timeStartPlaying;
	}
	public void setTimeStartPlaying(Date timeStartPlaying) {
		this.timeStartPlaying = timeStartPlaying;
	}
	public Date getTimeFinishPlaying() {
		return timeFinishPlaying;
	}
	public void setTimeFinishPlaying(Date timeFinishPlaying) {
		this.timeFinishPlaying = timeFinishPlaying;
	}
	public Date getTimeLeaveStage() {
		return timeLeaveStage;
	}
	public void setTimeLeaveStage(Date timeLeaveStage) {
		this.timeLeaveStage = timeLeaveStage;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public Integer getBandId() {
		return bandId;
	}
	public void setBandId(Integer bandId) {
		this.bandId = bandId;
	}
}
