<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>完成订单</title>
</head>
<body>
	<c:if test="${requestScope.orderFinishedPageUser==null }">
	<%
		response.sendRedirect("OrderQueryServletByUser?currentPage=1");
	%>
	</c:if>

<h2>查看订单</h2>
<hr color="gray" />
	<c:if test="${requestScope.orderFinishedPageUser!=null }">

	<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>配送员名称</th><th>菜名</th><th>餐厅名称</th><th>状态</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${orderFinishedPageUser.dataList}" var="orders">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td align="center">
				<a href="CourierCommentQueryServlet?currentPage=1&userorderid=${orders.userorderid }">${orders.greens.rest.courier.couriername }</a>
			</td>
	
			<td>
				<a href="GreensCommentQueryServlet?currentPage=1&userorderid=${orders.userorderid }">${orders.greens.greensname }</a>
			</td>
	
			<td>
				<a href="RestCommentQueryServlet?currentPage=1&userorderid=${orders.userorderid }">${orders.greens.rest.restname }</a>
			</td>
			
			<td>
				订单完成
			</td>

		</tr>
		    
		<c:set var="row" value="${1-row }"/>
		    
 	</c:forEach>



	<tr>
		<td colspan="4" align="center">
			共${orderFinishedPageUser.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${orderFinishedPageUser.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${orderFinishedPageUser.currentPage}】页/共${orderFinishedPageUser.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="4" align="center">
		
			<c:if test="${orderFinishedPageUser.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${orderFinishedPageUser.currentPage!=1 }">
				<a href="OrderQueryServletByUser?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="OrderQueryServletByUser?currentPage=${orderPage.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${orderFinishedPageUser.currentPage==orderFinishedPageUser.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${orderFinishedPageUser.currentPage!=orderFinishedPageUser.totalPage }">
				<a href="OrderQueryServletByUser?currentPage=${orderFinishedPageUser.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="OrderQueryServletByUser?currentPage=${orderFinishedPageUser.totalPage }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="4" align="center">
			<select name="currentPage" onchange="location='OrderQueryServletByUser?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${orderFinishedPageUser.totalPage}" step="1">
					<option value="${i}" ${i==orderFinishedPageUser.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="4" align="center">
		
			<c:set var="pageNumber" value="5"/>
			
			<c:forEach var="i" begin="${orderFinishedPageUser.begin}" end="${orderFinishedPageUser.end}" step="1">
				
				<c:if test="${i==orderFinishedPageUser.currentPage}">
					${i}
				</c:if>
				
				<c:if test="${i!=orderFinishedPageUser.currentPage}">
					<a href="OrderQueryServletByUser?currentPage=${i}">${i}</a>
				</c:if>
	
			</c:forEach>
		</td>
	</tr>
	
   </table>
</c:if>
<br/>
<a href="user_index.jsp">返回上一页</a>
	
</body>
</html>