package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class BandBean extends ProviderBean {

	private String numberOfMembers;
	private String genre;
	private ArrayList<SongBean> songs;
	private ScheduleBean schedule;

	public String getNumberOfMembers() {
		return numberOfMembers;
	}

	public void setNumberOfMembers(String numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public ArrayList<SongBean> getSongs() {
		return songs;
	}

	public void addSong(SongBean song) {
		this.songs.add(song);
	}

	public ScheduleBean getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduleBean schedule) {
		this.schedule = schedule;
	}
}
