<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>根据餐厅查看菜</title>
<script type="text/javascript" src="js/greens_query_user.js"></script>
</head>
<body>
<c:if test="${sessionScope.user==null }">
	<%
		String URL = request.getRequestURI();
		String prevURL = URL.substring(URL.lastIndexOf("/")+1);
		session.setAttribute("prevURL", prevURL);
	%>
	<script type="text/javascript">
		alert("尚未登录，请先登录！");
		location="user_login.jsp";
	</script>
</c:if>
<c:if test="${sessionScope.user!=null }">
	<c:if test="${requestScope.greensPage==null }">
	<%
		 	response.sendRedirect("GreensQueryByRestServlet?currentPage=1&currentPage1=1&restid=${sessionScope.restgreensid }");
	%>
	</c:if>
		
	<c:if test="${requestScope.greensPage!=null }">
	
	<h2>查看菜</h2>
	<hr color="gray" />
	
	<table border="1" cellpadding="1" cellspacing="0">
		<tr>
			<th>菜照片</th>
			<th>菜名</th>
			<th>餐厅</th>
			<th>价格</th>
			<th>库存</th>
			<th>操作</th>
		</tr>

		<c:set var="row" value="0"/>
		<c:forEach var="greens" items="${greensPage.dataList }">
		
			<tr bgcolor='${ row == 0 ? "#d0d0d0" : "#ffffff"}'>
			
				<td><img src="image/photo/${greens.greensphoto }" width="80px" /></td>
			
				<td><a href="GreensQueryByCommentServlet?currentPage=1&greensid=${greens.greensid }">${myfn:convertKeyword(greens.greensname,greensname)}</a></td>
			
				<td>${greens.rest.restname }</td>
			
				<td>${greens.greensprice }</td>
			
				<td>${greens.greensnumber }</td>
			
				<td><a href="UserShoppingServlet?greensid=${greens.greensid }" onclick = "return checkGreensnumber(${greens.greensid });">加入购物车</a></td>
			</tr>

			<c:set var="row" value="${1-row }" />
		</c:forEach>
		<tr>
			<td colspan="6" align="center">
				<c:if test="${ greensPage.currentPage==1 }">
					首页&nbsp;&nbsp;&nbsp;上一页&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${greensPage.currentPage!=1 }">
					<a href="GreensQueryByRestServlet?currentPage=1&currentPage1=1&restid=${sessionScope.restgreensid }">首页</a>&nbsp;&nbsp;&nbsp;
					<a href="GreensQueryByRestServlet?currentPage=${usergreensPage.currentPage-1 }&currentPage1=1&restid=${sessionScope.restgreensid }">上一页</a>&nbsp;&nbsp;&nbsp;
				</c:if>
				
				<!-- 数字分页  -->
				<c:forEach var="i" begin="${greensPage.begin }" end="${greensPage.end}" step="1">
					<c:if test="${i==greensPage.currentPage}">
						${i }
					</c:if>
					<c:if test="${i!=greensPage.currentPage}">
						<a href="GreensQueryByRestServlet?currentPage=${i }&currentPage1=1&restid=${sessionScope.restgreensid }" }>
							${i }
						</a>
					</c:if>
				</c:forEach>
				
				
				<c:if test="${greensPage.currentPage==greensPage.totalPage }">
					下一页&nbsp;&nbsp;&nbsp;尾页
				</c:if>
				<c:if test="${greensPage.currentPage!=greensPage.totalPage }">
					<a href="GreensQueryByRestServlet?currentPage=${greensPage.currentPage+1 }&currentPage1=1&restid=${sessionScope.restgreensid }">下一页</a>&nbsp;&nbsp;&nbsp;
					<a href="GreensQueryByRestServlet?currentPage=${greensPage.totalPage}&currentPage1=1&restid=${sessionScope.restgreensid }">尾页</a>
				</c:if>
				
				&nbsp;&nbsp;&nbsp;到
				<select name="currentPage" onchange="location='GreensQueryByRestServlet?currentPage1=1&restid=${sessionScope.restgreensid }&currentPage='+this.value">
					<c:forEach var="i" begin="1" end="${greensPage.totalPage}" step="1">
						<option value="${i }" ${i==greensPage.currentPage ? "selected" : "" }>${i }</option>
					</c:forEach>
				</select>
				页
			</td>
		</tr>
		
		<tr>
			<td colspan="6" align="center">
				共${greensPage.totalCount } 条记录&nbsp;&nbsp;&nbsp;
				每页${greensPage.pageSize } 条记录&nbsp;&nbsp;&nbsp;
				第【${greensPage.currentPage }】页/共${greensPage.totalPage }页
			</td>
		</tr>

	</table>
	
<hr color="gray" />

<h2>餐厅评论</h2>
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
				<a href="GreensQueryByRestServlet?currentPage1=1&currentPage=1&restid=${sessionScope.restgreensid }">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="GreensQueryByRestServlet?currentPage1=${restcommentPage.currentPage-1}&currentPage=1&restid=${sessionScope.restgreensid }">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${restcommentPage.currentPage==restcommentPage.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${restcommentPage.currentPage!=restcommentPage.totalPage }">
				<a href="GreensQueryByRestServlet?currentPage1=${restcommentPage.currentPage+1}&currentPage=1&restid=${sessionScope.restgreensid }">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="GreensQueryByRestServlet?currentPage1=${restcommentPage.totalPage }&currentPage=1&restid=${sessionScope.restgreensid }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="center">
			<select name="currentPage" onchange="location='GreensQueryByRestServlet?currentPage=1&restid=${sessionScope.restgreensid }&currentPage1=' + this.value">
				<c:forEach var="i" begin="1" end="${restcommentPage.totalPage}" step="1">
					<option value="${i}" ${i==restcommentPage.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
   </table>
</c:if>
<br/>
	<a href = "UserShoppingQueryServlet?currentPage=1">查看购物车</a><br/>
	<a href = "user_index.jsp">返回主頁</a>

	</c:if>
</c:if>
</body>
</html>