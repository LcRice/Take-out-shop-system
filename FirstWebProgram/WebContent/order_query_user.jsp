<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
</head>
<body>
	<c:if test="${requestScope.orderPageUser==null }">
	<%
		response.sendRedirect("CourierrestQueryServlet?currentPage=1");
	%>
	</c:if>

<h2>查看订单</h2>
<hr color="gray" />
	<c:if test="${requestScope.orderPageUser!=null }">

	<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>配送员名称</th><th>菜名</th><th>餐厅名称</th><th>状态</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${orderPageUser.dataList}" var="orders">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td align="center">
				${orders.greens.rest.courier.couriername }
			</td>
	
			<td>${orders.greens.greensname }</td>
	
			<td>${orders.greens.rest.restname }</td>
			
			<td>
				<c:if test="${orders.orderstatus==1 }">
					未配送
				</c:if>
				<c:if test="${orders.orderstatus==2 }">
					<a href="UserGetGreensServlet?userorderid=${orders.userorderid }">确认收货</a>
				</c:if>
				<c:if test="${orders.orderstatus==3 }">
					正在配送
				</c:if>
				<c:if test="${orders.orderstatus==4 }">
					订单完成
				</c:if>
			</td>

		</tr>
		    
		<c:set var="row" value="${1-row }"/>
		    
 	</c:forEach>



	<tr>
		<td colspan="4" align="center">
			共${orderPageUser.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${orderPageUser.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${orderPageUser.currentPage}】页/共${orderPageUser.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="4" align="center">
		
			<c:if test="${orderPageUser.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${orderPageUser.currentPage!=1 }">
				<a href="CourierrestQueryServlet?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="CourierrestQueryServlet?currentPage=${orderPage.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${orderPageUser.currentPage==orderPageUser.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${orderPageUser.currentPage!=orderPageUser.totalPage }">
				<a href="CourierrestQueryServlet?currentPage=${orderPageUser.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="CourierrestQueryServlet?currentPage=${orderPageUser.totalPage }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="4" align="center">
			<select name="currentPage" onchange="location='CourierrestQueryServlet?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${orderPageUser.totalPage}" step="1">
					<option value="${i}" ${i==orderPageUser.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="4" align="center">
		
			<c:set var="pageNumber" value="5"/>
			
			<c:forEach var="i" begin="${orderPageUser.begin}" end="${orderPageUser.end}" step="1">
				
				<c:if test="${i==orderPageUser.currentPage}">
					${i}
				</c:if>
				
				<c:if test="${i!=orderPageUser.currentPage}">
					<a href="CourierrestQueryServlet?currentPage=${i}">${i}</a>
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