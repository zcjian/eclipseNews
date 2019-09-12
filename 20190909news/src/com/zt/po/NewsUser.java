package com.zt.po;
/*
 * 新闻系统的用户表  t_NewsUser对应的实体类 NewsUser 
 * */
public class NewsUser {
    private int id;
    private String name;
    private String pass;
    private String status;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
