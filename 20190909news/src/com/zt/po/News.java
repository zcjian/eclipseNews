package com.zt.po;

import java.util.Date;

/*
 * 新闻系统的 新闻  t_News对应的实体类 News 
 * */
public class News {
	private int id;
	private String title;
	private String summary;
	private String content;
	private Date createTime;
	private NewsColumn newsColumn;
	private NewsUser newsUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public NewsColumn getNewsColumn() {
		return newsColumn;
	}

	public void setNewsColumn(NewsColumn newsColumn) {
		this.newsColumn = newsColumn;
	}

	public NewsUser getNewsUser() {
		return newsUser;
	}

	public void setNewsUser(NewsUser newsUser) {
		this.newsUser = newsUser;
	}

}
