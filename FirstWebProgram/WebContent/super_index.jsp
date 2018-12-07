<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员主页</title>
</head>
<body>
<h2>管理员主页</h2>
<hr color="gray" />
${sessionScope.superadmin.superadminnumber },您好！平台目前盈利${sessionScope.superadmin.superadminamount }元<br/>
<a href="RestQueryServletBySuper?currentPage=1">查看餐厅</a><br/>
<a href="UserQueryServletBySuper?currentPage=1">查看用户</a><br/>
<a href="CourierQueryServletBySuper?currentPage=1">查看配送员</a><br/>
</body>
</html>