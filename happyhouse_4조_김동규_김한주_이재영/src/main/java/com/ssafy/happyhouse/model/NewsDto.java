package com.ssafy.happyhouse.model;

public class NewsDto {
	private String title;// title
	private String link;// link
	private String company;// company
	private String upload_date;// upload_date
	private String content;// content
	
	public NewsDto() {}
	
	public NewsDto(String title, String link, String company, String upload_date, String content) {
		super();
		this.title = title;
		this.link = link;
		this.company = company;
		this.upload_date = upload_date;
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
