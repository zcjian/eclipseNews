<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="tag.jsp"%> 
<%@ page import="com.zt.po.News,com.zt.dao.NewsDao,com.zt.dao.impl.NewsDaoImpl" %> 
<%@ page import="java.util.*,com.zt.utils.PageUtils"%> 
 <%   
      NewsDao newsDao=new NewsDaoImpl();
       Map filter20=new HashMap();
       PageUtils pageUtils=new PageUtils();
       pageUtils.setCurrPage(1);pageUtils.setPageSize(20);
       List<News> newsList20=newsDao.searchNewsByPage(filter20, pageUtils);
       
       Map filter1=new HashMap();
       filter1.put("columnId", 1);
       PageUtils pageUtils2=new PageUtils();
       pageUtils2.setCurrPage(1);pageUtils2.setPageSize(5);
       List<News> newsList15=newsDao.searchNewsByPage(filter1, pageUtils2);
       
       Map filter5=new HashMap();
       filter5.put("columnId", 5);
       PageUtils pageUtils3=new PageUtils();
       pageUtils3.setCurrPage(1);pageUtils3.setPageSize(5);
       List<News> newsList55=newsDao.searchNewsByPage(filter5, pageUtils3);
       
       pageContext.setAttribute("newsList20", newsList20);
       pageContext.setAttribute("newsList15", newsList15);
       pageContext.setAttribute("newsList55", newsList55);
       
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新闻中国</title>
<link href="CSS/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
  <%@ include file="header.jsp"%>
</div>
<div id="container">
  <div class="sidebar">
    <h1> <img src="Images/title_1.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list">
      <ul>
        <c:forEach items="${newsList15}" var="news">
        <li> <a href='#'><b>${news.title} </b></a> </li>
       </c:forEach>
      </ul>
    </div>
    <h1> <img src="Images/title_2.gif" alt="体育新闻" /> </h1>
    <div class="side_list">
      <ul>
        <c:forEach items="${newsList55}" var="news">
        <li> <a href='#'><b>${news.title} </b></a> </li>
       </c:forEach>
      </ul>
    </div>
    
  </div>
  <div class="main">
    <div class="class_type"> <img src="Images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date">
        <li id='class_month'> <a href='#'><b> 国内 </b></a> <a href='#'><b> 国际 </b></a> <a href='#'><b> 军事 </b></a> <a href='#'><b> 体育 </b></a> <a href='#'><b> 娱乐 </b></a> <a href='#'><b> 社会 </b></a> <a href='#'><b> 财经 </b></a> <a href='#'><b> 科技 </b></a> <a href='#'><b> 健康 </b></a> <a href='#'><b> 汽车 </b></a> <a href='#'><b> 教育 </b></a> </li>
        <li id='class_month'> <a href='#'><b> 房产 </b></a> <a href='#'><b> 家居 </b></a> <a href='#'><b> 旅游 </b></a> <a href='#'><b> 文化 </b></a> <a href='#'><b> 其他 </b></a> </li>
      </ul>
      <ul class="classlist">
       <c:forEach items="${newsList20}" var="news" varStatus="i">
        <li><a href='#'> ${news.title}  </a><span> ${news.createTime} </span></li>
        <c:if test="${i.count%5==0}">
            <li class='space'></li>
        </c:if>
       </c:forEach>    

      </ul>
    </div>
    <div class="picnews">
      <ul>
        <li> <a href="#"><img src="Images/Picture1.jpg" width="249" alt="" /> </a><a href="#">幻想中穿越时空</a> </li>
        <li> <a href="#"><img src="Images/Picture2.jpg" width="249" alt="" /> </a><a href="#">国庆多变的发型</a> </li>
        <li> <a href="#"><img src="Images/Picture3.jpg" width="249" alt="" /> </a><a href="#">新技术照亮都市</a> </li>
        <li> <a href="#"><img src="Images/Picture4.jpg" width="249" alt="" /> </a><a href="#">群星闪耀红地毯</a> </li>
      </ul>
    </div>
  </div>
</div>
<div id="friend">
  <h1 class="friend_t"> <img src="Images/friend_ico.gif" alt="合作伙伴" /> </h1>
  <div class="friend_list">
    <ul>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
    </ul>
  </div>
</div>
<div id="footer">
     <%@ include file="footer.jsp"%>
</div>
</body>
</html>
