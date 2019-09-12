package com.zt.dao;

import java.util.List;
import java.util.Map;

import com.zt.po.News;
import com.zt.utils.PageUtils;

/*
 * 新闻系统的新闻  t_News的数据库层访问类接口
 * 
 * 把 对t_News表的添删 改查操作转换成 
 *      对于的实体类  News 的操作
 * */
public interface NewsDao {
   public boolean addNews(News news);
   public boolean updateNews(News news);
   public boolean delNews(int newsId);
   public News getNewsById(int newsId);
   
   /*分页+模糊查询*/
    public int getTotalSize(Map filter);
    public List<News> searchNewsByPage(Map filter,PageUtils pageUtils);
   
}
