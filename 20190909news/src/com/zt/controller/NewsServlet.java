package com.zt.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zt.dao.NewsColumnDao;
import com.zt.dao.NewsDao;
import com.zt.dao.NewsUserDao;
import com.zt.dao.impl.NewsColumnDaoImpl;
import com.zt.dao.impl.NewsDaoImpl;
import com.zt.dao.impl.NewsUserDaoImpl;
import com.zt.po.News;
import com.zt.po.NewsColumn;
import com.zt.po.NewsUser;
import com.zt.utils.PageUtils;

@WebServlet("/admin/news")
public class NewsServlet extends HttpServlet {
	private NewsColumnDao columnDao;
	private NewsDao newsDao;
	private NewsUserDao userDao;
    public void init(ServletConfig config) throws ServletException {
    	columnDao=new NewsColumnDaoImpl();
    	newsDao=new NewsDaoImpl();
    	userDao=new NewsUserDaoImpl();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("add".equals(method)){
			add(request,response);
		}
		if("list".equals(method)){
			list(request,response);
		}
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String columnId=request.getParameter("columnId");
		String title=request.getParameter("title");
		String summary=request.getParameter("summary");
		String content=request.getParameter("content");
		NewsColumn column=columnDao.getNewsColumnById(Integer.parseInt(columnId));
		News news=new News();
		news.setTitle(title);
		news.setSummary(summary);
		news.setContent(content);
		news.setNewsColumn(column);
	    HttpSession session=request.getSession();
	    NewsUser user=(NewsUser) session.getAttribute("loginUser");
		news.setNewsUser(user);	
		boolean f=newsDao.addNews(news);
		if(f){
			response.sendRedirect("news?method=list");
		}else{
			response.sendRedirect("error.jsp");
		}
		
	}
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map filter=new HashMap();
		String columnId=request.getParameter("columnId");
		String title=request.getParameter("title");
		String userId=request.getParameter("userId");
		if(columnId!=null&&!"0".equals(columnId)){
			filter.put("columnId", Integer.parseInt(columnId));
		}
		if(title!=null&&!"".equals(title)){
			filter.put("title", title);
		}
		if(userId!=null&&!"0".equals(userId)){
			filter.put("userId", Integer.parseInt(userId));
		}
		int totalSize=newsDao.getTotalSize(filter);
		PageUtils pageUtils =new PageUtils();
		//pageUtils.setPageSize(20);
		int currPage=1;
		pageUtils.setCurrPage(currPage);
		pageUtils.setTotalSize(totalSize);
		pageUtils.setTotalPage(totalSize);
		
		List<News> newsList=newsDao.searchNewsByPage(filter, pageUtils);
		request.setAttribute("filter", filter);
		request.setAttribute("pageUtils", pageUtils);
		request.setAttribute("newsList", newsList);
	
		 List<NewsColumn> columns=columnDao.findAll();
		 List<NewsUser>  userList=userDao.findAll();
		 request.setAttribute("columns", columns);
		 request.setAttribute("userList", userList);
		request.getRequestDispatcher("newsList.jsp").forward(request, response);
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
