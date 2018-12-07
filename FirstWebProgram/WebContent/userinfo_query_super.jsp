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

<c:if test="${sessionScope.usersuper!=null }">
<h2>用户信息</h2>
<hr color="gray" />

<table>
	<tr>
		<td>用户名称</td>
		<td>${sessionScope.usersuper.username }</td>
	</tr>
	<tr>
		<td>用户用户名</td>
		<td>${sessionScope.usersuper.usernumber }</td>
	</tr>
	<tr>
		<td>用户密码</td>
		<td>${sessionScope.usersuper.userpassword }</td>
	</tr>
	<tr>
		<td>绑定卡号</td>
		<td>${sessionScope.usersuper.usercardnumber }</td>
	</tr>
	<tr>
		<td>绑定卡密</td>
		<td>${sessionScope.usersuper.usercardpassword }</td>
	</tr>
	<tr>
		<td>用户余额</td>
		<td>${sessionScope.usersuper.useramount }</td>
	</tr>
	<tr>
		<td>用户地址</td>
		<td>${sessionScope.usersuper.useraddress }</td>
	</tr>
</table>
</c:if>
<hr color="gray" />
<a href="UserOrderQueryBySuperServlet?currentPage=1">查看该用户订单</a><br/>
<br/>
<a href="super_index.jsp">返回主页</a>

</body>
</html>