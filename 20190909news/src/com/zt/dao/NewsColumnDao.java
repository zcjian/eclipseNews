package com.zt.dao;

import java.util.List;

import com.zt.po.NewsColumn;

/*
 * 新闻系统的新闻栏目  t_NewsColumn 的数据库层访问类接口
 * 
 * 把 对t_NewsColumn表的添删 改查操作转换成 
 *      对于的实体类  NewsColumn 的操作
 * */
public interface NewsColumnDao {
   public boolean addNewsColumn( NewsColumn column);
   public boolean updateNewsColumn( NewsColumn column);
   public NewsColumn getNewsColumnById(int id);
   
   public NewsColumn getParentColumnById(int id); //查父级栏目
   public List<NewsColumn> findAll();
   
   public List<NewsColumn> getParentColumn(boolean flag);
   
}
