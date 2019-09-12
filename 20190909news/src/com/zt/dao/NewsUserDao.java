package com.zt.dao;

import java.util.List;

import com.zt.po.NewsUser;

/*
 * 新闻系统的用户表  t_NewsUser 的数据库层访问类接口
 * 
 * 把 对t_NewsUser表的添删 改查操作转换成 
 *      对于的实体类 NewsUser 的操作
 * */
public interface NewsUserDao {
      public NewsUser login(String name);
      public List<NewsUser> findAll();
}
