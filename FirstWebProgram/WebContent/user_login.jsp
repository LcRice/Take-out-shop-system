<%@page import="com.neuedu.util.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>

<%
	String usernumber = "";
	String userpassword = "";
	
	//获取名叫userInfo的cookie
	String userInfo = CookieUtil.findCookie(request, "userInfo");    //user1#1
	if(userInfo!=null){
		
		String[] userInfos = userInfo.split("#");
		
		usernumber = userInfos[0];
		userpassword = userInfos[1];
		
	}
%>

	<h2>用户登录</h2>
	<hr color="gray">
	<form action="UserLoginServlet" method="post" onSubmit = "return myfunction()">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="usernumber"></td>
			</tr>
			<tr>
				<td>密 码：</td>
				<td><input type="password" name="userpassword"></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="member" id = "member"><font size="1px" color="gray">记住用户名密码</font></td>
				<td><input type="checkbox" name="autoLogin" id = "autoLogin"><font size="1px" color="gray">七天免登录</font></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input style="margin-right: 50px;" type="submit" value="登陸" >
				<input type="button" value="注冊" onclick="location='user_register.jsp'"></td>
			</tr>
		</table>
	</form>
</body>
</html>