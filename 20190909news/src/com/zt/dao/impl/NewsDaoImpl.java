package com.zt.dao.impl;
/*
 * NewsDaoImpl
 * 新闻系统的新闻的数据库层访问类接口NewsDao的实现类
 * 真正的数据层操作方法的实现
 * */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zt.dao.NewsDao;
import com.zt.po.News;
import com.zt.po.NewsColumn;
import com.zt.po.NewsUser;
import com.zt.utils.DBUtils;
import com.zt.utils.PageUtils;
public class NewsDaoImpl implements NewsDao {
	public boolean addNews(News news) {
		String sql="insert into t_news(id,title,summary,content,createTime,"
				+ "columnId,userId) values(seq_news.nextval,?,?,?,sysdate,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean result=false;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,news.getTitle());
			pstmt.setString(2,news.getSummary());
			pstmt.setString(3,news.getContent());
			pstmt.setInt(4, news.getNewsColumn().getId());
			pstmt.setInt(5, news.getNewsUser().getId());
			int num=pstmt.executeUpdate();
			if(num==1){result=true;}
		 }catch (Exception e) {
            e.printStackTrace();
	     }finally{
		    DBUtils.close(null, pstmt, conn);
	     }	
		return result;
	}

	@Override
	public boolean updateNews(News news) {
		String sql="update t_news set title=?,summary=?,content=?"
				+ ",createTime=sysdate,columnId=?,userId=? where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean result=false;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,news.getTitle());
			pstmt.setString(2,news.getSummary());
			pstmt.setString(3,news.getContent());
			pstmt.setInt(4, news.getNewsColumn().getId());
			pstmt.setInt(5, news.getNewsUser().getId());
			pstmt.setInt(6, news.getId());
			int num=pstmt.executeUpdate();
			if(num==1){result=true;}
		 }catch (Exception e) {
            e.printStackTrace();
	     }finally{
		    DBUtils.close(null, pstmt, conn);
	     }	
		return result;
	}

	public boolean delNews(int newsId) {
		String sql="delete from t_news where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean result=false;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, newsId);
			int num=pstmt.executeUpdate();
			if(num==1){result=true;}
		 }catch (Exception e) {
            e.printStackTrace();
	     }finally{
		    DBUtils.close(null, pstmt, conn);
	     }	
		return result;
	}

	public News getNewsById(int newsId) {
		String sql="select n.*,c.name cname,u.name uname from "
				 + " t_news n,t_newscolumn c,t_newsUser u"
				+ " where n.columnId=c.id and n.userId=u.id and n.id=?";
		News news=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, newsId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				news=new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setSummary(rs.getString("summary"));
				news.setContent(rs.getString("content"));
				news.setCreateTime(rs.getDate("createTime"));
				NewsColumn c=new NewsColumn();
				c.setId(rs.getInt("columnId"));
				c.setName(rs.getString("cname"));
				news.setNewsColumn(c);				
				NewsUser u=new NewsUser();
				u.setId(rs.getInt("userId"));
				u.setName(rs.getString("uname"));
				news.setNewsUser(u);
			}
		 }catch(Exception e) {
		    e.printStackTrace();
		 }finally{
			 DBUtils.close(rs, pstmt, conn);
		 }
		return news;
	}

	public int getTotalSize(Map filter) {
		String sql="select count(*) from t_news where 1=1";
		if(filter.get("title")!=null){
			sql+=" and title like '%"+filter.get("title")+"%'";
		}
		if(filter.get("columnId")!=null){
			sql+=" and columnId in ("
					+ " select id from t_newscolumn "
					+ "where id="+filter.get("columnId")+""
				   + " or parentId="+filter.get("columnId")+")";
		}
		if(filter.get("userId")!=null){sql+=" and userId="+filter.get("userId");}
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){result=rs.getInt(1);}
		  }catch(Exception e) {
			    e.printStackTrace();
		  }finally{DBUtils.close(rs, pstmt, conn);
		  }
		return result;
	}

	public List<News> searchNewsByPage(Map filter, PageUtils pageUtils) {
		 List<News> newsList=new ArrayList();
		 String sql="select n.*,c.name cname,u.name uname,rownum r from "
				 + " t_news n,t_newscolumn c,t_newsUser u"
				 + " where n.columnId=c.id and n.userId=u.id ";
		 if(filter.get("title")!=null){
				sql+=" and title like '%"+filter.get("title")+"%'";
			}
	    if(filter.get("columnId")!=null){
			 sql+=" and columnId in ("
				+ " select id from t_newscolumn "
				+ "where id="+filter.get("columnId")+""
			    + " or parentId="+filter.get("columnId")+")";
			}
		if(filter.get("userId")!=null){sql+=" and userId="+filter.get("userId");}
		String newSql="select * from ("+sql+" and rownum<=?) where r>?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(newSql);
			pstmt.setInt(1,pageUtils.getCurrPage()*pageUtils.getPageSize());
			pstmt.setInt(2,(pageUtils.getCurrPage()-1)*pageUtils.getPageSize());
			rs=pstmt.executeQuery();
			while(rs.next()){
				News news=new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setSummary(rs.getString("summary"));
				news.setContent(rs.getString("content"));
				news.setCreateTime(rs.getDate("createTime"));
				NewsColumn c=new NewsColumn();
				c.setId(rs.getInt("columnId"));
				c.setName(rs.getString("cname"));
				news.setNewsColumn(c);				
				NewsUser u=new NewsUser();
				u.setId(rs.getInt("userId"));
				u.setName(rs.getString("uname"));
				news.setNewsUser(u);
				newsList.add(news);
			}
		 }catch(Exception e) {
		    e.printStackTrace();
		 }finally{
			 DBUtils.close(rs, pstmt, conn);
		 }		
		return newsList;
	}

}
