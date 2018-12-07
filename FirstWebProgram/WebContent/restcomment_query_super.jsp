<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>餐厅评论</title>
</head>
<body>
<h2>餐厅评论</h2>
<hr color="gray"/>
	<c:if test="${requestScope.restcommentPage!=null }">
	<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>用户编号</th><th>标题</th><th>内容</th><th>发表时间</th><th>评分</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${restcommentPage.dataList}" var="restcomment">
		
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
			共${restcommentPage.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${restcommentPage.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${restcommentPage.currentPage}】页/共${restcommentPage.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
		
			<c:if test="${restcommentPage.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${restcommentPage.currentPage!=1 }">
				<a href="RestQueryBySuperServlet?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="RestQueryBySuperServlet?currentPage=${restcommentPage.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${restcommentPage.currentPage==restcommentPage.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${restcommentPage.currentPage!=restcommentPage.totalPage }">
				<a href="RestQueryBySuperServlet?currentPage=${restcommentPage.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="RestQueryBySuperServlet?currentPage=${restcommentPage.totalPage }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
			<select name="currentPage" onchange="location='RestQueryBySuperServlet?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${restcommentPage.totalPage}" step="1">
					<option value="${i}" ${i==restcommentPage.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
   </table>
</c:if>
<a href="restinfo_query_super.jsp">返回上一页</a>
</body>
</html>