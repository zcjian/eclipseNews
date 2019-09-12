package com.zt.dao;

import java.util.List;
import java.util.Map;

import com.zt.po.News;
import com.zt.utils.PageUtils;

/*
 * ����ϵͳ������  t_News�����ݿ�������ӿ�
 * 
 * �� ��t_News�����ɾ �Ĳ����ת���� 
 *      ���ڵ�ʵ����  News �Ĳ���
 * */
public interface NewsDao {
   public boolean addNews(News news);
   public boolean updateNews(News news);
   public boolean delNews(int newsId);
   public News getNewsById(int newsId);
   
   /*��ҳ+ģ����ѯ*/
    public int getTotalSize(Map filter);
    public List<News> searchNewsByPage(Map filter,PageUtils pageUtils);
   
}
