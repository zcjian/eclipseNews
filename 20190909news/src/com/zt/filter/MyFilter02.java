package com.zt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter("/admin/*")
public class MyFilter02 implements Filter {
	public void destroy() {
		
	}
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		  HttpServletRequest request=(HttpServletRequest) arg0;
		  HttpServletResponse response=(HttpServletResponse) arg1;
		  HttpSession session=request.getSession();
		  if(session.getAttribute("loginUser")==null){
			  response.sendRedirect("../index.jsp");
		  }else{
			  chain.doFilter(request, response);
		  }
	}
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
