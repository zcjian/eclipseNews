package com.zt.dao;

import java.util.List;

import com.zt.po.NewsUser;

/*
 * ����ϵͳ���û���  t_NewsUser �����ݿ�������ӿ�
 * 
 * �� ��t_NewsUser�����ɾ �Ĳ����ת���� 
 *      ���ڵ�ʵ���� NewsUser �Ĳ���
 * */
public interface NewsUserDao {
      public NewsUser login(String name);
      public List<NewsUser> findAll();
}
