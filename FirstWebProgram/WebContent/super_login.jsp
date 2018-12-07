<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
</head>
<body>
<h2>管理员登录</h2>
<hr color="gray" />
<form action="SuperLoginServlet" method="post" onSubmit = "return myfunction()">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="supernumber"></td>
			</tr>
			<tr>
				<td>密 码：</td>
				<td><input type="password" name="superpassword"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input style="margin-right: 50px;" type="submit" value="登陸" >
				<input type="reset" value="重置"></td>
			</tr>
		</table>
	</form>
</body>
</html>