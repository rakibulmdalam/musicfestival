package de.tum.in.dbpra.model.bean;

import java.util.Date;

public class Post {
	private Integer id;
	private String heading;
	private String content;
	private Date date;
	private String tags;
	private Integer newsletterId;
	
	public Post(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getNewsletterId() {
		return newsletterId;
	}
	public void setNewsletterId(Integer newsletterId) {
		this.newsletterId = newsletterId;
	}
}
