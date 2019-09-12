package com.zt.po;
/*
 * 新闻系统的 新闻栏目  t_NewsColumn对应的实体类 NewsColumn 
 * */
public class NewsColumn {
    private  int  id;
    private String name;
    private NewsColumn parent;
    private String status="1";  //1启用   0禁用
    
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
	public NewsColumn getParent() {
		return parent;
	}
	public void setParent(NewsColumn parent) {
		this.parent = parent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
