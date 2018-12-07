<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看商户</title>
</head>
<body>

<c:if test="${sessionScope.restsuper!=null }">
<h2>商户信息</h2>
<hr color="gray" />

<table>
	<tr>
		<td>餐厅名称</td>
		<td>${sessionScope.restsuper.restname }</td>
	</tr>
	<tr>
		<td>餐厅用户名</td>
		<td>${sessionScope.restsuper.restnumber }</td>
	</tr>
	<tr>
		<td>餐厅密码</td>
		<td>${sessionScope.restsuper.restpassword }</td>
	</tr>
	<tr>
		<td>绑定卡号</td>
		<td>${sessionScope.restsuper.restcardnumber }</td>
	</tr>
	<tr>
		<td>绑定卡密</td>
		<td>${sessionScope.restsuper.restcardpassword }</td>
	</tr>
	<tr>
		<td>餐厅余额</td>
		<td>${sessionScope.restsuper.restamount }</td>
	</tr>
	<tr>
		<td>餐厅地址</td>
		<td>${sessionScope.restsuper.restaddress }</td>
	</tr>
	<tr>
		<td>评论数量</td>
		<td>${sessionScope.restsuper.restcommentcount }</td>
	</tr>
</table>
</c:if>
<hr color="gray" />
<a href="GreensQueryServletBySuper?currentPage=1">查看该餐厅菜</a><br/>
<a href="RestQueryBySuperServlet?currentPage=1">查看该餐厅评论</a><br/>
<a href="OrderQueryBySuperServlet?currentPage=1">查看该餐厅订单</a><br/>
<br/>
<a href="super_index.jsp">返回主页</a>

</body>
</html>