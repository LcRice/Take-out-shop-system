package com.neuedu.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.util.CookieUtil;

public class UserLoginCheckFilter implements Filter {

	private String passPageList;
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");	
	
		//获取out输出对象
		PrintWriter out = response.getWriter();

		//转型
		HttpServletRequest req  = (HttpServletRequest)request;
		HttpServletResponse resp  = (HttpServletResponse)response;
		
		//获取session对象
		HttpSession session = req.getSession();
		
		//截取请求路径
		String uri = req.getRequestURI();
	
		//判断session属性范围中是否存在user对象
		//if(session.getAttribute("user")==null && !uri.endsWith("user_login.jsp") && !uri.endsWith("user_register.jsp") ){
		
		//截取请求的页面文件名
		String pageName = uri.substring(uri.lastIndexOf("/") + 1);
		
		//session属性范围中没有user对象，而且访问的页面文件名不在放行列表中
		if(session.getAttribute("user")==null && !passPageList.contains(pageName)){
				
			//获取名叫userInfo的cookie
			String userInfo = CookieUtil.findCookie(req, "userInfo");    //user1#1
			if(userInfo!=null){
				
				String[] userInfos = userInfo.split("#");
				
				String usernumber = userInfos[0];
				String userpassword = userInfos[1];
				
				resp.sendRedirect("UserLoginServlet?usernumber=" + usernumber + "&userpassword=" + userpassword);
				
				return;
			}
			
			//在session属性范围中保存登录后要返回的URL
			
			//截取登录后要返回的URL
			//String uri = req.getRequestURI();
	 		String prevURL = uri.substring(uri.lastIndexOf("/") + 1);
	 
			//在session属性范围中保存登录后要返回的URL
			session.setAttribute("prevURL", prevURL);

			out.println("<script>alert('您尚未登录，请先登录');location.href='user_login.jsp'</script>");
		    
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.passPageList = fConfig.getInitParameter("passPageList");
	}

}
