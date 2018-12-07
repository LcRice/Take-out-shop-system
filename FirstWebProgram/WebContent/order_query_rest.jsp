<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>餐厅查看订单</title>
<script type="text/javascript" src="js/greens_query.js"></script>
</head>
<body>
	<c:if test="${requestScope.orderPage==null }">
	<%
		response.sendRedirect("OrderQueryServlet?currentPage=1");
	%>
	</c:if>

<h2>查看订单</h2>
<hr color="gray" />

<c:if test="${requestScope.orderPage!=null }">

	<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>客户名称</th><th>菜名</th><th>购买数量</th><th>客户地址</th><th>下单时间</th><th>下单状态</th><th>操作</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${orderPage.dataList}" var="orders">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td align="center">
				${orders.ordersummary.user.username }
			</td>
	
			<td>${orders.greens.greensname }</td>
	
			<td>${orders.count }</td>
			
			<td>${orders.ordersummary.user.useraddress }</td>
			
			<td>${orders.ordersummary.ordertime }</td>
			
			<td>
				<c:if test="${orders.orderstatus==1 }">
					未配送
				</c:if>
				<c:if test="${orders.orderstatus==2 }">
					已配送
				</c:if>
				<c:if test="${orders.orderstatus==3 }">
					正在配送
				</c:if>
				<c:if test="${orders.orderstatus==4 }">
					订单完成
				</c:if>
			</td>

			<td>
				<c:if test="${orders.orderstatus==1 }">
					<a href="CourierQueryServlet?currentPage=1&userorderid=${orders.userorderid }" onclick = "return confirm('是否配送');">配送</a>
				</c:if>
				<c:if test="${orders.orderstatus==2 }">
					已配送
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
		<td colspan="7" align="center">
			共${orderPage.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${orderPage.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${orderPage.currentPage}】页/共${orderPage.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
		
			<c:if test="${orderPage.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${orderPage.currentPage!=1 }">
				<a href="OrderQueryServlet?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="OrderQueryServlet?currentPage=${orderPage.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${orderPage.currentPage==orderPage.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${orderPage.currentPage!=orderPage.totalPage }">
				<a href="OrderQueryServlet?currentPage=${orderPage.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="OrderQueryServlet?currentPage=${orderPage.totalPage }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
			<select name="currentPage" onchange="location='OrderQueryServlet?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${orderPage.totalPage}" step="1">
					<option value="${i}" ${i==orderPage.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
		
			<c:set var="pageNumber" value="5"/>
			
			<c:forEach var="i" begin="${orderPage.begin}" end="${orderPage.end}" step="1">
				
				<c:if test="${i==orderPage.currentPage}">
					${i}
				</c:if>
				
				<c:if test="${i!=orderPage.currentPage}">
					<a href="OrderQueryServlet?currentPage=${i}">${i}</a>
				</c:if>
	
			</c:forEach>
		</td>
	</tr>
	
   </table>
</c:if>
<br/>
<a href="rest_index.jsp">返回上一页</a>
</body>
</html>