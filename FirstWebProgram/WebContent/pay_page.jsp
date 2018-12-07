<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>支付</h2>
	<hr color="gray" />

	<form action="PayCheckServlet" method="post">
		<table>
			<tr>
				<td>卡号</td>
				<td><input type="text" name="usercardnumber" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="usercardpassword" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="付款">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>