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
<c:if test="${sessionScope.rest==null }">
<%
	out.print("<script>alert('用户为空！')</script>");
	response.sendRedirect("rest_login.jsp");
%>
</c:if>

<c:if test="${sessionScope.rest!=null }">
<h2>用户信息</h2>
<hr color="gray" />

<img alt="" src="image/photo/${sessionScope.rest.restphoto }" width="30px" height="30px" />
${sessionScope.rest.restname },您好！
<br/>

<table>
	<tr>
		<td>餐厅用户名</td>
		<td>${sessionScope.rest.restnumber }</td>
	</tr>
	<tr>
		<td>餐厅密码</td>
		<td>${sessionScope.rest.restpassword }</td>
	</tr>
	<tr>
		<td>绑定卡号</td>
		<td>${sessionScope.rest.restcardnumber }</td>
	</tr>
	<tr>
		<td>绑定卡密</td>
		<td>${sessionScope.rest.restcardpassword }</td>
	</tr>
	<tr>
		<td>餐厅余额</td>
		<td>${sessionScope.rest.restamount }</td>
	</tr>
	<tr>
		<td>餐厅地址</td>
		<td>${sessionScope.rest.restaddress }</td>
	</tr>
	<tr>
		<td>评论数量</td>
		<td>${sessionScope.rest.restcommentcount }</td>
	</tr>
</table>
</c:if>
<br/>

<a href="rest_update.jsp">修改餐厅信息</a><br/>
<a href="rest_index.jsp">返回主页</a>

</body>
</html>