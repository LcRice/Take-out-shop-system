<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改餐厅信息</title>
<script type="text/javascript" src="js/rest_update.js"></script>
</head>
<body>
	<c:if test="${sessionScope.rest == null }">
	<%
		response.sendRedirect("rest_index.jsp");
	%>
	</c:if>
	<c:if test="${sessionScope.rest!=null }">
<h2>修改用户信息</h2>
<hr color="gray" />
<form action="RestUpdateServlet" method="post" onsubmit="return checkData();" enctype="multipart/form-data">
<table>
	<tr>
		<td>餐厅名字</td>
		<td><input type="text" name="restname" value="${sessionScope.rest.restname }" /></td>
	</tr>
	<tr>
		<td>餐厅头像</td>
		<td><input type="file" name="file" /></td>
	</tr>
	<tr>
		<td>餐厅用户名</td>
		<td>
			<input type="text" name="restnumber" value="${sessionScope.rest.restnumber }" />
			<span id="restnumberResult"></span>
		</td>
	</tr>
	<tr>
		<td>餐厅密码</td>
		<td><input type="text" name="restpassword" value="${sessionScope.rest.restpassword }" /></td>
	</tr>
	<tr>
		<td>绑定卡号</td>
		<td><input type="text" name="restcardnumber" value="${sessionScope.rest.restcardnumber }" /></td>
	</tr>
	<tr>
		<td>绑定卡密</td>
		<td><input type="text" name="restcardpassword" value="${sessionScope.rest.restcardpassword }" /></td>
	</tr>
	<tr>
		<td>餐厅地址</td>
		<td><input type="text" name="restaddress" value="${sessionScope.rest.restaddress }" /></td>
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
<a href="rest_query.jsp">返回上一页</a>
</body>
</html>