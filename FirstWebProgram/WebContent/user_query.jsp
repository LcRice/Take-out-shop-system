<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看用户</title>
</head>
<body>
<c:if test="${sessionScope.user==null }">
<%
	out.print("<script>alert('用户为空！')</script>");
	response.sendRedirect("user_login.jsp");
%>
</c:if>

<c:if test="${sessionScope.user!=null }">
<h2>用户信息</h2>
<hr color="gray" />

<img alt="" src="image/photo/${sessionScope.user.userphoto }" width="30px" height="30px" />
${sessionScope.user.username },您好！
<br/>

<table>
	<tr>
		<td>用户名</td>
		<td>${sessionScope.user.usernumber }</td>
	</tr>
	<tr>
		<td>用户密码</td>
		<td>${sessionScope.user.userpassword }</td>
	</tr>
	<tr>
		<td>绑定卡号</td>
		<td>${sessionScope.user.usercardnumber }</td>
	</tr>
	<tr>
		<td>绑定卡密</td>
		<td>${sessionScope.user.usercardpassword }</td>
	</tr>
	<tr>
		<td>用户余额</td>
		<td>${sessionScope.user.useramount }</td>
	</tr>
	<tr>
		<td>用户地址</td>
		<td>${sessionScope.user.useraddress }</td>
	</tr>
</table>
</c:if>
<br/>

<a href="user_update.jsp">修改用户信息</a><br/>
<a href="user_index.jsp">返回主页</a>

</body>
</html>