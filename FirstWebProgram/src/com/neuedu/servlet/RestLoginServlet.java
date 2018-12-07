package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Restaurant;
import com.neuedu.service.RestService;
import com.neuedu.service.Impl.RestServiceImpl;
import com.neuedu.util.CookieUtil;

public class RestLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();

		// 获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();

		// 获取application对象
		ServletContext application = this.getServletContext();


		String restnumber = request.getParameter("restnumber");
		String restpassword = request.getParameter("restpassword");

		String member = request.getParameter("member");
		String autoLogin = request.getParameter("autoLogin");

		// 组合业务层对象
		RestService restService = new RestServiceImpl();

		// 调用业务方法
		Restaurant rest = restService.login(restnumber, restpassword);

		if (rest != null) { // 登录成功

			// 登录成功并且选中复选框时，发送一个持久化cookie

			// if(member!=null){ //选中复选框
			if (autoLogin != null) { // 选中复选框
				// 发送一个持久化cookie
				CookieUtil.addCookie(response, "restInfo", restnumber + "#" + restpassword, 7);
			}

			// 在session属性范围中保存用户对象
			session.setAttribute("rest", rest);

			// 在application属性范围中更新并保存在线人数
			if (application.getAttribute("onlineCount") == null) { // 第一位访客
				application.setAttribute("onlineCount", 1);
			} else {
				application.setAttribute("onlineCount", (Integer) application.getAttribute("onlineCount") + 1);
			}

			// 从session属性范围中获取要返回的URL,默认返回主页
			String prevURL = "rest_index.jsp";

			// 重定向到url
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/" + prevURL));
		} else {
			out.println("<script>alert('用户名或密码错误，请重新输入');history.back()</script>"); // 回退到前一页，之前的数据可以保留
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
