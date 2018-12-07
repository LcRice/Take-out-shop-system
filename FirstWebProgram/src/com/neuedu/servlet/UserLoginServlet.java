package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.Impl.UserServiceImpl;
import com.neuedu.util.CookieUtil;

public class UserLoginServlet extends HttpServlet {
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


		String usernumber = request.getParameter("usernumber");
		String userpassword = request.getParameter("userpassword");

		String member = request.getParameter("member");
		String autoLogin = request.getParameter("autoLogin");

		// 组合业务层对象
		UserService useService = new UserServiceImpl();

		// 调用业务方法
		User user = useService.login(usernumber, userpassword);

		if (user != null) { // 登录成功

			// 登录成功并且选中复选框时，发送一个持久化cookie

			// if(member!=null){ //选中复选框
			if (autoLogin != null) { // 选中复选框
				// 发送一个持久化cookie
				CookieUtil.addCookie(response, "userInfo", usernumber + "#" + userpassword, 7);
			}

			// 在session属性范围中保存用户对象
			session.setAttribute("user", user);

			// 在application属性范围中更新并保存在线人数
			if (application.getAttribute("onlineCount") == null) { // 第一位访客
				application.setAttribute("onlineCount", 1);
			} else {
				application.setAttribute("onlineCount", (Integer) application.getAttribute("onlineCount") + 1);
			}

			// 从session属性范围中获取要返回的URL,默认返回主页
			String prevURL = "user_index.jsp";

			if (session.getAttribute("prevURL") != null) {
				prevURL = (String) session.getAttribute("prevURL");
			}

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
