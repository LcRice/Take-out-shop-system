<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配送员订单查询</title>
</head>
<body>
	<h2>配送员订单</h2>
	<hr color="gray"/>
	<c:if test="${requestScope.orderPage!=null }">
	<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>配送员名称</th><th>购买菜名</th><th>商户</th><th>用户名</th><th>状态</th><th>订单时间</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${orderPage.dataList}" var="courierorder">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td align="center">
				${courierorder.greens.rest.courier.couriername }
			</td>
	
			<td>${courierorder.greens.greensname }</td>
	
			<td>${courierorder.greens.rest.restname }</td>
			
			<td>${courierorder.ordersummary.user.username }</td>

			<td>
				<c:if test="${courierorder.orderstatus==1 }">
					未配送
				</c:if>
				<c:if test="${courierorder.orderstatus==2 }">
					已收货
				</c:if>
				<c:if test="${courierorder.orderstatus==3 }">
					正在配送
				</c:if>
				<c:if test="${courierorder.orderstatus==4 }">
					订单完成
				</c:if>
			</td>
			
			<td>${courierorder.ordersummary.ordertime }</td>
			
		</tr>
		    
		<c:set var="row" value="${1-row }"/>
		    
 	</c:forEach>



	<tr>
		<td colspan="6" align="center">
			共${orderPage.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${orderPage.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${orderPage.currentPage}】页/共${orderPage.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="6" align="center">
		
			<c:if test="${orderPage.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${orderPage.currentPage!=1 }">
				<a href="CourierOrderQueryBySuperServlet?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="CourierOrderQueryBySuperServlet?currentPage=${orderPage.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${orderPage.currentPage==orderPage.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${orderPage.currentPage!=orderPage.totalPage }">
				<a href="CourierCourierOrderQueryBySuperServlet?currentPage=${orderPage.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="CourierOrderQueryBySuperServlet?currentPage=${orderPage.totalPage }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="6" align="center">
			<select name="currentPage" onchange="location='CourierOrderQueryBySuperServlet?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${orderPage.totalPage}" step="1">
					<option value="${i}" ${i==orderPage.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
   </table>
</c:if>
<a href="restinfo_query_super.jsp">返回上一页</a>
</body>
</html>