<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看菜</title>
<script type="text/javascript" src="js/greens_query.js"></script>
</head>
<body>
	<c:if test="${requestScope.greensPage==null }">
	<%
		response.sendRedirect("GreensQueryServlet?currentPage=1");
	%>
	</c:if>

<h2>查看菜</h2>
<hr color="gray" />

<c:if test="${requestScope.greensPage!=null }">

<form action="GreensDeleteBatchServlet" method = "post" onsubmit = "return checkCount()">

	<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>选择</th><th>菜照片</th><th>菜名</th><th>菜价格</th><th>菜库存</th><th>餐厅名字</th><th>操作</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${greensPage.dataList}" var="greens">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td><input type = "checkbox" name = "greensids" value="${greens.greensid }"  onclick = "checkSelect()"></td>
		 
		 	<td align="center">
				<img alt="" src="image/photo/${greens.greensphoto }" width="80px" hight="80px"/>
			</td>
	
			<td><a href="GreensFindServlet?greensid=${greens.greensid }">${greens.greensname }</a></td>
	
			<td>${greens.greensprice }</td>
			
			<td>${greens.greensnumber }</td>
			
			<td>${greens.rest.restname }</td>
			
			<td><a href="GreensDeleteServlet?greensid=${greens.greensid }" onclick = "return confirm('是否删除本菜');">删除</a></td>
			
		</tr>
		    
		<c:set var="row" value="${1-row }"/>
		    
 	</c:forEach>



	<tr>
		<td colspan="7" align="center">
			共${greensPage.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${greensPage.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${greensPage.currentPage}】页/共${greensPage.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
		
			<c:if test="${greensPage.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${greensPage.currentPage!=1 }">
				<a href="GreensQueryServlet?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="GreensQueryServlet?currentPage=${greensPage.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${greensPage.currentPage==greensPage.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${greensPage.currentPage!=greensPage.totalPage }">
				<a href="GreensQueryServlet?currentPage=${greensPage.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="GreensQueryServlet?currentPage=${greensPage.totalPage }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
			<select name="currentPage" onchange="location='GreensQueryServlet?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${greensPage.totalPage}" step="1">
					<option value="${i}" ${i==greensPage.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
		
			<c:set var="pageNumber" value="5"/>
			
			<c:forEach var="i" begin="${greensPage.begin}" end="${greensPage.end}" step="1">
				
				<c:if test="${i==greensPage.currentPage}">
					${i}
				</c:if>
				
				<c:if test="${i!=greensPage.currentPage}">
					<a href="GreensQueryServlet?currentPage=${i}">${i}</a>
				</c:if>
	
			</c:forEach>
		</td>
	</tr>
	<tr>
			<td><input type = "checkbox" id = "all" onclick = "selectAll()">全选</td>
			<td colspan="5" align = "center"><input type = "submit" value = "批量删除"></td>
			<td><input type = "button" value = "反选" onclick = "selectReverse()"></td>
		</tr>
	
   </table>
</form>
</c:if>
<br/>
<a href="rest_index.jsp">返回上一页</a>
</body>
</html>