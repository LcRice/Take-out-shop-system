<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看配送员</title>
</head>
<body>

<c:if test="${sessionScope.couriersuper!=null }">
<h2>配送员信息</h2>
<hr color="gray" />

<table>
	<tr>
		<td>配送员名称</td>
		<td>${sessionScope.couriersuper.couriername }</td>
	</tr>
	<tr>
		<td>配送员用户名</td>
		<td>${sessionScope.couriersuper.couriernumber }</td>
	</tr>
	<tr>
		<td>配送员密码</td>
		<td>${sessionScope.couriersuper.courierpassword }</td>
	</tr>
	<tr>
		<td>配送员工资</td>
		<td>${sessionScope.couriersuper.couriercamount }</td>
	</tr>
	<tr>
		<td>配送员状态</td>
		<td>${sessionScope.couriersuper.courierstatus }</td>
	</tr>
	<tr>
		<td>配送员评论数</td>
		<td>${sessionScope.couriersuper.couriercommentcount }</td>
	</tr>
	<tr>
		<td>配送员好评数</td>
		<td>${sessionScope.couriersuper.courierwellreceived }</td>
	</tr>
</table>
</c:if>
<hr color="gray" />
<a href="CourierOrderQueryBySuperServlet?currentPage=1">查看该配送员订单</a><br/>
<a href="CourierCommentQueryBySuperServlet?currentPage=1">查看该配送员评论</a><br/>
<br/>
<a href="super_index.jsp">返回主页</a>

</body>
</html>