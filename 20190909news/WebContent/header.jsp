<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
   function login() {
	    var name=document.getElementById("uname").value;
	    var pwd=document.getElementById("upwd").value;
	    if(name==""||pwd==""){
	    	alert("登录名和密码必填");
	    	return false;
	    }else{
	    	return true;
	    }
    }
</script> 
<c:choose>
   <c:when test="${empty loginUser}">
<div id="top_login">
    <form action="user?method=login" method="post" onsubmit="return login()">
    <label> 登录名 </label>
    <input type="text" id="uname" name="uname" class="login_input" />
    <label> 密&#160;&#160;码 </label>
    <input type="password" id="upwd" name="upwd" class="login_input"/>
    <input type="submit" class="login_sub" value="登录"/>
    <label id="error">${error}</label>
    <img src="Images/friend_logo.gif" alt="Google" id="friend_logo" /> 
    </form>
    </div>
  <div id="nav">
    <div id="logo"> <img src="Images/logo.jpg" alt="新闻中国" /> </div>
    <div id="a_b01"> <img src="Images/a_b01.gif" alt="" /> </div>
    <!--mainnav end-->
  </div>
  </c:when>
  <c:otherwise>
    <div id="welcome">欢迎使用新闻管理系统！</div>
  <div id="nav">
    <div id="logo"><img src="Images/logo.jpg" alt="新闻中国" /></div>
    <div id="a_b01"><img src="Images/a_b01.gif" alt="" /></div>
  </div>
</div>
<div id="admin_bar">
  <div id="status">管理员： ${loginUser.name}  &#160;&#160;&#160;&#160; 
  <a href="user?method=out">login out</a> &nbsp;&nbsp;&nbsp;&nbsp;
   <a href="admin/index.jsp">后台管理中心</a>
  </div>
  <div id="channel"> </div>
  </c:otherwise>
</c:choose>