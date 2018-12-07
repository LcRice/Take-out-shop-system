<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>餐厅查看评论</title>
<script type="text/javascript" src="js/comment_add_user.js"></script>
</head>
<body>
	<c:if test="${sessionScope.rest==null }">
		<script type="text/javascript">
			alert("您还未登录，请先登录！");
			location="rest_login.jsp";
		</script>
	</c:if>
	<c:if test="${requestScope.restcommentPageByRest==null }">
	<%
		response.sendRedirect("CommentQueryServletforRestByRest?currentPage=1");
	%>
	</c:if>

<h2>评论</h2>
<hr color="gray" />
<c:if test="${sessionScope.rest!=null }">
<c:if test="${requestScope.restcommentPageByRest!=null }">
<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>用户编号</th><th>标题</th><th>内容</th><th>发表时间</th><th>评分</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${restcommentPageByRest.dataList}" var="restcomment">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td align="center">
				${restcomment.userid }
			</td>
	
			<td>${restcomment.restcommenttitle }</td>
	
			<td>${restcomment.restcommentcontext }</td>
			
			<td>${restcomment.restcommentpubtime }</td>
			
			<td>
				<c:forEach var="i" begin="1" end="${restcomment.score }">
					<img src="image/star_yellow.gif" />
				</c:forEach> 
				<c:forEach var="i" begin="1" end="${5 - restcomment.score }">
					<img src="image/star_white.gif" />
				</c:forEach>
			</td>
			
		</tr>
		    
		<c:set var="row" value="${1-row }"/>
		    
 	</c:forEach>



	<tr>
		<td colspan="7" align="center">
			共${restcommentPageByRest.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${restcommentPageByRest.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${restcommentPageByRest.currentPage}】页/共${restcommentPageByRest.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
		
			<c:if test="${restcommentPageByRest.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${restcommentPageByRest.currentPage!=1 }">
				<a href="CommentQueryServletforRestByRest?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="CommentQueryServletforRestByRest?currentPage=${restcommentPageByRest.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${restcommentPageByRest.currentPage==restcommentPageByRest.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${restcommentPageByRest.currentPage!=restcommentPageByRest.totalPage }">
				<a href="CommentQueryServletforRestByRest?currentPage=${restcommentPageByRest.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="CommentQueryServletforRestByRest?currentPage=${restcommentPageByRest.totalPage }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
			<select name="currentPage" onchange="location='CommentQueryServletforRestByRest?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${restcommentPageByRest.totalPage}" step="1">
					<option value="${i}" ${i==restcommentPageByRest.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
		
			<c:set var="pageNumber" value="5"/>
			
			<c:forEach var="i" begin="${restcommentPageByRest.begin}" end="${restcommentPageByRest.end}" step="1">
				
				<c:if test="${i==restcommentPageByRest.currentPage}">
					${i}
				</c:if>
				
				<c:if test="${i!=restcommentPageByRest.currentPage}">
					<a href="CommentQueryServletforRestByRest?currentPage=${i}">${i}</a>
				</c:if>
	
			</c:forEach>
		</td>
	</tr>
	
   </table>
</c:if>
</c:if>

<br/>
<a href="rest_index.jsp">返回主页</a>
</body>
</html>