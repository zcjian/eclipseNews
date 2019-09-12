package com.zt.dao.impl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.zt.dao.NewsUserDao;
import com.zt.po.NewsUser;
import com.zt.utils.DBUtils;
/*
 * NewsUserDaoImpl
 * ����ϵͳ���û�������ݿ�������ӿ�NewsUserDao��ʵ����
 * ���������ݲ����������ʵ��
 * */
public class NewsUserDaoImpl implements NewsUserDao {
	public NewsUser login(String name) {
		String sql="select * from t_newsUser where name=? ";
		NewsUser user=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			 if(rs.next()){
				 user=new NewsUser();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 user.setPass(rs.getString("pass"));
				 user.setStatus(rs.getString("status"));
			 }
		  }catch (Exception e) {
                 e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}	
		return user;
	}
	public List<NewsUser> findAll(){
		String sql="select * from t_newsUser ";
		List<NewsUser>  userList=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			 while(rs.next()){
				 NewsUser user=new NewsUser();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 user.setPass(rs.getString("pass"));
				 user.setStatus(rs.getString("status"));
				 userList.add(user);
			 }
		  }catch (Exception e) {
                 e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}	
		return userList;
	}
}
