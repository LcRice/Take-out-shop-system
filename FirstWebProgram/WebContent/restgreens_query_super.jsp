<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>根据餐厅查看菜</title>
<script type="text/javascript" src="js/greens_query_user.js"></script>
</head>
<body>

	<c:if test="${requestScope.greensPage==null }">
	<%
		 	response.sendRedirect("GreensQueryServletBySuper?currentPage=1");
	%>
	</c:if>
		
	<c:if test="${requestScope.greensPage!=null }">
	
	<h2>查看菜</h2>
	<hr color="gray" />
	
	<table border="1" cellpadding="1" cellspacing="0">
		<tr>
			<th>菜照片</th>
			<th>菜名</th>
			<th>餐厅</th>
			<th>价格</th>
			<th>库存</th>
			<th>操作</th>
		</tr>

		<c:set var="row" value="0"/>
		<c:forEach var="greens" items="${greensPage.dataList }">
		
			<tr bgcolor='${ row == 0 ? "#d0d0d0" : "#ffffff"}'>
			
				<td><img src="image/photo/${greens.greensphoto }" width="80px" /></td>
			
				<td><a href="GreensQueryByCommentServlet?currentPage=1&greensid=${greens.greensid }">${greens.greensname }</a></td>
			
				<td>${greens.rest.restname }</td>
			
				<td>${greens.greensprice }</td>
			
				<td>${greens.greensnumber }</td>
			
				<td><a href="GreensDeleteServlet?greensid=${greens.greensid }">删除</a></td>
			</tr>

			<c:set var="row" value="${1-row }" />
		</c:forEach>
		<tr>
			<td colspan="6" align="center">
				<c:if test="${ greensPage.currentPage==1 }">
					首页&nbsp;&nbsp;&nbsp;上一页&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${greensPage.currentPage!=1 }">
					<a href="GreensQueryServletBySuper?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
					<a href="GreensQueryServletBySuper?currentPage=${usergreensPage.currentPage-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
				</c:if>
				
				<!-- 数字分页  -->
				<c:forEach var="i" begin="${greensPage.begin }" end="${greensPage.end}" step="1">
					<c:if test="${i==greensPage.currentPage}">
						${i }
					</c:if>
					<c:if test="${i!=greensPage.currentPage}">
						<a href="GreensQueryServletBySuper?currentPage=${i }" }>
							${i }
						</a>
					</c:if>
				</c:forEach>
				
				
				<c:if test="${greensPage.currentPage==greensPage.totalPage }">
					下一页&nbsp;&nbsp;&nbsp;尾页
				</c:if>
				<c:if test="${greensPage.currentPage!=greensPage.totalPage }">
					<a href="GreensQueryServletBySuper?currentPage=${greensPage.currentPage+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
					<a href="GreensQueryServletBySuper?currentPage=${greensPage.totalPage}">尾页</a>
				</c:if>
				
				&nbsp;&nbsp;&nbsp;到
				<select name="currentPage" onchange="location='GreensQueryServletBySuper?currentPage='+this.value">
					<c:forEach var="i" begin="1" end="${greensPage.totalPage}" step="1">
						<option value="${i }" ${i==greensPage.currentPage ? "selected" : "" }>${i }</option>
					</c:forEach>
				</select>
				页
			</td>
		</tr>
		
		<tr>
			<td colspan="6" align="center">
				共${greensPage.totalCount } 条记录&nbsp;&nbsp;&nbsp;
				每页${greensPage.pageSize } 条记录&nbsp;&nbsp;&nbsp;
				第【${greensPage.currentPage }】页/共${greensPage.totalPage }页
			</td>
		</tr>

	</table>
	</c:if>
</body>
</html>