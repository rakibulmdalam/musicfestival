package de.tum.in.dbpra.model.bean;

public class Area {
	
	private String id;
	private String name;
	private String type;
	
	public Area (){}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}