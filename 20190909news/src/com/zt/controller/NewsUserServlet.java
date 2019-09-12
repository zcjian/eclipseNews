package com.zt.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zt.dao.NewsUserDao;
import com.zt.dao.impl.NewsUserDaoImpl;
import com.zt.po.NewsUser;

@WebServlet("/user")
public class NewsUserServlet extends HttpServlet {
	private NewsUserDao newsUserDao;
	public void init(ServletConfig config) throws ServletException {
		newsUserDao=new NewsUserDaoImpl();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("login".equals(method)){
			login(request,response);
		}
		if("out".equals(method)){
			out(request,response);
		}
	}
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String uname=request.getParameter("uname");
    	String upwd=request.getParameter("upwd");
    	NewsUser user=newsUserDao.login(uname);
    	if(user!=null){
    		if(upwd.equals(user.getPass())){
    			//存session 跳成功
    			 HttpSession session=request.getSession();
    			 session.setAttribute("loginUser", user);
    		}else{
    			request.setAttribute("error", "密码输入错误");
    		}
    	}else{
    		request.setAttribute("error", "用户名不存在");
    	}
    	request.getRequestDispatcher("index.jsp").forward(request, response);
	}
    protected void out(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session=request.getSession();
    	 session.invalidate();
    	 response.sendRedirect("index.jsp");
   	}

}
