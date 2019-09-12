package com.zt.dao;

import java.util.List;

import com.zt.po.NewsColumn;

/*
 * ����ϵͳ��������Ŀ  t_NewsColumn �����ݿ�������ӿ�
 * 
 * �� ��t_NewsColumn�����ɾ �Ĳ����ת���� 
 *      ���ڵ�ʵ����  NewsColumn �Ĳ���
 * */
public interface NewsColumnDao {
   public boolean addNewsColumn( NewsColumn column);
   public boolean updateNewsColumn( NewsColumn column);
   public NewsColumn getNewsColumnById(int id);
   
   public NewsColumn getParentColumnById(int id); //�鸸����Ŀ
   public List<NewsColumn> findAll();
   
   public List<NewsColumn> getParentColumn(boolean flag);
   
}
