package com.zt.po;
/*
 * ����ϵͳ�� ������Ŀ  t_NewsColumn��Ӧ��ʵ���� NewsColumn 
 * */
public class NewsColumn {
    private  int  id;
    private String name;
    private NewsColumn parent;
    private String status="1";  //1����   0����
    
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
