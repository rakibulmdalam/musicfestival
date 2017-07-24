package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BandBean extends ProviderBean {

	private int numberOfMembers;
	private String genre;
	private ArrayList<SongBean> songs;
	private ArrayList<ScheduleBean> schedules;

	public BandBean() {
		super();
		this.songs = new ArrayList<SongBean>();
	}

	public int getNumberOfMembers() {
		return numberOfMembers;
	}

	public void setNumberOfMembers(int numberOfMembers) {
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
	
	public String getFormattedSongNames() {
		return getSongs().stream().map(SongBean::getTitle).collect(Collectors.joining(", "));
	}
	
	public void setSongs(ArrayList<SongBean> songs) {
		this.songs = songs;
	}

	public void addSong(SongBean song) {
		this.songs.add(song);
	}

	public ArrayList<ScheduleBean> getSchedules() {
		return schedules;
	}

	public void setSchedules(ArrayList<ScheduleBean> schedules) {
		this.schedules = schedules;
	}

	
}
