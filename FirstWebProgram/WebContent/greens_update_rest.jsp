<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改菜</title>
<script type="text/javascript" src="js/greens_update.js"></script>
</head>
<body>
<c:if test="${sessionScope.oneGreens == null }">
	<%
	response.sendRedirect("GreensFindServlet");
	%>
</c:if>

<c:if test="${sessionScope.oneGreens != null }">
	<h2>修改菜信息</h2>
	<hr color="gray" />
	<form action="GreensUpdateServlet" method="post" onsubmit="return checkData();" enctype="multipart/form-data">
		<table>
			<tr>
				<td>菜名</td>
				<td>
					<input type="text" name="greensname" id="greensname" onblur="checkGreensname()" value="${sessionScope.oneGreens.greensname }" />
					<span id="greensnameResult"></span>
				</td>
			</tr>
			<tr>
				<td>菜价格</td>
				<td>
					<input type="text" name="greensprice" id="greensprice" onblur="checkGreensprice()" value="${sessionScope.oneGreens.greensprice }" />
					<span id="greenspriceResult"></span>	
				</td>
			</tr>
			
			<tr>
				<td align="right">菜照片：</td>
				<td>
					<input type="file" name="file" id="file" value="${sessionScope.oneGreens.greensphoto }">
				</td>
			</tr>
			<tr>
				<td>库存</td>
				<td>
					<input type="text" name="greensnumber" id="greensnumber" onblur="checkGreensnumber()" value="${sessionScope.oneGreens.greensnumber }" />
					<span id="greensnumberResult"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input style="margin-right: 50px;" type="submit" value="修改" />
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	
	</form>
</c:if>
</body>
</html>