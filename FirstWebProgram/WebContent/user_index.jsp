<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户主页</title>
</head>
<body>
<h2>用户主页</h2>
<hr color="gray" />
<img alt="" src="image/photo/${sessionScope.user.userphoto }" width="30px" height="30px" />
<a href="user_query.jsp">${sessionScope.user.username }</a>,你好！<br/>
<a href="GreensQueryByUserServlet?currentPage=1">查看菜</a><br/>
<a href="RestQueryByUserServlet?currentPage=1">查看餐厅</a><br/>
<hr color="gray" />
<a href="CourierrestQueryServlet?currentPage=1">我的订单</a><br/>
<a href="OrderQueryServletByUser?currentPage=1">评价</a>
<hr color="gray" />
<a href="UserLogoutServlet">退出</a>
</body>
</html>