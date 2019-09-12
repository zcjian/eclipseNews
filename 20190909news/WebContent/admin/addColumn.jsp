<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../tag.jsp"%>  
 <%@page import="com.zt.po.NewsColumn,java.util.*"%>
  <%@page import="com.zt.dao.NewsColumnDao,com.zt.dao.impl.NewsColumnDaoImpl" %>
 <%
   NewsColumnDao columnDao=new NewsColumnDaoImpl();
   List<NewsColumn> columns=columnDao.getParentColumn(true);
   pageContext.setAttribute("columns", columns);
  %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加主题--管理后台</title>
<link href="CSS/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
    <%@ include file="../header.jsp" %>
</div>
<div id="main">
  <div id="opt_list">
       <%@ include file="nav.jsp" %>
  </div>
  <div id="opt_area">
     <h1 id="opt_type"> 添加栏目： </h1>
    <form action="admin/column?method=add" method="post">
      <p>
        <label>父级栏目 </label>
        <select name="parentId">
          <option value="0">无</option>
          <c:forEach items="${columns}" var="c">
          <option value='${c.id}'> ${c.name } </option>
         </c:forEach>
        </select>
      </p>
      <p>
        <label> 栏目名称 </label>
        <input name="name" type="text" class="opt_input" />
      </p>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>    
  </div>
</div>
<div id="site_link"> <a href="#">关于我们</a><span>|</span> <a href="#">Aboue Us</a><span>|</span> <a href="#">联系我们</a><span>|</span> <a href="#">广告服务</a><span>|</span> <a href="#">供稿服务</a><span>|</span> <a href="#">法律声明</a><span>|</span> <a href="#">招聘信息</a><span>|</span> <a href="#">网站地图</a><span>|</span> <a href="#">留言反馈</a> </div>
<div id="footer">
        <%@ include file="../footer.jsp" %>
</div>
</body>
</html>
