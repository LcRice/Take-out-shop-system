<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>餐厅主页</title>
</head>
<body>
<img alt="" src="image/photo/${sessionScope.rest.restphoto }" width="30px" height="30px" />
<a href="rest_query.jsp">${sessionScope.rest.restname }</a>,hello!
<hr color="gray">
<a href="greens_add.jsp">上架菜品</a><br/>
<a href="GreensQueryServlet?currentPage=1">查询菜品</a><br/>

<a href="OrderQueryServlet?currentPage=1">查看订单</a><br/>
<a href="AutoSelectCourierServlet">自动派单</a>

<hr color="gray">

<a href="CommentQueryServletforRestByRest?currentPage=1">查看评论信息</a><br/>

<hr color="gray">
<a href="RestLogoutServlet">退出</a>
</body>
</html>