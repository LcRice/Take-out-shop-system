<%@page import="com.neuedu.util.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配送员登录</title>

</head>
<body>

<%
	String couriernumber = "";
	String courierpassword = "";
	
	//获取名叫restInfo的cookie
	String courierInfo = CookieUtil.findCookie(request, "courierInfo");    //user1#1
	if(courierInfo!=null){
		
		String[] courierInfos = courierInfo.split("#");
		
		couriernumber = courierInfos[0];
		courierpassword = courierInfos[1];
		
	}
%>

	<h2>配送员登录</h2>
	<hr color="gray">
	<form action="CourierLoginServlet" method="post" onSubmit = "return myfunction()">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="couriernumber"></td>
			</tr>
			<tr>
				<td>密 码：</td>
				<td><input type="password" name="courierpassword"></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="member" id = "member"><font size="1px" color="gray">记住用户名密码</font></td>
				<td><input type="checkbox" name="autoLogin" id = "autoLogin"><font size="1px" color="gray">七天免登录</font></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input style="margin-right: 50px;" type="submit" value="登陸" >
				<input type="button" value="注冊" onclick="location='courier_register.jsp'"></td>
			</tr>
		</table>
	</form>
</body>
</html>