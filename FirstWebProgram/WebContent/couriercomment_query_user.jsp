<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配送员评论</title>
<script type="text/javascript" src="js/comment_add_user.js"></script>
</head>
<body>
	<c:if test="${sessionScope.user==null }">
		<script type="text/javascript">
			alert("您还未登录，请先登录！");
			location="user_login.jsp";
		</script>
	</c:if>
	<c:if test="${requestScope.couriercommentPage==null }">
	<%
		response.sendRedirect("CourierCommentQueryServlet?currentPage=1&userorderid=${requestScope.userorderid }");
	%>
	</c:if>

<h2>评论</h2>
<hr color="gray" />
<c:if test="${sessionScope.user!=null }">
<c:if test="${requestScope.couriercommentPage!=null }">
<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>用户编号</th><th>标题</th><th>内容</th><th>发表时间</th><th>评分</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${couriercommentPage.dataList}" var="couriercomment">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td align="center">
				${couriercomment.userid }
			</td>
	
			<td>${couriercomment.couriercommenttitle }</td>
	
			<td>${couriercomment.couriercommentcontext }</td>
			
			<td>${couriercomment.couriercommentpubtime }</td>
			
			<td>
				<c:forEach var="i" begin="1" end="${couriercomment.score }">
					<img src="image/star_yellow.gif" />
				</c:forEach> 
				<c:forEach var="i" begin="1" end="${5 - couriercomment.score }">
					<img src="image/star_white.gif" />
				</c:forEach>
			</td>
			
		</tr>
		    
		<c:set var="row" value="${1-row }"/>
		    
 	</c:forEach>



	<tr>
		<td colspan="7" align="center">
			共${couriercommentPage.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${couriercommentPage.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${couriercommentPage.currentPage}】页/共${couriercommentPage.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
		
			<c:if test="${couriercommentPage.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${couriercommentPage.currentPage!=1 }">
				<a href="CourierCommentQueryServlet?currentPage=1&userorderid=${requestScope.userorderid }">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="CourierCommentQueryServlet?currentPage=${couriercommentPage.currentPage-1}&userorderid=${requestScope.userorderid }">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${couriercommentPage.currentPage==couriercommentPage.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${couriercommentPage.currentPage!=couriercommentPage.totalPage }">
				<a href="CourierCommentQueryServlet?currentPage=${couriercommentPage.currentPage+1}&userorderid=${requestScope.userorderid }">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="CourierCommentQueryServlet?currentPage=${couriercommentPage.totalPage }&userorderid=${requestScope.userorderid }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
			<select name="currentPage" onchange="location='CourierCommentQueryServlet?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${couriercommentPage.totalPage}" step="1">
					<option value="${i}" ${i==couriercommentPage.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
		
			<c:set var="pageNumber" value="5"/>
			
			<c:forEach var="i" begin="${couriercommentPage.begin}" end="${couriercommentPage.end}" step="1">
				
				<c:if test="${i==couriercommentPage.currentPage}">
					${i}
				</c:if>
				
				<c:if test="${i!=couriercommentPage.currentPage}">
					<a href="CourierCommentQueryServlet?currentPage=${i}&userorderid=${requestScope.userorderid }">${i}</a>
				</c:if>
	
			</c:forEach>
		</td>
	</tr>
	
   </table>
</c:if>
<br/>
<h2>添加评论</h2>
<hr color="gray"/>
		<form action="CommentAddServletforCourier?userorderid=${requestScope.userorderid }" method="post">
			评论标题
			<input type="text" name="title">
			<br/>
			评论内容
			<textarea name="content" rows="5" cols="30" id="content" onkeyup="checkWordCount()" class="ckeditor"></textarea><br/>
			<br /> 
			还能输入<span id="restWordCount">20</span>个字
			<br/>
			评论打分
  				<c:forEach var="i" begin="1" end="5">
				 <img src="image/star_white.gif" id="star${i}" 
				 		onmousemove="enter(${i})" 
				 		onmouseout="leave(${i})"
				 		onclick="select(${i})"/>
			  </c:forEach>
			  
			<input type="hidden" name="score" id="score" value="1"/>
 <br />

			<input type="submit" value="提交" />
		</form>
	</c:if>
	<br/>
	<a href="user_index.jsp">返回主页</a>
</body>
</html>