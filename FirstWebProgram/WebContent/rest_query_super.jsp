<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看餐厅</title>
</head>
<body>
<c:if test="${requestScope.restPage==null }">
	<%
		response.sendRedirect("RestQueryServletBySuper?currentPage=1");
	%>
	</c:if>

<h2>查看商家</h2>
<hr color="gray" />

<c:if test="${requestScope.restPage!=null }">

	<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>商户名称</th><th>商户地址</th><th>商户评论数</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${restPage.dataList}" var="rests">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td align="center">
		 		<a href="RestInfoQueryServlet?restid=${rests.restid }">${rests.restname }</a>
			</td>
	
			<td>${rests.restaddress }</td>
	
			<td>${rests.restcommentcount }</td>
			
		</tr>
		    
		<c:set var="row" value="${1-row }"/>
		    
 	</c:forEach>



	<tr>
		<td colspan="3" align="center">
			共${restPage.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${restPage.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${restPage.currentPage}】页/共${restPage.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="3" align="center">
		
			<c:if test="${restPage.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${restPage.currentPage!=1 }">
				<a href="RestQueryServletBySuper?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="RestQueryServletBySuper?currentPage=${restPage.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${restPage.currentPage==restPage.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${restPage.currentPage!=restPage.totalPage }">
				<a href="RestQueryServletBySuper?currentPage=${restPage.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="RestQueryServletBySuper?currentPage=${restPage.totalPage }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="3" align="center">
			<select name="currentPage" onchange="location='RestQueryServletBySuper?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${restPage.totalPage}" step="1">
					<option value="${i}" ${i==restPage.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
   </table>
</c:if>
<br/>
<a href="super_index.jsp">返回上一页</a>
</body>
</html>