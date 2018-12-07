<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看菜评论</title>
</head>
<body>
	
	<c:if test="${requestScope.greencommentPage==null }">
	<%
		response.sendRedirect("GreensQueryByCommentServlet?currentPage=1&greensid=${requestScope.greensid }");
	%>
	</c:if>

<h2>评论</h2>
<hr color="gray" />

<c:if test="${requestScope.greencommentPage!=null }">
<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>用户编号</th><th>标题</th><th>内容</th><th>发表时间</th><th>评分</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${greencommentPage.dataList}" var="greencomment">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td align="center">
				${greencomment.userid }
			</td>
	
			<td>${greencomment.greencommenttitle }</td>
	
			<td>${greencomment.greencommentcontext }</td>
			
			<td>${greencomment.greencommentpubtime }</td>
			
			<td>
				<c:forEach var="i" begin="1" end="${greencomment.score }">
					<img src="image/star_yellow.gif" />
				</c:forEach> 
				<c:forEach var="i" begin="1" end="${5 - greencomment.score }">
					<img src="image/star_white.gif" />
				</c:forEach>
			</td>
			
		</tr>
		    
		<c:set var="row" value="${1-row }"/>
		    
 	</c:forEach>



	<tr>
		<td colspan="5" align="center">
			共${greencommentPage.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${greencommentPage.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${greencommentPage.currentPage}】页/共${greencommentPage.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="5" align="center">
		
			<c:if test="${greencommentPage.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${greencommentPage.currentPage!=1 }">
				<a href="GreensQueryByCommentServlet?greensid=${requestScope.greensid }&currentPage=1&userorderid=${requestScope.userorderid }">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="GreensQueryByCommentServlet?greensid=${requestScope.greensid }&currentPage=${greencommentPage.currentPage-1}&userorderid=${requestScope.userorderid }">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${greencommentPage.currentPage==greencommentPage.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${greencommentPage.currentPage!=greencommentPage.totalPage }">
				<a href="GreensQueryByCommentServlet?greensid=${requestScope.greensid }&currentPage=${greencommentPage.currentPage+1}&userorderid=${requestScope.userorderid }">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="GreensQueryByCommentServlet?greensid=${requestScope.greensid }&currentPage=${greencommentPage.totalPage }&userorderid=${requestScope.userorderid }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="5" align="center">
			<select name="currentPage" onchange="location='GreensQueryByCommentServlet?greensid=${requestScope.greensid }&currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${greencommentPage.totalPage}" step="1">
					<option value="${i}" ${i==greencommentPage.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
   </table>
</c:if>

<br/>
<a href="user_index.jsp">返回主页</a>
</body>
</html>