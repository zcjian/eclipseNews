package com.zt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zt.dao.NewsColumnDao;
import com.zt.dao.impl.NewsColumnDaoImpl;
import com.zt.po.NewsColumn;
@WebServlet("/admin/column")
public class NewsColumnServlet extends HttpServlet {
	private  NewsColumnDao columnDao;
	public void init(ServletConfig config) throws ServletException {
		columnDao=new NewsColumnDaoImpl();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("add".equals(method)){
			add(request,response);
		}
		if("list".equals(method)){
			list(request,response);
		}
		if("edit".equals(method)){
			edit(request,response);
		}
		if("update".equals(method)){
			update(request,response);
		}
	}
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String parentId=request.getParameter("parentId");
    	String name=request.getParameter("name");
    	NewsColumn column=new NewsColumn();
    	column.setName(name);
    	if("0".equals(parentId)){
    		column.setParent(null);
    	}else{
    		column.setParent(columnDao.getParentColumnById(Integer.parseInt(parentId)));
    	}
    	boolean f=columnDao.addNewsColumn(column);
    	if(f){
    		response.sendRedirect("column?method=list");
    	}else{
    		response.sendRedirect("error.jsp");
    	}
    	
	}
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<NewsColumn> columns=columnDao.findAll();
    	request.setAttribute("columns", columns);
    	request.getRequestDispatcher("columnList.jsp").forward(request, response);
	}
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idStr=request.getParameter("id");
    	int id=0;
    	if(idStr!=null&&!"".equals(idStr)){
    		id=Integer.parseInt(idStr);
    	}
    	NewsColumn column= columnDao.getNewsColumnById(id);
    	if(column==null){
    		column=columnDao.getParentColumnById(id);
    	}
    	request.setAttribute("column", column);
    	request.getRequestDispatcher("columnEdit.jsp").forward(request, response);
    	
   	}
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String parentId=request.getParameter("parentId");
    	String name=request.getParameter("name");
    	String idStr=request.getParameter("id");
    	int id=0;
    	if(idStr!=null&&!"".equals(idStr)){
    		id=Integer.parseInt(idStr);
    	}
    	NewsColumn column= columnDao.getNewsColumnById(id);
    	if(column==null){
    		column=columnDao.getParentColumnById(id);
    	}
    	column.setName(name);
    	if("0".equals(parentId)){
    		column.setParent(null);
    	}else{
    		column.setParent(columnDao.getParentColumnById(Integer.parseInt(parentId)));
    	}
    	boolean f=columnDao.updateNewsColumn(column);
    	if(f){
    		response.sendRedirect("column?method=list");
    	}else{
    		response.sendRedirect("error.jsp");
    	}
   	}

}
