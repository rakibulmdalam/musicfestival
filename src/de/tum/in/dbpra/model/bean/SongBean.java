package de.tum.in.dbpra.model.bean;

public class SongBean {
	private Integer id;
	private String title;
	private BandBean band;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BandBean getBand() {
		return band;
	}

	public void setBand(BandBean band) {
		this.band = band;
	}
}
