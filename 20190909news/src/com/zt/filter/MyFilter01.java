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
@WebFilter("/*")
public class MyFilter01 implements Filter {
	public void destroy() {	
	}
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse respnse =(HttpServletResponse) arg1;
		request.setCharacterEncoding("UTF-8");
		respnse.setCharacterEncoding("UTF-8");
		chain.doFilter(request, respnse);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
