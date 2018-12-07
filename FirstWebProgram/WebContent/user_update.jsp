<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
<script type="text/javascript" src="js/user_update.js"></script>
</head>
<body>
	<c:if test="${sessionScope.user == null }">
	<%
		response.sendRedirect("user_index.jsp");
	%>
	</c:if>
	<c:if test="${sessionScope.user!=null }">
<h2>修改用户信息</h2>
<hr color="gray" />
<form action="UserUpdateServlet" method="post" onsubmit="return checkData();" enctype="multipart/form-data">
<table>
	<tr>
		<td>用户名字</td>
		<td><input type="text" name="username" value="${sessionScope.user.username }" /></td>
	</tr>
	<tr>
		<td>用户头像</td>
		<td><input type="file" name="file" /></td>
	</tr>
	<tr>
		<td>用户用户名</td>
		<td>
			<input type="text" name="usernumber" value="${sessionScope.user.usernumber }" />
			<span id="usernumberResult"></span>
		</td>
	</tr>
	<tr>
		<td>用户密码</td>
		<td><input type="text" name="userpassword" value="${sessionScope.user.userpassword }" /></td>
	</tr>
	<tr>
		<td>绑定卡号</td>
		<td><input type="text" name="usercardnumber" value="${sessionScope.user.usercardnumber }" /></td>
	</tr>
	<tr>
		<td>绑定卡密</td>
		<td><input type="text" name="usercardpassword" value="${sessionScope.user.usercardpassword }" /></td>
	</tr>
	<tr>
		<td>用户地址</td>
		<td><input type="text" name="useraddress" value="${sessionScope.user.useraddress }" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input style="margin-right: 50px;" type="submit" value="确认修改" >
		<input type="reset" value="重置"></td>
	</tr>
</table>
</form>
</c:if>

<br/>
<a href="user_query.jsp">返回上一页</a>
</body>
</html>