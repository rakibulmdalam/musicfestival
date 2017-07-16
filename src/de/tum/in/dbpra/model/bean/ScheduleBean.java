package de.tum.in.dbpra.model.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleBean implements Comparable<ScheduleBean>{
	
	private Integer id;
	private Date timeBuildUp;
	private Date timeStartPlaying;
	private Date timeFinishPlaying;
	private Date timeLeaveStage;
	private StageBean stage;
	private BandBean band;

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
	
	public String getDateWithoutTime() {
		DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
		return dateFormat.format(this.timeStartPlaying);
	}

	public Date getTimeStartPlaying() {
		return timeStartPlaying;
	}
	
	public String getFormattedTimeStartPlaying() {
		DateFormat dateFormat = new SimpleDateFormat("h.ma");
		return dateFormat.format(this.timeStartPlaying);
	}

	public void setTimeStartPlaying(Date timeStartPlaying) {
		this.timeStartPlaying = timeStartPlaying;
	}

	public Date getTimeFinishPlaying() {
		return timeFinishPlaying;
	}

	public String getFormattedTimeFinishPlaying() {
		DateFormat dateFormat = new SimpleDateFormat("h.ma");
		return dateFormat.format(this.timeFinishPlaying);
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

	public StageBean getStage() {
		return stage;
	}

	public void setStage(StageBean stage) {
		this.stage = stage;
	}

	public BandBean getBand() {
		return band;
	}

	public void setBand(BandBean band) {
		this.band = band;
	}

	@Override
	public int compareTo(ScheduleBean o) {
		return this.timeStartPlaying.compareTo(o.getTimeStartPlaying());
	}
}