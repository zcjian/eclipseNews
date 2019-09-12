package com.zt.dao.impl;
/*
 * NewsColumnDaoImpl
 * 新闻系统的新闻栏目的数据库层访问类接口NewsColumnDao的实现类
 * 真正的数据层操作方法的实现
 * */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zt.dao.NewsColumnDao;
import com.zt.po.NewsColumn;
import com.zt.utils.DBUtils;
public class NewsColumnDaoImpl implements NewsColumnDao {
	public boolean addNewsColumn(NewsColumn column) {
		String sql="insert into t_newscolumn(id,name,parentId,status) "
				+ "values(seq_newsColumn.nextval,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean result=false;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, column.getName());
			if(column.getParent()==null){pstmt.setObject(2, null);
			}else{pstmt.setInt(2, column.getParent().getId());}
			pstmt.setString(3, column.getStatus());
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
	public boolean updateNewsColumn(NewsColumn column) {
		String sql="update t_newscolumn set name=?,parentId=?,status=?"
			 	+ " where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean result=false;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, column.getName());
			if(column.getParent()==null){pstmt.setObject(2, null);
			}else{pstmt.setInt(2, column.getParent().getId());}
			pstmt.setString(3, column.getStatus());
			pstmt.setInt(4, column.getId());
			int num=pstmt.executeUpdate();
			if(num==1){result=true;}
		 }catch (Exception e) {
            e.printStackTrace();
	     }finally{
		    DBUtils.close(null, pstmt, conn);
	     }	
		return result;
	}
	public NewsColumn getParentColumnById(int id){
		String sql="select * from t_newscolumn where id=?";
		Connection conn=null; PreparedStatement pstmt=null;
		ResultSet rs=null; NewsColumn column=null;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				column=new NewsColumn();
				column.setId(rs.getInt("id"));
				column.setName(rs.getString("name"));
				column.setStatus(rs.getString("status"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
		return column;
	}

	public NewsColumn getNewsColumnById(int id) {
		String sql="select t.*,p.name pname from t_newscolumn t, t_newscolumn p"
				 + " where t.parentId=p.id and t.id=?";
		Connection conn=null; PreparedStatement pstmt=null;
		ResultSet rs=null; NewsColumn column=null;
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id); rs=pstmt.executeQuery();
			if(rs.next()){
				column=new NewsColumn();
				column.setId(rs.getInt("id"));column.setName(rs.getString("name"));
				column.setStatus(rs.getString("status"));
				NewsColumn parentColumn=new NewsColumn();
				parentColumn.setId(rs.getInt("parentId"));
				parentColumn.setName(rs.getString("pname"));
				column.setParent(parentColumn);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
		return column;
	}

	@Override
	public List<NewsColumn> findAll() {
		String sql="select * from t_newscolumn where status='1'";
		Connection conn=null; PreparedStatement pstmt=null;
		ResultSet rs=null;
		 List<NewsColumn> columns=new ArrayList();
		try {
			conn=DBUtils.getConnection();
			pstmt=conn.prepareStatement(sql);
		    rs=pstmt.executeQuery();
			while(rs.next()){
				NewsColumn column=new NewsColumn();
				column.setId(rs.getInt("id"));
				column.setName(rs.getString("name"));
				column.setStatus(rs.getString("status"));
				
				columns.add(column);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
		return columns;
	}
	
	public List<NewsColumn> getParentColumn(boolean flag){
		String sql="";
		  if(flag){
			  sql="select * from t_newscolumn where parentId is null";
		   }else{
			   sql="select * from t_newscolumn where parentId is not null";
		   }
			Connection conn=null; PreparedStatement pstmt=null;
			ResultSet rs=null;
			 List<NewsColumn> columns=new ArrayList();
			try {
				conn=DBUtils.getConnection();
				pstmt=conn.prepareStatement(sql);
			    rs=pstmt.executeQuery();
				while(rs.next()){
					NewsColumn column=new NewsColumn();
					column.setId(rs.getInt("id"));
					column.setName(rs.getString("name"));
					column.setStatus(rs.getString("status"));
					columns.add(column);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBUtils.close(rs, pstmt, conn);
			}
			return columns;
	  }

}
