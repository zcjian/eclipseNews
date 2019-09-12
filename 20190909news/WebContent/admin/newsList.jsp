<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../tag.jsp"%>   
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
     <h1 id="opt_type"> 新闻列表： </h1>
       <form action="admin/news?method=list" method="post">
         <table width="500px" cellpadding="0" cellspacing="0">
            <tr>
            <td>栏目
              <select name="columnId">
                 <option value="0">全部</option>
                 <c:forEach items="${columns}" var="c">
                     <option value="${c.id}" <c:if test="${c.id==filter['columnId']}">selected="selected"</c:if>>${c.name}</option>
                     
                 </c:forEach>
              </select></td>
            <td>标题   <input type="text" name="title" value="${filter['title']}"/>
              </td>
           
             <td>发布人
              <select name="userId">
                <option value="0">全部</option>
                 <c:forEach items="${userList}" var="user">
                     <option value="${user.id}" <c:if test="${user.id==filter['userId']}">selected="selected"</c:if>>${user.name}</option>
                 </c:forEach>
              </select></td>        
             <td>
                   <input type="submit" value="查询"/>
             </td>   
              </tr>      
         </table>
         </form>
          <ul class="classlist">
          <c:forEach items="${newsList}" var="news">
             <li> ${news.title } <span> 作者：
        ${news.newsUser.name }                                       
        &#160;&#160;&#160;&#160; <a href='#'>修改</a> 
        &#160;&#160;&#160;&#160; 
        <a href='#' onclick='return clickdel()'>删除</a> </span> </li>
         
       </c:forEach>
          <li class='space'></li>
              <p align="right">
                    当前页数:[1/3]&nbsp; <a href="#">下一页</a> <a href="#">末页</a> 
              </p>
       
        </ul>
        
  </div>
</div>
<div id="site_link"> <a href="#">关于我们</a><span>|</span> <a href="#">Aboue Us</a><span>|</span> <a href="#">联系我们</a><span>|</span> <a href="#">广告服务</a><span>|</span> <a href="#">供稿服务</a><span>|</span> <a href="#">法律声明</a><span>|</span> <a href="#">招聘信息</a><span>|</span> <a href="#">网站地图</a><span>|</span> <a href="#">留言反馈</a> </div>
<div id="footer">
        <%@ include file="../footer.jsp" %>
</div>
</body>
</html>
