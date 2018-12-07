<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看菜</title>
<script type="text/javascript" src="js/greens_query_user.js"></script>
</head>
<body>
<c:if test="${sessionScope.user==null }">
	<%
		String URL = request.getRequestURI();
		String prevURL = URL.substring(URL.lastIndexOf("/")+1);
		session.setAttribute("prevURL", prevURL);
	%>
	<script type="text/javascript">
		alert("尚未登录，请先登录！");
		location="user_login.jsp";
	</script>
</c:if>
<c:if test="${sessionScope.user!=null }">
	<c:if test="${requestScope.usergreensPage==null }">
	<%
		 	response.sendRedirect("GreensQueryByUserServlet");
	%>
	</c:if>
		
	<c:if test="${requestScope.usergreensPage!=null }">
	
	<h2>查看菜</h2>
	<hr color="gray" />
	
	<form action = "GreensQueryByUserServlet" method = "post">
		<input type="text" name="findgreens" value="${requestScope.greensname }" />
		<input type="submit" value="查询" />
	</form>
	<br/>
	<form action="UserShoppingBatchServlet" method = "post" onsubmit = "return checkCount()">
	<table border="1" cellpadding="1" cellspacing="0">
		<tr>
			<th>选择</th>
			<th>菜照片</th>
			<th>菜名</th>
			<th>餐厅</th>
			<th>价格</th>
			<th>库存</th>
			<th>操作</th>
		</tr>

		<c:set var="row" value="0"/>
		<c:forEach var="greens" items="${usergreensPage.dataList }">
		
			<tr bgcolor='${ row == 0 ? "#d0d0d0" : "#ffffff"}'>
			
				<td><input type = "checkbox" name = "greensids" value="${greens.greensid }"  onclick = "checkSelect()"></td>
			
				<td><img src="image/photo/${greens.greensphoto }" width="80px" /></td>
			
				<td><a href="GreensQueryByCommentServlet?currentPage=1&greensid=${greens.greensid }">${myfn:convertKeyword(greens.greensname,greensname)}</a></td>
			
				<td>${greens.rest.restname }</td>
			
				<td>${greens.greensprice }</td>
			
				<td>${greens.greensnumber }</td>
			
				<td><a href="UserShoppingServlet?greensid=${greens.greensid }" onclick = "return checkGreensnumber(${greens.greensid });">加入购物车</a></td>
			</tr>

			<c:set var="row" value="${1-row }" />
		</c:forEach>
		<tr>
			<td colspan="8" align="center">
				<c:if test="${ usergreensPage.currentPage==1 }">
					首页&nbsp;&nbsp;&nbsp;上一页&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${usergreensPage.currentPage!=1 }">
					<a href="GreensQueryByUserServlet?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
					<a href="GreensQueryByUserServlet?currentPage=${usergreensPage.currentPage-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
				</c:if>
				
				<!-- 数字分页  -->
				<c:forEach var="i" begin="${usergreensPage.begin }" end="${usergreensPage.end}" step="1">
					<c:if test="${i==usergreensPage.currentPage}">
						${i }
					</c:if>
					<c:if test="${i!=usergreensPage.currentPage}">
						<a href="GreensQueryByUserServlet?currentPage=${i }" }>
							${i }
						</a>
					</c:if>
				</c:forEach>
				
				
				<c:if test="${usergreensPage.currentPage==usergreensPage.totalPage }">
					下一页&nbsp;&nbsp;&nbsp;尾页
				</c:if>
				<c:if test="${usergreensPage.currentPage!=usergreensPage.totalPage }">
					<a href="GreensQueryByUserServlet?currentPage=${usergreensPage.currentPage+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
					<a href="GreensQueryByUserServlet?currentPage=${usergreensPage.totalPage}">尾页</a>
				</c:if>
				
				&nbsp;&nbsp;&nbsp;到
				<select name="currentPage" onchange="location='GreensQueryByUserServlet?currentPage='+this.value">
					<c:forEach var="i" begin="1" end="${usergreensPage.totalPage}" step="1">
						<option value="${i }" ${i==usergreensPage.currentPage ? "selected" : "" }>${i }</option>
					</c:forEach>
				</select>
				页
			</td>
		</tr>
		
		<tr>
			<td colspan="8" align="center">
				共${usergreensPage.totalCount } 条记录&nbsp;&nbsp;&nbsp;
				每页${usergreensPage.pageSize } 条记录&nbsp;&nbsp;&nbsp;
				第【${usergreensPage.currentPage }】页/共${usergreensPage.totalPage }页
			</td>
		</tr>
		<tr>
			<td><input type = "checkbox" id = "all" onclick = "selectAll()">全选</td>
			<td colspan="5" align = "center"><input type = "submit" value = "购买"></td>
			<td><input type = "button" value = "反选" onclick = "selectReverse()"></td>
		</tr>

	</table>

</form>
	<a href = "UserShoppingQueryServlet?currentPage=1">查看购物车</a><br/>
	<a href = "user_index.jsp">返回主頁</a>

	</c:if>
</c:if>
</body>
</html>