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
		//���������������
		response.setContentType("text/html;charset=utf-8");	
	
		//��ȡout�������
		PrintWriter out = response.getWriter();

		//ת��
		HttpServletRequest req  = (HttpServletRequest)request;
		HttpServletResponse resp  = (HttpServletResponse)response;
		
		//��ȡsession����
		HttpSession session = req.getSession();
		
		//��ȡ����·��
		String uri = req.getRequestURI();
	
		//�ж�session���Է�Χ���Ƿ����user����
		//if(session.getAttribute("user")==null && !uri.endsWith("user_login.jsp") && !uri.endsWith("user_register.jsp") ){
		
		//��ȡ�����ҳ���ļ���
		String pageName = uri.substring(uri.lastIndexOf("/") + 1);
		
		//session���Է�Χ��û��user���󣬶��ҷ��ʵ�ҳ���ļ������ڷ����б���
		if(session.getAttribute("user")==null && !passPageList.contains(pageName)){
				
			//��ȡ����userInfo��cookie
			String userInfo = CookieUtil.findCookie(req, "userInfo");    //user1#1
			if(userInfo!=null){
				
				String[] userInfos = userInfo.split("#");
				
				String usernumber = userInfos[0];
				String userpassword = userInfos[1];
				
				resp.sendRedirect("UserLoginServlet?usernumber=" + usernumber + "&userpassword=" + userpassword);
				
				return;
			}
			
			//��session���Է�Χ�б����¼��Ҫ���ص�URL
			
			//��ȡ��¼��Ҫ���ص�URL
			//String uri = req.getRequestURI();
	 		String prevURL = uri.substring(uri.lastIndexOf("/") + 1);
	 
			//��session���Է�Χ�б����¼��Ҫ���ص�URL
			session.setAttribute("prevURL", prevURL);

			out.println("<script>alert('����δ��¼�����ȵ�¼');location.href='user_login.jsp'</script>");
		    
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.passPageList = fConfig.getInitParameter("passPageList");
	}

}
